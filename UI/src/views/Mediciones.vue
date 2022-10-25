<template>
  <div class="jumbotron">
    <div class="container text-center">
      <h1>Impacto ambiental - DDS</h1>
      <RouterLink to="/">Inicio</RouterLink>
    </div>
  </div>
  
<div class="container">
  <h2>Mediciones</h2>
  <form action="">
    <div class="form-group">
      <label for="email">Ingrese Id de la organizacion:</label>
      <select type="number" class="form-control" id="idOrganizaion" placeholder="1234" name="idOrganizaion" required>
        <option value="0000">0000</option>
        </select>
    </div>
    <button type="submit" class="btn btn-primary">Cargar medicion</button>
  </form>
</div>
</template>