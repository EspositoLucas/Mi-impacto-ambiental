import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgSelectModule } from '@ng-select/ng-select';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';

import { httpInterceptorProviders } from './httpInterceptors';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { LogoutComponent } from './logout/logout.component';
import { CrearOrganizacionComponent } from './organizacion/crear-organizacion/crear-organizacion.component';
import { TipoOrganizacionSelectComponent } from './selects/tipo-organizacion-select/tipo-organizacion-select.component';
import { BaseSelectComponent } from './selects/base-select/base-select.component';
import { UnidadSelectComponent } from './selects/unidad-select/unidad-select.component';
import { IndexOrganizacionComponent } from './organizacion/index-organizacion/index-organizacion.component';
import { IndexTemplateComponent } from './templates/index-template/index-template.component';
import { EditOrganizacionComponent } from './organizacion/edit-organizacion/edit-organizacion.component';
import { ClasificacionOrganizacionSelectComponent } from './selects/clasificacion-organizacion-select/clasificacion-organizacion-select.component';
import { GrillaContactosComponent } from './organizacion/grilla-contactos/grilla-contactos.component';

@NgModule({
    declarations: [
        AppComponent,
        LoginComponent,
        NavbarComponent,
        HomeComponent,
        LogoutComponent,
        CrearOrganizacionComponent,
        TipoOrganizacionSelectComponent,
        BaseSelectComponent,
        UnidadSelectComponent,
        IndexOrganizacionComponent,
        IndexTemplateComponent,
        EditOrganizacionComponent,
        ClasificacionOrganizacionSelectComponent,
        GrillaContactosComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgbModule,
        NgSelectModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
    ],
    providers: [httpInterceptorProviders],
    bootstrap: [AppComponent],
})
export class AppModule {}
