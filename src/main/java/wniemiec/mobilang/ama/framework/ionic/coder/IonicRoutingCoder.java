package wniemiec.mobilang.ama.framework.ionic.coder;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.Screen;


public class IonicRoutingCoder {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String APP_PATH;
    private final List<CodeFile> routingCodes;
    private final List<Screen> screens;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        APP_PATH = "src/app";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicRoutingCoder(List<Screen> screens) {
        this.screens = screens;
        routingCodes = new ArrayList<>();
    }


    //-------------------------------------------------------------------------
    //		Methods
    //-------------------------------------------------------------------------
    public List<CodeFile> generateCode() {
        generateAppRoutingCode();

        return routingCodes;
    }

    private void generateAppRoutingCode() {
        List<String> code = new ArrayList<>();

        code.add("import { NgModule } from '@angular/core';");
        code.add("import { PreloadAllModules, RouterModule, Routes } from '@angular/router';");
        code.add("");
        code.add("const routes: Routes = [");
        code.add("  {");
        code.add("    path: '',");
        code.add("    redirectTo: 'home',");
        code.add("    pathMatch: 'full'");
        code.add("  },");
        code.add("  {");
        code.add("    path: 'home',");
        code.add("    loadChildren: () => import('./pages/home/home.module').then( m => m.HomePageModule)");
        code.add("  },");

        for (Screen screen : screens) {

            code.add("  {");
            code.add("    path: '" + screen.getRawName() + "',");
            code.add("    loadChildren: () => import('./pages/" + screen.getRawName() + "/" + screen.getRawName() + ".module').then( m => m." + screen.getName() + "PageModule)");
            code.add("  },");
            code.add("  {");
            code.add("    path: '" + screen.getRawName() + "/:q',");
            code.add("    loadChildren: () => import('./pages/" + screen.getRawName() + "/" + screen.getRawName() + ".module').then( m => m." + screen.getName() + "PageModule)");
            code.add("  },");
        }

        code.add("];");
        code.add("");
        code.add("@NgModule({");
        code.add("  imports: [");
        code.add("    RouterModule.forRoot(routes, { preloadingStrategy: PreloadAllModules })");
        code.add("  ],");
        code.add("  exports: [RouterModule]");
        code.add("})");
        code.add("export class AppRoutingModule {}");

        routingCodes.add(new CodeFile(APP_PATH + "/app-routing.module.ts", code));
    }
}
