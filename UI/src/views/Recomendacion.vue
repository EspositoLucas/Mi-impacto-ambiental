<template>
      <div class="jumbotron">
    <div class="container text-center">
      <h1>Impacto ambiental - DDS</h1>
      <RouterLink to="/">Inicio</RouterLink>
    </div>
  </div>
  
<div class="container">
  <h2>Cargar recomendacion</h2>
  <form action="">
    <div class="form-group">
      <label for="email">Ingrese recomendacion:</label>
      <input type="string" class="form-control" id="recomendacion" name="recomendacion" required>
    </div>
    <button type="submit" class="btn btn-primary">Cargar medicion</button>
  </form>
</div>
</template>