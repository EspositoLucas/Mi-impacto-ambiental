const apiAgregarDatosPersonales = "http://localhost:5000/usuarios/datos-estandar/actualizar";

new Vue({
    el: '#app',
    data: {
        duenio: {
            nombre: '',
            apellido: '',
            telefono: '',
            email: '',
            formasNotificacion1: [],
            formasNotificacion: '',
            fechaDeNacimiento: '',
            tipoDocumento: '',
            nroDocumento: '',
            domicilio: '',
            otrosContactos: [{
                nombre: '',
                apellido: '',
                telefono: '',
                email: '',
                formasNotificacion1: [],
                formasNotificacion: '',
                errorContacto: []
            }],
        },
        errorsDuenio: []
    },

    methods: {
        enviar() {
            if(this.validarDatos()) {
                this.duenio.formasNotificacion = (this.duenio.formasNotificacion1).join(', ');

                for (let i = 0; i < this.duenio.otrosContactos.length; i++) {
                    this.duenio.otrosContactos[i].formasNotificacion = (this.duenio.otrosContactos[i].formasNotificacion1).join(', ');
                }

                console.log(this.duenio);

                var idSesion = localStorage.getItem("IDSESION");
                fetch(apiAgregarDatosPersonales, {
                    method: "POST",
                    headers: {
                        'Content-Type': 'application/json',
                        "Authorization": idSesion
                    },
                    body: JSON.stringify(this.duenio)
                })
                    //.then(response => response.json())
                    .then(data => {
                        console.log('status: ', data.status);

                        switch (data.status) {
                            case 201:
                                alert("¡Se han guardado sus datos personales correctamente! Pulse aceptar para volver a la pantalla principal");
                                window.location.href = 'index.html';
                                break;
                            default:
                                console.log('error');
                                break;
                        }
                    })
            }

        },

        addContacto() {
            this.duenio.otrosContactos.push({
                                    nombre: '',
                                    apellido: '',
                                    telefono: '',
                                    email: '',
                                    formasNotificacion1: [],
                                    formasNotificacion: '',
                                    errorContacto: []
            })
        },

        deleteContacto(counter) {
              this.duenio.otrosContactos.splice(counter,1);
        },

        validarDatos() {

            this.losContactosSonValidos(); // los pongo asi pq sino el if corta cuando encuetra un falso
            this.elDuenioEsValido();
            return this.losContactosSonValidos() && this.elDuenioEsValido()

        },

        validEmail: function (email) {
            var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
            return re.test(email);
        },

        validTel: function (telefono) {
            var re = /^\+5411([0-9]){8}$/
            return re.test(telefono);
        },

        elDuenioEsValido: function() {
            let esValido = true
            this.errorsDuenio = [] // para que no se acumulen los errores

            if(this.duenio.nombre == '' || this.duenio.apellido == '') {
                this.errorsDuenio.push("Debes escribir tu nombre completo.");
                esValido = false
            }


            if(!this.validTel(this.duenio.telefono) ) {
                this.errorsDuenio.push("El teléfono es invalido, debe comenzar con +5411 y luego 8 digitos");
                esValido = false
            }

            if(this.duenio.email == '') {
                this.errorsDuenio.push("El correo electrónico es obligatorio.");
                esValido = false
            }else if(!this.validEmail(this.duenio.email)) {
                this.errorsDuenio.push("El email que escribiste no es válido. Revisa la dirección.");
                esValido = false
            }

            if(this.duenio.tipoDocumento == '' || this.duenio.nroDocumento == '') {
                this.errorsDuenio.push("El tipo y número de documento son obligatorios.");
                esValido = false
            }

            if(this.duenio.domicilio == '') {
                this.errorsDuenio.push("El domicilio es obligatorio.");
                esValido = false
            }

            if(this.duenio.formasNotificacion1 == '') {
                this.errorsDuenio.push("Debes elegir como mínimo una forma de notificación.");
                esValido = false
            }

            if(this.duenio.fechaDeNacimiento == '') {
                this.errorsDuenio.push("La fecha de nacimiento es obligatoria.");
                esValido = false
            }
            return esValido;
        },

        losContactosSonValidos: function() {
            let esValido = true
            for(let i = 0; i < this.duenio.otrosContactos.length; i++) {
                this.duenio.otrosContactos[i].errorContacto = [];

                if(this.duenio.otrosContactos[i].nombre == '' || this.duenio.otrosContactos[i].apellido == '') {
                    this.duenio.otrosContactos[i].errorContacto.push("Debes escribir su nombre completo.");
                    esValido = false
                }

                if(!this.validTel(this.duenio.otrosContactos[i].telefono) ) {
                    this.duenio.otrosContactos[i].errorContacto.push("El teléfono es invalido, debe comenzar con +5411 y luego 8 digitos");
                    esValido = false
                }

                if(this.duenio.otrosContactos[i].email == '') {
                    this.duenio.otrosContactos[i].errorContacto.push("El correo electrónico es obligatorio.");
                    esValido = false
                }else if(!this.validEmail(this.duenio.otrosContactos[i].email)) {
                    this.duenio.otrosContactos[i].errorContacto.push("El email que escribiste no es válido. Revisa la dirección.");
                    esValido = false
                }

                if(this.duenio.otrosContactos[i].formasNotificacion1 == '') {
                    this.duenio.otrosContactos[i].errorContacto.push("Debes elegir como mínimo una forma de notificación.");
                    esValido = false
                }
            }
            return esValido;
        },

        lasMascotasSonValidas: function() {
            let esValido = true
            for(let i = 0; i < this.duenio.mascotas.length; i++) {
                this.duenio.mascotas[i].errorMascota = [];

                if(this.duenio.mascotas[i].nombre == '' || this.duenio.mascotas[i].apodo == '') {
                    this.duenio.mascotas[i].errorMascota.push("Debes escribir su nombre y apodo.");
                    esValido = false
                }

                if(this.duenio.mascotas[i].edad == '') {
                    this.duenio.mascotas[i].errorMascota.push("La edad es obligatoria.");
                    esValido = false
                }else if(!this.validAge(this.duenio.mascotas[i].edad)) {
                    this.duenio.mascotas[i].errorMascota.push("La edad debe ser un valor entre 1 y 30.");
                    esValido = false
                }

                if(this.duenio.mascotas[i].descripcion == '') {
                    this.duenio.mascotas[i].errorMascota.push("La descripción es obligatoria.");
                    esValido = false
                }

                if(this.duenio.mascotas[i].tipo == '' || this.duenio.mascotas[i].sexo == '') {
                    this.duenio.mascotas[i].errorMascota.push("El tipo y sexo son obligatorios.");
                    esValido = false
                }

                if(this.duenio.mascotas[i].fotos.length == 0) {
                    this.duenio.mascotas[i].errorMascota.push("Debes subir al menos una foto de la mascota.");
                    esValido = false
                }

                if(this.duenio.mascotas[i].organizacion.nombre == null) {
                    this.duenio.mascotas[i].errorMascota.push("Debes elegir una organización.");
                    esValido = false
                }

                if(this.duenio.mascotas[i].caracteristicas.length == 0) {
                    this.duenio.mascotas[i].errorMascota.push("Debes seleccionar al menos una característica.");
                    esValido = false
                }

            }
            return esValido;
        }
    }
})