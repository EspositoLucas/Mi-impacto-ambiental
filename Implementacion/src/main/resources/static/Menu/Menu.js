class MenuAdmin extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
	        <div class="menu-bar" id="app-sesion">
                <div class="contenedor-base">

                    <nav class="menu">
                        <a href="Admin_Pantalla_Principal.html">Página Principal</a>
                        <a @click=eliminarSesion() href="index.html">Cerrar Sesión</a>
                    </nav>

                    <a href="Admin_Pantalla_Principal.html">
                        <div class="logo-menu">
                            <img src="../static/Menu/img/logo_blanco.png" alt="Logo">
                        </div>
                    </a>

                </div>
            </div>
    `;
    }
}

class MenuUsuario extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
            <div class="menu-bar">
                <div class="contenedor-base" id="app-sesion">

                    <nav class="menu">
                        <a v-if="sesionInvalida" href="Iniciar_Sesion.html">Iniciar Sesión</a>
                        <a v-if="!sesionInvalida" href="Mi_Usuario.html">Mi Usuario</a>
                        <a href="index.html">Página Principal</a>
                        <a href="Organizaciones.html">Organizaciones</a>
                    </nav>

                    <a href="index.html">
                        <div class="logo-menu">
                            <img src="../static/Menu/img/logo_blanco.png" alt="Logo">
                        </div>
                    </a>

                </div>
            </div>
    `;
    }
}

class MenuVoluntario extends HTMLElement {
    connectedCallback() {
        this.innerHTML = `
	        <div class="menu-bar" id="app-sesion">
                <div class="contenedor-base">

                    <nav class="menu">
                        <a href="Voluntario.html">Página Principal</a>
                        <a @click=eliminarSesion() href="index.html">Cerrar Sesión</a>
                    </nav>

                    <a href="Voluntario.html">
                        <div class="logo-menu">
                            <img src="../static/Menu/img/logo_blanco.png" alt="Logo">
                        </div>
                    </a>

                </div>
            </div>
    `;
    }
}

customElements.define('main-menu-admin', MenuAdmin);
customElements.define('main-menu-usuario', MenuUsuario);
