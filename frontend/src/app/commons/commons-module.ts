import {CommonModule} from "@angular/common";
import {NotFoundComponent} from "./pages/not-found/not-found.component";
import {FormsModule} from "@angular/forms";
import {NgModule} from "@angular/core";
import {ManagerHomepageComponent} from "./components/manager-homepage.component";

@NgModule({
  declarations: [
    NotFoundComponent,
    ManagerHomepageComponent,
  ],
  imports: [
    FormsModule,
    CommonModule
  ],
  exports: [
    CommonModule,
    FormsModule,
    NotFoundComponent,
  ]
})
export class CommonsModule {

}
