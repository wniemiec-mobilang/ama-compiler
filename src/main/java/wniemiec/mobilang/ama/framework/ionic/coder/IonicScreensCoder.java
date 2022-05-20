package wniemiec.mobilang.ama.framework.ionic.coder;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.ama.coder.exception.CoderException;
import wniemiec.mobilang.ama.framework.ionic.parser.IonicBehaviorParser;
import wniemiec.mobilang.ama.framework.ionic.parser.IonicStructureParser;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.Screen;


/**
 * Responsible for generating Ionic framework code for screens.
 */
public class IonicScreensCoder {

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private static final String APP_PAGES_PATH;
    private final List<Screen> screens;
    private IonicStructureParser structureParser;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        APP_PAGES_PATH = "src/app/pages";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicScreensCoder(List<Screen> screens) {
        this.screens = screens;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<CodeFile> generateCode() throws CoderException {
        List<CodeFile> screensCode = new ArrayList<>();

        for (Screen screenData : screens) {
            screensCode.addAll(generateCodeForScreen(screenData));
        }

        return screensCode;
    }
    
    private List<CodeFile> generateCodeForScreen(Screen screenData) 
    throws CoderException {
        return List.of(
            buildModuleFileCode(screenData),
            buildHtmlFileCode(screenData),
            buildScssFileCode(screenData),
            buildPageFileCode(screenData),
            buildRoutingFileCode(screenData)
        );
    }

    private CodeFile buildModuleFileCode(Screen screen) {
        List<String> code = new ArrayList<>();

        code.add("import { NgModule } from '@angular/core';");
        code.add("import { CommonModule } from '@angular/common';");
        code.add("import { FormsModule } from '@angular/forms';");
        code.add("import { IonicModule, IonicRouteStrategy } from '@ionic/angular';");
        code.add("import { " + screen.getName() + "Page } from './" + screen.getRawName().toLowerCase() + ".page';");
        code.add("import { " + screen.getName() + "PageRoutingModule } from './" + screen.getRawName().toLowerCase() + "-routing.module';");
        code.add("");
        code.add("@NgModule({");
        code.add("  imports: [");
        code.add("    CommonModule,");
        code.add("    FormsModule,");
        code.add("    IonicModule,");
        code.add("    " + screen.getName() + "PageRoutingModule");
        code.add("  ],");
        code.add("  declarations: [" + screen.getName() + "Page]");
        code.add("})");
        code.add("export class " + screen.getName() + "PageModule {}");

        return generateCodeFileFor(screen, ".module.ts", code);
    }

    private CodeFile generateCodeFileFor(Screen screen, String suffix, List<String> code) {
        String filename = generateScreenFilename(screen, suffix);

        return new CodeFile(filename, code);
    }

    private CodeFile buildHtmlFileCode(Screen screen) {
        structureParser = new IonicStructureParser();
        structureParser.parse(screen.getStructure());
        
        List<String> structureCode = structureParser.getParsedCode();
        List<String> code = new ArrayList<>();

        code.add("<ion-content>");
        code.addAll(structureCode);
        code.add("</ion-content>");

        return generateCodeFileFor(screen, ".page.html", code);
    }

    private CodeFile buildScssFileCode(Screen screen) {
        List<String> code = screen.getStyle().toCode();

        return generateCodeFileFor(screen, ".page.scss", code);
    }

    private CodeFile buildPageFileCode(Screen screen) throws CoderException {
        IonicBehaviorParser behaviorProcessor = new IonicBehaviorParser();
        behaviorProcessor.parse(screen.getBehavior());
        List<String> behaviorCode = behaviorProcessor.getParsedCode();

        List<String> code = new ArrayList<>();

        code.add("import { Component, OnInit, ViewEncapsulation } from '@angular/core';");
        code.add("import { ActivatedRoute } from '@angular/router';");
        code.add("@Component({");
        code.add("  selector: '" + screen.getRawName().toLowerCase() + "-page',");
        code.add("  templateUrl: '" + screen.getRawName().toLowerCase() + ".page.html',");
        code.add("  styleUrls: ['" + screen.getRawName().toLowerCase() + ".page.scss'],");
        code.add("  encapsulation: ViewEncapsulation.None");
        code.add("})");
        code.add("export class " + screen.getName() + "Page implements OnInit {");

        for (String id : structureParser.getInputIds()) {
            String normalizedId = id.replace("-", "_");

            code.add("  _" + normalizedId + " = \"\";");
        }

        code.add("");
        code.add("  constructor(private routeParams: ActivatedRoute) {");
        code.add("  }");
        code.add("");
        code.add("  ngOnInit(): void {");
        code.addAll(behaviorCode);
        code.add("");
        code.add("  }");
        code.add("}");

        return generateCodeFileFor(screen, ".page.ts", code);
    }

    private CodeFile buildRoutingFileCode(Screen screen) {
        List<String> code = new ArrayList<>();

        code.add("import { " + screen.getName() + "Page } from './" + screen.getRawName().toLowerCase() + ".page';");
        code.add("import { NgModule } from '@angular/core';");
        code.add("import { PreloadAllModules, RouterModule, Routes } from '@angular/router';");
        code.add("");
        code.add("const routes: Routes = [");
        code.add("  {");
        code.add("    path: '',");
        code.add("    component: " + screen.getName() + "Page");
        code.add("  }");
        code.add("];");
        code.add("");
        code.add("@NgModule({");
        code.add("  imports: [RouterModule.forChild(routes)],");
        code.add("  exports: [RouterModule],");
        code.add("})");
        code.add("export class " + screen.getName() + "PageRoutingModule {}");

        return generateCodeFileFor(screen, "-routing.module.ts", code);
    }

    private String generateScreenFilename(Screen screen, String suffix) {
        StringBuilder filename = new StringBuilder();

        filename.append(APP_PAGES_PATH);
        filename.append('/');
        filename.append(screen.getRawName());
        filename.append('/');
        filename.append(screen.getRawName());
        filename.append(suffix);

        return filename.toString();
    }
}
