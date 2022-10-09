	const apiRegistrarEstandar = "http://localhost:5000/usuarios/registrar-estandar";
	const apiIniciarSesion = "http://localhost:5000/usuarios/iniciar-sesion";

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
				},
				submitted: false,
				status: '',
				checkPassword: ''
			}
		},
		computed: {
			passwordsFilled () {
				return (this.form.password !== '');
			},
			notSamePasswords () {
				return (this.form.password !== this.checkPassword)
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
				if(this.passwordsFilled && this.passwordValidation.valid) {
					axios.post(apiRegistrarEstandar, this.form)
					.then( (response) => {this.iniciarSesion()}
					)
					.catch( (error) => {
						if(error.response) {
							alert("No se creo el usuario. Usuario o constraseña incorrectos");
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
			},
			iniciarSesion() {
				fetch(apiIniciarSesion, {
                	method: "POST",
                    headers: {
                      	'Content-Type': 'application/json'
                    },
                    body: JSON.stringify({
                        usuario: this.form.usuario,
                        password: this.form.password
                    })
                })
                .then(function (response){
                	if(!response.ok){
                    	alert("Hubo un error al iniciar sesión. Usuario o constraseña incorrectos");
                    	throw new Error("Status: " + response.status);
                	}
                   	return response.json();
                })
                .then(data => {
                   	console.log('logged in');
                        localStorage.setItem("IDSESION", data.idSesion); //guarda ID
                        window.location.href = 'Registrar_Datos_Personales.html';
                })
			}
		}
	})