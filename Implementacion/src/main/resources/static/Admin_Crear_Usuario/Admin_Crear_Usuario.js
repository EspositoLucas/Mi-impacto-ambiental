const apiRegistrarAdmin = "http://localhost:5000/usuarios/registrar-admin";
const apiVoluntario = "http://localhost:5000/usuarios/registrar-voluntario";
const apiDatosAdmin = "http://localhost:5000/usuarios/datos-administrador";

new Vue({
	el:"#app",
	data () {
		return {
			rules: [
				{ message: "Requiere un caracter especial", regex:/[@#$/%!_*?¿-]+/ },
				{ message: "Requiere una mayúscula",  regex:/[A-Z]+/ },
				{ message: "Mínimo de 8 caracteres", regex:/.{8,}/ },
				{ message: "Requiere un número", regex:/[0-9]+/ }
			],
			form: {
			    usuario: '',
            	password: '',
            	organizacion: {}
			},
			checkPassword: '',
			submitted: false,
			rol: ''
		}
	},

    created() {
        var idSesion = localStorage.getItem("IDSESION"); //recupera ID

        fetch(apiDatosAdmin, {
            headers: {
            "Authorization": idSesion //se envia el IDSESION para identificar al usuario en backend
        }})
        .then(response =>{
            return response.json()})
        .then(adminObtenido => {
            this.form.organizacion = adminObtenido.organizacion;
        })
    },

	computed: {
		notSamePasswords () {
			return (this.form.password !== this.checkPassword)
		},

		passwordsFilled () {
			return (this.form.password !== '' && this.checkPassword !== '')
		},

		passwordValidation () {
			let errors = []
			for (let condition of this.rules) {
				if (!condition.regex.test(this.form.password)) {
					errors.push(condition.message)
				}
			}
			if (errors.length === 0) {
				return { valid:true, errors }
			} else {
				return { valid:false, errors }
			}
		}
	},

	methods: {
	    enviar: function() {
	        var api;
	        if(this.rol == "ADMINISTRADOR") {
	            api = apiRegistrarAdmin;
	        } else {
	            api = apiVoluntario;
	        }

            if(!this.notSamePasswords && this.passwordsFilled && this.passwordValidation.valid) {
                axios.post(api, this.form)
                .then((result) => {
                	console.log(result);
                    alert("El usuario se ha creado correctamente. Pulse aceptar para volver a la pantalla principal");
                    window.location.href = 'Admin_Pantalla_Principal.html';
                })
                .catch(function (error) {
                  	if(error.response) {
                   		alert("Usuario o constraseña incorrectos");
                   	}
                })
            }
	    },
	    mostrarContraseñas() {
        	let password = document.getElementById("password")
        	let secondPassword = document.getElementById("secondPassword")
        	if(password.type == 'text') {
        	    password.type 		= 'password'
        	    secondPassword.type = 'password'
        	} else {
        	    password.type 		= 'text'
        	    secondPassword.type = 'text'
        	}
        }
	}

})
