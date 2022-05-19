package wniemiec.mobilang.ama.framework.ionic.coder;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.Screen;
import wniemiec.util.java.StringUtils;


public class IonicRoutingCoder {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private static final String APP_PATH;
    private final List<CodeFile> routingCodes;
    private final List<Screen> screensData;


    //-------------------------------------------------------------------------
    //		Initialization block
    //-------------------------------------------------------------------------
    static {
        APP_PATH = "src/app";
    }


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicRoutingCoder(List<Screen> screensData) {
        this.screensData = screensData;
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

        for (Screen screen : screensData) {
            String normalizedScreenName = normalizeScreenName(screen.getName());

            code.add("  {");
            code.add("    path: '" + screen.getName() + "',");
            code.add("    loadChildren: () => import('./pages/" + screen.getName() + "/" + screen.getName() + ".module').then( m => m." + normalizedScreenName + "PageModule)");
            code.add("  },");
            code.add("  {");
            code.add("    path: '" + screen.getName() + "/:q',");
            code.add("    loadChildren: () => import('./pages/" + screen.getName() + "/" + screen.getName() + ".module').then( m => m." + normalizedScreenName + "PageModule)");
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

    private String normalizeScreenName(String name) {
        StringBuilder normalizedName = new StringBuilder();
        
        for (String term : name.split("-")) {
            normalizedName.append(StringUtils.capitalize(term));
        }

        return normalizedName.toString();
    }
}
