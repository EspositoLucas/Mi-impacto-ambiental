<template>

<div class="jumbotron">
  <div class="container text-center">
    <h1>Impacto ambiental - DDS</h1>
    <RouterLink to="/">Inicio</RouterLink>
  </div>
</div>
<div class="container">
    <h2>Trayectos</h2>
     <form action="">
        <div class="form-group">
          <label for="email">Lugar inicio:</label>
          <select class="form-control" id="sel1" required>
            <option>inicio1</option>
            <option>inicio2</option>
          </select>
        </div>
        <label for="email">Lugar fin:</label>
          <select class="form-control" id="sel2" required>
            <option>fin1</option>
            <option>fin2</option>
          </select>
          <label for="email">Miembros:</label>
          <select class="form-control" id="sel2" required>
            <option>miembro1</option>
            <option>miembro2</option>
          </select>
          <label for="email">Tramos:</label>
          <select class="form-control" id="sel2" required>
            <option>tramo1</option>
            <option>tramo2</option>
          </select>
          <div class="form-group">
              <label for="birthday">Fecha inicio:</label>
              <input type="date" id="inicio" name="inicio" required> 
              <label for="birthday">Fecha fin:</label>
              <input type="date" id="fin" name="fin" required> 
            </div>
            <button type="submit" class="btn btn-primary">
                Registrar trayecto
            </button>
      </form>     
</div>
</template>