import { createRouter, createWebHistory } from 'vue-router'
import Home from "../views/Home.vue"
import Recomendaciones from "../views/Recomendaciones.vue"
import CalculadoraHC from "../views/CalculadoraHC.vue"
import Contacto from "../views/Contacto.vue"
import EnvioRecomendaciones from "../views/EnvioRecomendaciones.vue"
import Recomendacion from "../views/Recomendacion.vue"
import Reportes from "../views/Reportes.vue"
import Trayectos from "../views/Trayectos.vue"
import Mediciones from "../views/Mediciones.vue"
import Vinculaciones from "../views/Vinculacion.vue"
import Inicio from "../views/IniciarSesion.vue"
import Registrarse from "../views/Registrarse.vue"

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: Home
    },
    {
      path: '/recomendaciones',
      name: 'recomendaciones',
      component: Recomendaciones
    },
    {
      path: '/calculadoraHC',
      name: 'calculadoraHC',
      component: CalculadoraHC
    },
    {
      path: '/recomendaciones',
      name: 'recomendaciones',
      component: Recomendaciones
    },
    {
      path: '/contacto',
      name: 'contacto',
      component: Contacto
    },
    {
      path: '/envioRecomendaciones',
      name: 'envioRecomendaciones',
      component: EnvioRecomendaciones
    },
    {
      path: '/mediciones',
      name: 'mediciones',
      component: Mediciones
    },
    {
      path: '/recomendacion',
      name: 'recomendacion',
      component: Recomendacion
    },
    {
      path: '/reportes',
      name: 'reportes',
      component: Reportes
    },
    {
      path: '/trayectos',
      name: 'trayectos',
      component: Trayectos
    },
    {path: '/vinculaciones',
    name: 'vinculaciones',
    component: Vinculaciones
  },
  {path: '/iniciar_sesion',
  name: 'iniciar_sesion',
  component: Inicio
  },
  {
    path: '/registrarse',
  name: 'registrarse',
  component: Registrarse
  }

  ]
})

export default router
