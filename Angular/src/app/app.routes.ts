import { Routes } from '@angular/router';
import { CargarMedicionesComponent } from './cargar-mediciones/cargar-mediciones.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { LogoutComponent } from './logout/logout.component';
import { CrearOrganizacionComponent } from './organizacion/crear-organizacion/crear-organizacion.component';
import { EditOrganizacionComponent } from './organizacion/edit-organizacion/edit-organizacion.component';
import { IndexOrganizacionComponent } from './organizacion/index-organizacion/index-organizacion.component';
import { IndexSectorComponent } from './sector/index-sector/index-sector.component';

export const routes: Routes = [
    {
        path: 'login',
        component: LoginComponent,
    },
    {
        path: 'logout',
        component: LogoutComponent,
    },
    {
        path: 'organizacion',
        component: IndexOrganizacionComponent,
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
        path: '',
        component: HomeComponent,
        pathMatch: 'full',
    },
];
