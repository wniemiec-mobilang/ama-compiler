package wniemiec.mobilang.ama.framework.ionic.coder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import wniemiec.mobilang.ama.coder.exception.CoderException;
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


class IonicScreensCoderTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final int INDEX_MODULE;
    private static final int INDEX_HTML;
    private static final int INDEX_SCSS;
    private static final int INDEX_PAGE;
    private static final int INDEX_ROUTING;
    private static final String TAG_ID;
    private IonicScreensCoder coder;
    private List<Screen> screens;
    private List<CodeFile> obtainedCode;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        INDEX_MODULE = 0;
        INDEX_HTML = 1;
        INDEX_SCSS = 2;
        INDEX_PAGE = 3;
        INDEX_ROUTING = 4;
        TAG_ID = "tagid";
    }


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
    void testSingleScreen() throws CoderException {
        withScreen(new Screen.Builder()
            .name("about")
            .structure(buildButtonWithOnClickAndValue("click me"))
            .style(buildButtonStyleUsingBlueAndWhite())
            .behavior(buildDeclarationWithIdAndAssignment("hello", "world"))
            .build()
        );
        runCoder();
        assertCoderGeneratedSomething();
        assertCodeFileHasName(
            "src/app/pages/about/about.module.ts",
            "src/app/pages/about/about.page.html",
            "src/app/pages/about/about.page.scss",
            "src/app/pages/about/about.page.ts",
            "src/app/pages/about/about-routing.module.ts"
        );
        assertModuleCodeEquals(
            "import { NgModule } from '@angular/core';",
            "import { CommonModule } from '@angular/common';",
            "import { FormsModule } from '@angular/forms';",
            "import { IonicModule, IonicRouteStrategy } from '@ionic/angular';",
            "import { AboutPage } from './about.page';",
            "import { AboutPageRoutingModule } from './about-routing.module';",
            "",
            "@NgModule({",
            "  imports: [",
            "    CommonModule,",
            "    FormsModule,",
            "    IonicModule,",
            "    AboutPageRoutingModule",
            "  ],",
            "  declarations: [AboutPage]",
            "})",
            "export class AboutPageModule {}"
        );
        assertHtmlCodeEquals(
            "<ion-content>",
            "    <button id=\"" + TAG_ID + "\">",
            "        click me", 
            "    </button>",
            "</ion-content>"
        );
        assertScssCodeEquals(
            "button {",
            "    background-color: blue;",
            "    color: white;",
            "}"
        );
        assertPageCodeEquals(
            "import { Component, OnInit, ViewEncapsulation } from '@angular/core';",
            "import { ActivatedRoute } from '@angular/router';",
            "@Component({",
            "  selector: 'about-page',",
            "  templateUrl: 'about.page.html',",
            "  styleUrls: ['about.page.scss'],",
            "  encapsulation: ViewEncapsulation.None",
            "})",
            "export class AboutPage implements OnInit {",
            "",
            "  constructor(private routeParams: ActivatedRoute) {",
            "  }",
            "",
            "  ngOnInit(): void {",
            "document.getElementById(\"" + TAG_ID + "\").onclick = () => alert('hey!! you pressed the button!');",
            "let hello = \"world\";",
            "",
            "  }",
            "}"
        );
        assertRoutingCodeEquals(
            "import { AboutPage } from './about.page';",
            "import { NgModule } from '@angular/core';",
            "import { PreloadAllModules, RouterModule, Routes } from '@angular/router';",
            "",
            "const routes: Routes = [",
            "  {",
            "    path: '',",
            "    component: AboutPage",
            "  }",
            "];",
            "",
            "@NgModule({",
            "  imports: [RouterModule.forChild(routes)],",
            "  exports: [RouterModule],",
            "})",
            "export class AboutPageRoutingModule {}"
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
        buttonTag.addAttribute("id", TAG_ID);
        
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

    private void runCoder() throws CoderException {
        coder = new IonicScreensCoder(screens);
        obtainedCode = coder.generateCode();
    }

    private void assertCoderGeneratedSomething() {
        Assertions.assertFalse(obtainedCode.isEmpty());
    }

    private void assertCodeFileHasName(String... names) {
        for (int i = 0; i < names.length; i++) {
            Assertions.assertEquals(names[i], obtainedCode.get(i).getName());
        }
    }

    private void assertModuleCodeEquals(String... lines) {
        assertCodeEquals(INDEX_MODULE, lines);
    }

    private void assertCodeEquals(int index, String... lines) {
        List<String> expectedCode = Arrays.asList(lines);

        assertHasSameSize(expectedCode, obtainedCode.get(index).getCode());
        assertHasSameLines(expectedCode, obtainedCode.get(index).getCode());
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

    private void assertHtmlCodeEquals(String... lines) {
        assertCodeEquals(INDEX_HTML, lines);
    }

    private void assertScssCodeEquals(String... lines) {
        assertCodeEquals(INDEX_SCSS, lines);
    }

    private void assertPageCodeEquals(String... lines) {
        assertCodeEquals(INDEX_PAGE, lines);
    }

    private void assertRoutingCodeEquals(String... lines) {
        assertCodeEquals(INDEX_ROUTING, lines);
    }
}
