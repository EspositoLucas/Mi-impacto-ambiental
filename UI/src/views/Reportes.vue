<template>
    <div class="jumbotron">
        <div class="container text-center">
            <h1>Impacto ambiental - DDS</h1>
            <RouterLink to="/">Inicio</RouterLink>
        </div>
    </div>

    <h2 id="titulo" class="text-center">Reportes</h2>
        <div class="border border-secondary position-relative top-50 start-50 translate-middle-x" id="Body" >
           
            <div class="d-flex justify-content-center  " >
                <div class="position-relative me-auto p-2  w-25 justify-content-center ">
                    <button  type="button" class="btn btn-primary d-block " id="boton">Reportes hc por sector territorial</button>
                    <button  type="button" class="btn btn-primary d-block " id="boton">Reportes composicion de sector territorial</button> 
                    <button  type="button" class="btn btn-primary d-block " id="boton">Reportes hc por clasificacion organizacion</button> 
                </div>   
                 
            <div class=" w-100 " id="lista">         
                                <ul class="list-group">
                                    <li class="list-group-item" >
                                        item
                                    </li>
                                </ul> 
                        </div>  
            </div>
        </div>
</template>