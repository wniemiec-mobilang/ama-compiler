package wniemiec.mobilang.ama.framework.ionic.coder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.Screen;
import wniemiec.mobilang.ama.models.Style;
import wniemiec.mobilang.ama.models.StyleSheetRule;
import wniemiec.mobilang.ama.models.behavior.Behavior;
import wniemiec.mobilang.ama.models.behavior.Declaration;
import wniemiec.mobilang.ama.models.behavior.Declarator;
import wniemiec.mobilang.ama.models.behavior.Instruction;
import wniemiec.mobilang.ama.models.behavior.Literal;
import wniemiec.mobilang.ama.models.tag.Tag;


class IonicRoutingCoderTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private IonicRoutingCoder coder;
    private List<Screen> screens = new ArrayList<>();
    private List<CodeFile> obtainedCode;


    //-------------------------------------------------------------------------
    //		Test hooks
    //-------------------------------------------------------------------------
    @BeforeEach
    void setUp() {
        screens = new ArrayList<>();
    }
    

    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testSingleScreen() {
        withScreen(new Screen.Builder()
            .name("about")
            .structure(buildButtonWithOnClickAndValue("click me"))
            .style(buildButtonStyleUsingBlueAndWhite())
            .behavior(buildDeclarationWithIdAndAssignment("hello", "world"))
            .build()
        );
        runCoder();
        assertCoderGeneratedSomething();
        assertCodeFileHasName("src/app/app-routing.module.ts");
        assertCodeEquals(
            "import { NgModule } from '@angular/core';",
            "import { PreloadAllModules, RouterModule, Routes } from '@angular/router';",
            "",
            "const routes: Routes = [",
            "  {",
            "    path: '',",
            "    redirectTo: 'home',",
            "    pathMatch: 'full'",
            "  },",
            "  {",
            "    path: 'home',",
            "    loadChildren: () => import('./pages/home/home.module').then( m => m.HomePageModule)",
            "  },",
            "  {",
            "    path: 'about',",
            "    loadChildren: () => import('./pages/about/about.module').then( m => m.AboutPageModule)",
            "  },",
            "  {",
            "    path: 'about/:q',",
            "    loadChildren: () => import('./pages/about/about.module').then( m => m.AboutPageModule)",
            "  },",
            "];",
            "",
            "@NgModule({",
            "  imports: [",
            "    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })",
            "  ],",
            "  exports: [RouterModule]",
            "})",
            "export class AppRoutingModule {}"
        );
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withScreen(Screen screen) {
        screens.add(screen);
    }

    private Tag buildButtonWithOnClickAndValue(String value) {
        Tag buttonTag = Tag.getNormalInstance("button");
        
        buttonTag.addAttribute("onclick", "alert('hey!! you pressed the button!')");
        buttonTag.setValue(value);
        
        return buttonTag;
    }

    private Style buildButtonStyleUsingBlueAndWhite() {
        Style style = new Style();
        StyleSheetRule rule = new StyleSheetRule();

        rule.addSelector("button");
        rule.addDeclaration("background-color", "blue");
        rule.addDeclaration("color", "white");
        style.addRule(rule);

        return style;
    }

    private Behavior buildDeclarationWithIdAndAssignment(String id, String assignment) {
        Declaration declaration = buildLiteralDeclaration(id, assignment);

        return buildBehaviorWith(declaration);
    }

    private Declaration buildLiteralDeclaration(String id, String assignment) {
        Declarator declarator = new Declarator(
            "string", 
            "let", 
            id, 
            Literal.ofString(assignment)
        );
        
        return new Declaration("let", declarator);
    }

    private Behavior buildBehaviorWith(Instruction... declarations) {
        return new Behavior(Arrays.asList(declarations));
    }

    private void runCoder() {
        coder = new IonicRoutingCoder(screens);
        obtainedCode = coder.generateCode();
    }

    private void assertCoderGeneratedSomething() {
        Assertions.assertFalse(obtainedCode.isEmpty());
    }

    private void assertCodeFileHasName(String name) {
        Assertions.assertEquals(name, obtainedCode.get(0).getName());
    }

    private void assertCodeEquals(String... lines) {
        List<String> expectedCode = Arrays.asList(lines);

        assertHasSameSize(expectedCode, obtainedCode.get(0).getCode());
        assertHasSameLines(expectedCode, obtainedCode.get(0).getCode());
    }

    private void assertHasSameSize(List<String> expected, List<String> obtained) {
        Assertions.assertEquals(expected.size(), obtained.size());
    }

    private void assertHasSameLines(List<String> expected, List<String> obtained) {
        for (int i = 0; i < expected.size(); i++) {            
            assertHasSameLine(expected.get(i), obtained.get(i));
        }
    }

    private void assertHasSameLine(String expected, String obtained) {
        Assertions.assertEquals(
            removeWhiteSpaces(expected),
            removeWhiteSpaces(obtained)
        );
    }

    private String removeWhiteSpaces(String text) {
        return text.replaceAll("[\\s\\t]+", "");
    }
}
