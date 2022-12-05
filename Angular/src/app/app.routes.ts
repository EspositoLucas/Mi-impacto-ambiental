import { Routes } from '@angular/router';
import { CargarMedicionesComponent } from './cargar-mediciones/cargar-mediciones.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { CrearMiembroComponent } from './miembro/crear-miembro/crear-miembro.component';
import { IndexMiembroContainer } from './miembro/index-miembro/index-miembro.container';
import { CrearOrganizacionComponent } from './organizacion/crear-organizacion/crear-organizacion.component';
import { EditOrganizacionComponent } from './organizacion/edit-organizacion/edit-organizacion.component';
import { IndexOrganizacionContainer } from './organizacion/index-organizacion/index-organizacion.container';
import { CrearPersonaComponent } from './persona/crear-persona/crear-persona.component';
import { IndexPersonaComponent } from './persona/index-persona/index-persona.component';
import { RegisterComponent } from './register/register.component';
import { IndexSectorComponent } from './sector/index-sector/index-sector.component';
import { IndexSolicitudComponent } from './solicitud/index-solicitud/index-solicitud.component';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent,
    },
    {
        path: 'register',
        component: RegisterComponent,
    },
    {
        path: 'logout',
        component: LogoutComponent,
    },
    {
        path: 'organizacion',
        component: IndexOrganizacionContainer,
    },
    {
        path: 'organizacion/crear',
        component: CrearOrganizacionComponent,
    },
    {
        path: 'organizacion/edit/:id',
        component: EditOrganizacionComponent,
    },
    {
        path: 'medicion',
        component: CargarMedicionesComponent,
    },
    {
        path: 'sector',
        component: IndexSectorComponent,
    },
    {
        path: 'persona',
        component: IndexPersonaComponent,
    },
    {
        path: 'persona/crear',
        component: CrearPersonaComponent,
    },
    {
        path: 'miembro',
        component: IndexMiembroContainer,
    },
    {
        path: 'miembro/crear',
        component: CrearMiembroComponent,
    },
    {
        path: 'solicitud',
        component: IndexSolicitudComponent,
    },
    {
        path: '',
        component: HomeComponent,
        pathMatch: 'full',
    },
];
