<template>
    <div class="jumbotron">
        <div class="container text-center">
            <h1>Impacto ambiental - DDS</h1>
            <RouterLink to="/">Inicio</RouterLink>
        </div>
    </div>

    <section class="registrarse">
        <form method="POST" action="" class="form__registro">
            <h2 class="form__titulo">Registrarse</h2>
            <div class="form__subcontainer">
                <h3 class="form__subtitulo">Nombre de usuario*</h3>
                <input type="text" class="form__input" placeholder="Usuario123" required>
            </div>
            <div class="form__subcontainer">
                <h3 class="form__subtitulo">E-mail*</h3>
                <input type="text" class="form__input" placeholder="hola@ejemplo.com" required>
            </div>
            <div class="form__subcontainer">
                <h3 class="form__subtitulo">Ingrese el e-mail nuevamente*</h3>
                <input type="text" class="form__input" placeholder="hola@ejemplo.com" required>
            </div>
            <div class="form__subcontainer">
                <h3 class="form__subtitulo">Contraseña*</h3>
                <input type="text" class="form__input" placeholder="pepito1234" required>
            </div>
            <div class="form__subcontainer">
                <h3 class="form__subtitulo">Ingrese la contraseña nuevamente*</h3>
                <input type="text" class="form__input" placeholder="pepito1234" required>
            </div>
            
            <input type="submit" value="Registrarse" class="form__boton_envio">

            <h4 class="form__titulo_aclaracion">Los campos marcados con * son obligatorios</h4>
        </form>
    </section>
</template>

<style>
.registrarse {
    width: 100%;
    height: auto;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.form__registro {
    width: 30%;
    height: auto;
    display: flex;
    flex-direction: column;
    align-items: center;
}

.form__titulo {
    width: auto;
    height: auto;
    font-size: 35px;
}

.form__subcontainer {
    width: 90%;
    height: auto;
    margin: 5px 0;
    display: flex;
    flex-direction: column;
}

.form__subtitulo {
    font-size: 15px;
}

.form__input {
    width: 100%;
    height: 35px;
}
</style>