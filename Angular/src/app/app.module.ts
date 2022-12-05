import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { NgSelectModule } from '@ng-select/ng-select';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { NgbModule } from '@ng-bootstrap/ng-bootstrap';
import { LoginComponent } from './login/login.component';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { HttpClientModule } from '@angular/common/http';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';

import { httpInterceptorProviders } from './httpInterceptors';
import { NavbarComponent } from './navbar/navbar.component';
import { HomeComponent } from './home/home.component';
import { LogoutComponent } from './logout/logout.component';
import { CrearOrganizacionComponent } from './organizacion/crear-organizacion/crear-organizacion.component';
import { TipoOrganizacionSelectComponent } from './selects/tipo-organizacion-select/tipo-organizacion-select.component';
import { BaseSelectComponent } from './selects/base-select/base-select.component';
import { UnidadSelectComponent } from './selects/unidad-select/unidad-select.component';
import { IndexOrganizacionContainer } from './organizacion/index-organizacion/index-organizacion.container';
import { IndexTemplateComponent } from './templates/index-template/index-template.component';
import { EditOrganizacionComponent } from './organizacion/edit-organizacion/edit-organizacion.component';
import { ClasificacionOrganizacionSelectComponent } from './selects/clasificacion-organizacion-select/clasificacion-organizacion-select.component';
import { GrillaContactosComponent } from './organizacion/grilla-contactos/grilla-contactos.component';
import { ToastrModule } from 'ngx-toastr';
import { CargarMedicionesComponent } from './cargar-mediciones/cargar-mediciones.component';
import { OrganizacionSelectComponent } from './selects/organizacion-select/organizacion-select.component';
import { IndexSectorComponent } from './sector/index-sector/index-sector.component';
import { IndexPersonaComponent } from './persona/index-persona/index-persona.component';
import { TipoDocumentoSelectComponent } from './selects/tipo-documento-select/tipo-documento-select.component';
import { CrearPersonaComponent } from './persona/crear-persona/crear-persona.component';
import { PersonaSelectComponent } from './selects/persona-select/persona-select.component';
import { SectorSelectComponent } from './selects/sector-select/sector-select.component';
import { IndexMiembroContainer } from './miembro/index-miembro/index-miembro.container';
import { CrearMiembroComponent } from './miembro/crear-miembro/crear-miembro.component';
import { RegisterComponent } from './register/register.component';
import { IndexSolicitudComponent } from './solicitud/index-solicitud/index-solicitud.component';
import { IndexMiembroPesadoComponent } from './miembro/index-miembro/index-miembro-pesado/index-miembro-pesado.component';
import { IndexMiembroLivianoComponent } from './miembro/index-miembro/index-miembro-liviano/index-miembro-liviano.component';
import { IndexOrganizacionLivianoComponent } from './organizacion/index-organizacion/index-organizacion-liviano/index-organizacion-liviano.component';
import { IndexOrganizacionPesadoComponent } from './organizacion/index-organizacion/index-organizacion-pesado/index-organizacion-pesado.component';

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
        IndexOrganizacionContainer,
        IndexTemplateComponent,
        EditOrganizacionComponent,
        ClasificacionOrganizacionSelectComponent,
        GrillaContactosComponent,
        CargarMedicionesComponent,
        OrganizacionSelectComponent,
        IndexSectorComponent,
        IndexPersonaComponent,
        TipoDocumentoSelectComponent,
        CrearPersonaComponent,
        PersonaSelectComponent,
        SectorSelectComponent,
        IndexMiembroContainer,
        CrearMiembroComponent,
        RegisterComponent,
        IndexSolicitudComponent,
        IndexMiembroPesadoComponent,
        IndexMiembroLivianoComponent,
        IndexOrganizacionLivianoComponent,
        IndexOrganizacionPesadoComponent,
    ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        NgbModule,
        NgSelectModule,
        FormsModule,
        ReactiveFormsModule,
        HttpClientModule,
        BrowserAnimationsModule,
        ToastrModule.forRoot(),
    ],
    providers: [httpInterceptorProviders],
    bootstrap: [AppComponent],
})
export class AppModule {}
