package wniemiec.mobilex.ama.framework.ionic;

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import util.io.MockFileManager;
import util.terminal.MockInputTerminal;
import util.terminal.MockOutputTerminal;
import wniemiec.io.java.Consolex;
import wniemiec.io.java.LogLevel;
import wniemiec.io.java.Terminal;
import wniemiec.mobilex.ama.coder.exception.CoderException;
import wniemiec.mobilex.ama.export.exception.AppGenerationException;
import wniemiec.mobilex.ama.framework.ionic.IonicFramework;
import wniemiec.mobilex.ama.models.CodeFile;
import wniemiec.mobilex.ama.models.Properties;
import wniemiec.mobilex.ama.models.Screen;
import wniemiec.mobilex.ama.models.Style;
import wniemiec.mobilex.ama.models.StyleSheetRule;
import wniemiec.mobilex.ama.models.behavior.Behavior;
import wniemiec.mobilex.ama.models.behavior.Declaration;
import wniemiec.mobilex.ama.models.behavior.Declarator;
import wniemiec.mobilex.ama.models.behavior.Instruction;
import wniemiec.mobilex.ama.models.behavior.Literal;
import wniemiec.mobilex.ama.models.tag.Tag;


class IonicFrameworkTest {
    
    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final int INDEX_MODULE;
    private static final int INDEX_HTML;
    private static final int INDEX_SCSS;
    private static final int INDEX_PAGE;
    private static final int INDEX_ROUTING;
    private static final String TAG_ID;
    private IonicFramework ionicFramework;
    private MockInputTerminal mockInputTerminal;
    private MockOutputTerminal mockOutputTerminal;
    private MockFileManager mockFileManager;
    private Terminal terminal;
    private Properties properties;
    private Path projectLocation;
    private List<Screen> screens;
    private List<CodeFile> obtainedCode;
    private Path sourceCodePath;
    private Path mobileOutput;
    private String keystorePassword;


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
        mockInputTerminal = new MockInputTerminal();
        mockOutputTerminal = new MockOutputTerminal();
        terminal = new Terminal(mockInputTerminal, mockOutputTerminal);
        properties = new Properties();
        mockFileManager = new MockFileManager();
        ionicFramework = new IonicFramework(terminal, mockFileManager);
        screens = new ArrayList<>();
        obtainedCode = new ArrayList<>();
        Consolex.setLoggerLevel(LogLevel.OFF);
    }

    @AfterEach
    void cleanUp() {
        Consolex.setLoggerLevel(LogLevel.INFO);
    }


    //-------------------------------------------------------------------------
    //		Tests
    //-------------------------------------------------------------------------
    @Test
    void testProjectCreator() throws IOException {
        withAppName("myapp");
        withTargetPlatforms("android");
        withLocation("myapp/bar");
        withKeystorePassword("abcdef12");
        runProjectCreator();
        assertTerminalHistoryIsEmpty();
        assertTerminalErrorHistoryIsEmpty();
        assertInputTerminalWas(
            "ionic", 
            "start", 
            "myapp",
            "blank",
            "--type=angular",
            "--capacitor",
            "--confirm",
            "--no-interactive",
            "mv", 
            "myapp", 
            "bar",
            "mv",
            "bar",
            "myapp"
        );
        assertFileManagerExecuted(
            "APPEND: myapp/bar/src/global.scss:global.scss\n.ion-page {\n  justify-content: flex-start;\n}",
            "REMOVE FILE: myapp/bar/src/theme/variables.scss",
            "CREATE FILE: myapp/bar/src/theme/variables.scss",
            "REMOVE DIRECTORY: myapp/bar/src/app/home",
            "REMOVE FILE: myapp/bar/src/app/app-routing.module.ts",
            "CREATE DIRECTORY: myapp/bar/src/app/pages"
        );
    }

    @Test
    void testProjectDependencies() throws IOException {
        withAppName("myapp");
        withTargetPlatforms("android");
        withLocation("myapp/bar");
        withKeystorePassword("abcdef12");
        runProjectCreator();
        addProjectDependencies(
            "dependency1/somelib",
            "@somecompany/foo"
        );
        assertTerminalHistoryIsEmpty();
        assertTerminalErrorHistoryIsEmpty();
        assertInputTerminalWas(
            "ionic", 
            "start", 
            "myapp",
            "blank",
            "--type=angular",
            "--capacitor",
            "--confirm",
            "--no-interactive",
            "mv", 
            "myapp", 
            "bar",
            "mv",
            "bar",
            "myapp",
            "npm", 
            "install", 
            "--prefix",
            "myapp/bar",
            "--save", 
            "dependency1/somelib",
            "npm", 
            "install", 
            "--prefix",
            "myapp/bar",
            "--save", 
            "@somecompany/foo"
        );
        assertFileManagerExecuted(
            "APPEND: myapp/bar/src/global.scss:global.scss\n.ion-page {\n  justify-content: flex-start;\n}",
            "REMOVE FILE: myapp/bar/src/theme/variables.scss",
            "CREATE FILE: myapp/bar/src/theme/variables.scss",
            "REMOVE DIRECTORY: myapp/bar/src/app/home",
            "REMOVE FILE: myapp/bar/src/app/app-routing.module.ts",
            "CREATE DIRECTORY: myapp/bar/src/app/pages"
        );
    }

    @Test
    void testSingleScreen() throws CoderException {
        withScreen(new Screen.Builder()
            .name("about")
            .structure(buildButtonWithOnClickAndValue("click me"))
            .style(buildButtonStyleUsingBlueAndWhite())
            .behavior(buildDeclarationWithIdAndAssignment("hello", "world"))
            .build()
        );
        runCodeGeneration();
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

    @Test
    void testAndroidGeneration() throws AppGenerationException {
        withSourceCodePath("myapp/foo");
        withMobileOutputPath("myapp/bar");
        withKeystorePassword("abcdef12");
        runAppGenerator("android");
        assertTerminalHistoryIsEmpty();
        assertTerminalErrorHistoryIsEmpty();
        assertInputTerminalWas(
            "keytool",
            "-genkeypair",
            "-v",
            "-keystore",
            "myapp/foo/android/app/myapp.keystore",
            "-alias",
            "myapp",
            "-keyalg",
            "RSA",
            "-keysize",
            "2048",
            "-validity",
            "10000",
            "-dname",
            "cn=Unknown,ou=Unknown,o=Unknown,c=Unknown",
            "-storepass",
            "abcdef12",
            "-keypass",
            "abcdef12",
            "gradlew",
            "-p",
            "myapp/foo/android",
            "bundleRelease"
        );
        assertFileManagerExecuted(
            "REMOVE FILE: myapp/foo/android/app/myapp.keystore",
            "APPEND: myapp/foo/android/gradle.properties:MYAPP_UPLOAD_STORE_FILE=myapp.keystore",
            "APPEND: myapp/foo/android/gradle.properties:MYAPP_UPLOAD_KEY_ALIAS=myapp",
            "APPEND: myapp/foo/android/gradle.properties:MYAPP_UPLOAD_STORE_PASSWORD=abcdef12",
            "APPEND: myapp/foo/android/gradle.properties:MYAPP_UPLOAD_KEY_PASSWORD=abcdef12",
            "APPEND: myapp/foo/android/gradle.properties:org.gradle.jvmargs=-Xmx4608m",
            "WRITE: myapp/foo/android/app/build.gradle:// Modified by SCMA",
            "COPY: myapp/foo/android/app/build/outputs/bundle/release/app-release.aab -> myapp/bar/mobile/android/myapp.aab"
        );
    }
    

    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    private void withAppName(String name) {
        properties.setAppName(name);
    }

    private void withTargetPlatforms(String... platforms) {
        for (String platform : platforms) {
            properties.addPlatform(platform);
        }
    }

    private void withLocation(String location) {
        projectLocation = Path.of(location);
    }

    private void withKeystorePassword(String password) {
        keystorePassword = password;
    }

    private void runProjectCreator() throws IOException  {
        ionicFramework.createProject(properties, projectLocation);
    }

    private void addProjectDependencies(String... dependencies) throws IOException {
        for (String dependency : dependencies) {
            ionicFramework.addProjectDependency(dependency, projectLocation);
        }
    }

    private void assertTerminalHistoryIsEmpty() {
        Assertions.assertTrue(terminal.getHistory().isEmpty());
    }

    private void assertTerminalErrorHistoryIsEmpty() {
        Assertions.assertTrue(terminal.getErrorHistory().isEmpty());
    }

    private void assertInputTerminalWas(String... actions) {
        assertCodeEquals(mockInputTerminal.getLog(), actions);
    }

    private void assertCodeEquals(List<String> obtained, String... lines) {
        List<String> expectedCode = Arrays.asList(lines);

        assertHasSameSize(expectedCode, obtained);
        assertHasSameLines(expectedCode, obtained);
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

    private void assertFileManagerExecuted(String... actions) {
        assertCodeEquals(mockFileManager.getLog(), actions);
    }

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

    private void runCodeGeneration() throws CoderException {
        obtainedCode = ionicFramework.generateCode(screens).getCodeFiles();
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

    private void withSourceCodePath(String location) {
        sourceCodePath = Path.of(location);
    }

    private void withMobileOutputPath(String location) {
        mobileOutput = Path.of(location);
    }

    private void runAppGenerator(String platform) throws AppGenerationException {
        ionicFramework.generateMobileApplicationFor(
            platform, 
            sourceCodePath, 
            mobileOutput
        );
    }
}
