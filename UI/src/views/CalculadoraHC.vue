<template>

  <div class="jumbotron">
    <div class="container text-center">
      <h1>Impacto ambiental - DDS</h1>
      <RouterLink to="/">Inicio</RouterLink>
    </div>
  </div>
  
<div class="container">
  <h2>Calculadora HC</h2>
  <form action="">
    <div class="form-group">
      <label for="email">Ingrese variable de calculo:</label>
      <input type="string" class="form-control" id="variable" placeholder="FE" name="variable" required>
    </div>
    <div class="form-group">
      <label for="email">Ingrese periodicidad:</label>
          <select class="form-control" id="sel1" required>
            <option>Anual</option>
            <option>Mensual</option>
          </select>
    </div>
    <div class="form-group">
      <label for="birthday">Inicio:</label>
      <input type="date" id="inicio" name="inicio" required> 
      <label for="birthday">Fin:</label>
      <input type="date" id="fin" name="fin" required> 
    </div>
    <div class="form-group">
        <label for="email">Ingrese valor:</label>
        <input type="number" class="form-control" id="valor" placeholder="10" name="valor" required>
      </div>
    <button type="submit" class="btn btn-primary">Calcular</button>
  </form>
</div>
</template>