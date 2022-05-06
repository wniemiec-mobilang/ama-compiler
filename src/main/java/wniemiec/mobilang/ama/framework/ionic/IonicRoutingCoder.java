package wniemiec.mobilang.ama.framework.ionic;

import java.util.ArrayList;
import java.util.List;
import wniemiec.mobilang.ama.models.CodeFile;
import wniemiec.mobilang.ama.models.ScreenData;


class IonicRoutingCoder {

    //-------------------------------------------------------------------------
    //		Attributes
    //-------------------------------------------------------------------------
    private final List<CodeFile> routingCodes;
    private final List<ScreenData> screensData;


    //-------------------------------------------------------------------------
    //		Constructor
    //-------------------------------------------------------------------------
    public IonicRoutingCoder(List<ScreenData> screensData) {
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

        for (ScreenData screen : screensData) {
            code.add("  {");
            code.add("    path: '" + screen.getName() + "',");
            code.add("    loadChildren: () => import('./pages/" + screen.getName() + "/" + screen.getName() + ".module').then( m => m." + screen.getName() + "PageModule)");
            code.add("  },");
            code.add("  {");
            // TODO: replace 'q' with correct screen parameters (available at IonicMobiLangDirectiveParser.getScreenParameters())
            co2de.add("    path: '" + screen.getName() + "/:q',");
            code.add("    loadChildren: () => import('./pages/" + screen.getName() + "/" + screen.getName() + ".module').then( m => m." + screen.getName() + "PageModule)");
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

        routingCodes.add(new CodeFile("app/app-routing.module.ts", code));
    }
}
