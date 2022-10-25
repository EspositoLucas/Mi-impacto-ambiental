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
      <input type="string" class="form-control" id="mail" name="mail" required>
    </div>
    <div class="form-group">
      <label for="email">Ingrese telefono de contacto:</label>
      <input type="string" class="form-control" id="telefono" name="telefono" required>
    </div>
    <div class="form-group">
      <label for="email">Seleccione recomendacion:</label>
      <input type="file" class="form-control" id="recomendacion" name="recomendacion" required>
    </div>
    <button type="submit" class="btn btn-primary">Enviar recomendacion</button>
  </form>
</div>
</template>