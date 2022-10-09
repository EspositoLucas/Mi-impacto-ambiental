const apiUrlDatosAdmin = "http://localhost:5000/usuarios/datos-administrador";
const apiCaracteristicasGlobales = "http://localhost:5000/api/caracteristicas";
const apiUrlOrganizacion = "http://localhost:5000/api/organizaciones/modificar";
const apiNormalizarFotos = "http://localhost:5000/api/organizaciones/normalizar-fotos";

new Vue({
	el: '#app',
    data: {
      	administrador: {
      		organizacion: {
      			id: null,
      			caracPropias: [],
      			calidad: null,
      			resolucion: {
      				pixelesAlto: null,
      				pixelesAncho: null
      			}
      		}
      	},
        caracteristicasGlobales: [],
        caracteristicasMarcadas: [],
        organizacion2: {}
    },
	methods: {
		laCaracteristicaEstaEnLaLista(unaCaracteristica, unaLista) {
			for (let i = 0; i < unaLista.length; i++) {
				if(unaCaracteristica.id == unaLista[i].id) {
					return true
				}
			}
			return false
		},
		sacarCaracteristicaDeLaLista(caracteristica, listaDeCaracteristicas) {
			this.caracteristicasMarcadas = listaDeCaracteristicas.filter(function(unaCaracteristica) { return unaCaracteristica.id != caracteristica.id})
		},
		agregarOSacarDeLista(caracteristica) {
			if(this.laCaracteristicaEstaEnLaLista(caracteristica, this.caracteristicasMarcadas)){
				this.sacarCaracteristicaDeLaLista(caracteristica, this.caracteristicasMarcadas)
			}else{
				this.caracteristicasMarcadas.push(caracteristica)
			}

		},
		laCaracteristicaEstaEnLaOrg(caracteristica) {
			return this.laCaracteristicaEstaEnLaLista(caracteristica, this.administrador.organizacion.caracPropias)
		},
		agregarLasMarcadasALaLista() {
			for(let j=0; j<this.caracteristicasGlobales.length; j++) {
            	if(this.laCaracteristicaEstaEnLaLista(this.caracteristicasGlobales[j], this.administrador.organizacion.caracPropias)) {
            		this.caracteristicasMarcadas.push(this.caracteristicasGlobales[j])
            	}
         	}
		},
		obtenerCalidadElegida() {
			let calidad = document.getElementById("calidad")
			this.organizacion2.calidad = calidad.value
		},
		obtenerResolucionElegida() {
			let pixelesAlto = document.getElementById("pixelesAlto")
			this.organizacion2.resolucion.pixelesAlto = pixelesAlto.value

            let pixelesAncho = document.getElementById("pixelesAncho")
            this.organizacion2.resolucion.pixelesAncho = pixelesAncho.value
		},
		losDatosSonValidos() {
			return this.organizacion2.resolucion.pixelesAlto > 0 && this.organizacion2.resolucion.pixelesAncho > 0
		},
		guardarCambios() {

			this.organizacion2 = this.administrador.organizacion
			this.obtenerCalidadElegida()
			this.obtenerResolucionElegida()
			this.organizacion2.caracPropias = this.caracteristicasMarcadas

			if(this.losDatosSonValidos()){
				axios.post(apiUrlOrganizacion, this.organizacion2).then((result) => {console.log(result);})
				this.caracteristicasMarcadas = []
				window.location.href = 'Admin_Pantalla_Principal.html';
			}else{
				alert("Los pixeles de alto y de ancho deben ser mayores que 0")
			}

		},
		normalizarFotos() {
			var idSesion = localStorage.getItem("IDSESION");
			fetch(apiNormalizarFotos, {
				headers: {
					"Authorization": idSesion
				}
			})
			.then(function(response) {
                if(response.ok) {
                  	alert("¡Las fotos se normalizaron con éxito!")
                }})
			.catch(function (error) {
                if (error.response.status == 400) {
                	alert("El servidor no pudo procesar la solicitud. Inténtelo de nuevo")
                }
            });
		}
	},
    created() {
        var idSesion = localStorage.getItem("IDSESION"); //recupera ID

        fetch(apiUrlDatosAdmin, {
            headers: {
               "Authorization": idSesion //se envia el IDSESION para identificar al usuario en backend
                }})
                .then(response => response.json())
                .then(adminObtenido => { this.administrador = adminObtenido; })
            	.then(
            		fetch(apiCaracteristicasGlobales)   //traigo todas las caracteristicas porque el admin puede agregar las que quiera
                		.then(response => response.json())
                    	.then(caracObtenidas => {
                    		this.caracteristicasGlobales = caracObtenidas;
                  	   		this.agregarLasMarcadasALaLista();
                    	})
            	)


        }
})