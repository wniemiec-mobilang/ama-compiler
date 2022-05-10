package wniemiec.mobilang.ama.framework.ionic;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.ama.coder.exception.CoderException;
import wniemiec.mobilang.ama.framework.ionic.parser.IonicBehaviorParser;
import wniemiec.mobilang.ama.framework.ionic.parser.IonicStructureParser;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.ScreenData;
import wniemiec.util.java.StringUtils;


/**
 * Responsible for generating Ionic framework code for screens.
 */
class IonicScreensCoder {

    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    private static final String SCREEN_NAME_PREFIX;
    private final List<ScreenData> screensData;
    private IonicStructureParser structureParser;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        SCREEN_NAME_PREFIX = "src/app/pages/";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicScreensCoder(List<ScreenData> screensData) {
        this.screensData = screensData;
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<CodeFile> generateCode() throws CoderException {
        List<CodeFile> screensCode = new ArrayList<>();

        for (ScreenData screenData : screensData) {
            screensCode.addAll(generateCodeForScreen(screenData));
        }

        return screensCode;
    }
    
    private List<CodeFile> generateCodeForScreen(ScreenData screenData) 
    throws CoderException {
        return List.of(
            buildModuleFileCode(screenData),
            buildHtmlFileCode(screenData),
            buildScssFileCode(screenData),
            buildPageFileCode(screenData),
            buildRoutingFileCode(screenData)
        );
    }

    private CodeFile buildModuleFileCode(ScreenData screen) {
        List<String> code = new ArrayList<>();
        String name = StringUtils.capitalize(screen.getName());

        code.add("import { NgModule } from '@angular/core';");
        code.add("import { CommonModule } from '@angular/common';");
        code.add("import { FormsModule } from '@angular/forms';");
        code.add("import { IonicModule, IonicRouteStrategy } from '@ionic/angular';");
        code.add("import { " + name + "Page } from './" + name.toLowerCase() + ".page';");
        code.add("import { " + name + "PageRoutingModule } from './" + name.toLowerCase() + "-routing.module';");
        code.add("");
        code.add("@NgModule({");
        code.add("  imports: [");
        code.add("    CommonModule,");
        code.add("    FormsModule,");
        code.add("    IonicModule,");
        code.add("    " + name + "PageRoutingModule");
        code.add("  ],");
        code.add("  declarations: [" + name + "Page]");
        code.add("})");
        code.add("export class " + name + "PageModule {}");

        return generateCodeFileFor(screen, ".module.ts", code);
    }

    private CodeFile generateCodeFileFor(ScreenData screen, String suffix, List<String> code) {
        String filename = generateScreenFilename(screen, suffix);

        return new CodeFile(filename, code);
    }

    private CodeFile buildHtmlFileCode(ScreenData screen) {
        structureParser = new IonicStructureParser(screen.getStructure());
        
        List<String> structureCode = structureParser.parse();
        List<String> code = new ArrayList<>();

        code.add("<ion-content>");
        code.addAll(structureCode);
        code.add("</ion-content>");

        return generateCodeFileFor(screen, ".page.html", code);
    }

    private CodeFile buildScssFileCode(ScreenData screen) {
        List<String> code = screen.getStyle().toCode();

        return generateCodeFileFor(screen, ".page.scss", code);
    }

    private CodeFile buildPageFileCode(ScreenData screen) throws CoderException {
        IonicBehaviorParser behaviorProcessor = new IonicBehaviorParser(screen.getBehavior());
        List<String> behaviorCode = behaviorProcessor.parse();

        List<String> code = new ArrayList<>();
        String name = StringUtils.capitalize(screen.getName());

        code.add("import { Component, OnInit, ViewEncapsulation } from '@angular/core';");
        code.add("import { ActivatedRoute } from '@angular/router';");
        code.add("@Component({");
        code.add("  selector: '" + name.toLowerCase() + "-page',");
        code.add("  templateUrl: '" + name.toLowerCase() + ".page.html',");
        code.add("  styleUrls: ['" + name.toLowerCase() + ".page.scss'],");
        code.add("  encapsulation: ViewEncapsulation.None");
        code.add("})");
        code.add("export class " + name + " implements OnInit {");

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

    private CodeFile buildRoutingFileCode(ScreenData screen) {
        List<String> code = new ArrayList<>();
        String name = StringUtils.capitalize(screen.getName());

        code.add("import { " + name + "Page } from './" + name.toLowerCase() + ".page';");
        code.add("import { NgModule } from '@angular/core';");
        code.add("import { PreloadAllModules, RouterModule, Routes } from '@angular/router';");
        code.add("");
        code.add("const routes: Routes = [");
        code.add("  {");
        code.add("    path: '',");
        code.add("    component: " + name + "Page");
        code.add("  }");
        code.add("];");
        code.add("");
        code.add("@NgModule({");
        code.add("  imports: [RouterModule.forChild(routes)],");
        code.add("  exports: [RouterModule],");
        code.add("})");
        code.add("export class " + name + "PageRoutingModule {}");

        return generateCodeFileFor(screen, "routing.module.ts", code);
    }

    private String generateScreenFilename(ScreenData screenData, String suffix) {
        StringBuilder filename = new StringBuilder();

        filename.append(SCREEN_NAME_PREFIX);
        filename.append(screenData.getName());
        filename.append(suffix);

        return filename.toString();
    }
}
