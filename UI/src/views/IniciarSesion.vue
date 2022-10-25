<script lang="ts"> 
import { RouterLink, RouterView } from 'vue-router'
</script>
<template>
    <div class="jumbotron">
        <div class="container text-center">
            <h1>Impacto ambiental - DDS</h1>
            <RouterLink to="/">Inicio</RouterLink>
        </div>
    </div>

    <section class="iniciar_sesion">
        <form action="" class="form__iniciar_sesion">
            <h2 class="form__titulo">Iniciar sesión</h2>
            <div class="form__subcontainer">
                <h3 class="form__subtitulo">E-mail</h3>
                <input type="text" class="form__input" placeholder="hola@ejemplo.com" required>
            </div>
            <div class="form__subcontainer">
                <h3 class="form__subtitulo">Contraseña</h3>
                <input type="text" class="form__input" placeholder="pepito1234" required>
            </div>
            <input type="submit" value="Iniciar sesión" class="form__boton_envio">
            <div class="form__subcontainer2">
                <h4 class="subcontainer2__titulo" >¿No tiene una cuenta?</h4>
                <RouterLink class="form__boton_registrarse" to="registrarse">Registrarse</RouterLink>
            </div>
        </form>
    </section>
</template>

<style>
.iniciar_sesion {
    width: 100%;
    height: auto;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.form__iniciar_sesion {
    width: 20%;
    height: auto;
    display: flex;
    flex-direction: column;
    align-items: center;
    padding: 10px;
    background-color: rgb(245, 245, 245);
}

.form__titulo {
    width: 100%;
    height: auto;
    text-align: center;
    font-size: 35px;
}

.form__subcontainer {
    width: 100%;
    height: auto;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.form__subtitulo {
    width: auto;
    height: auto;
}

.form__input {
    width: 95%;
    height: 30px;
    padding-left: 5px;
    border: none;
    font-size: 12px;
}

.form__boton_envio {
    width: 110px;
    height: 40px;
    border-radius: 5px;
    cursor: pointer;
    margin-top: 30px;
    border: none;
    color: #fff;
    font-weight: bold;
    background-color: rgb(0, 153, 255);
}

.form__boton_envio:hover {
    transform: scale(1.03);
    transition: 0.1s;
}

.form__subcontainer2 {
    width: 90%;
    height: auto;
    margin-top: 10px;
    display: flex;
    justify-content: space-between;
}

.subcontainer2__titulo {
    width: auto;
    font-size: 12px;
}

.form__boton_registrarse {
    border: none;
    background-color: transparent;
    text-decoration: underline;
    cursor: pointer;
}

</style>