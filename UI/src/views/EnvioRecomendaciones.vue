<template>

<div class="jumbotron">
  <div class="container text-center">
    <h1>Impacto ambiental - DDS</h1>
    <RouterLink to="/">Inicio</RouterLink>
  </div>
</div>

    <div class="container">
      <h2>Enviar recomendacion</h2>
      <form action="">
        <div class="form-group">
          <label for="email">Ingrese mail de contacto:</label>
          <input
            type="string"
            class="form-control"
            id="mail"
            placeholder="dds@gmail.com"
            name="mail"
            required
          />
        </div>
        <div class="form-group">
          <label for="email">Ingrese telefono de contacto:</label>
          <input
            type="number"
            class="form-control"
            id="telefono"
            placeholder="1122334455"
            name="telefono"
            required
          />
        </div>
        <div class="form-group">
          <label for="email">Seleccione recomendacion:</label>
          <select class="form-control" id="sel1" required>
            <option>recomendacion1</option>
            <option>recomendacion2</option>
            <option>recomendacion3</option>
            <option>recomendacion4</option>
          </select>
        </div>
        <button type="submit" class="btn btn-primary">
          Enviar recomendacion
        </button>
      </form>
    </div>
</template>