// Call the dataTables jQuery plugin
$(document).ready(function() {
cargarUsuarios();
  $('#usuarios').DataTable();
  actualizarEmailUsuario();
});

function actualizarEmailUsuario(){
    document.getElementById('txt-email-usuario').outerHTML=localStorage.email;
}

async function cargarUsuarios(){
  const request = await fetch('api/usuarios', {
    method: 'GET',
    headers:getHeaders()
  });
  const usuarios = await request.json();
  console.log(usuarios);
let listHtml='';
for(let usuario of usuarios){
       let botonConsultar='<a href="#" onclick="consultarUsuario(' + usuario.id + ')" class="btn btn-info btn-circle btn-sm"><i class="fas fa-info-circle"></i></a>';
       let botonEliminar='<a href="#" onclick="eliminarUsuario(' + usuario.id + ')" class="btn btn-danger btn-circle btn-sm"><i class="fas fa-trash"></i></a>';
       let usuarioHtml='<tr><td>' + usuario.id + '</td><td>' + usuario.nombre + '</td><td>' + usuario.apellido + '</td><td>' +
           usuario.tipoDocumento + '</td><td>' + usuario.numeroDocumento + '</td><td>'+ usuario.fechaNacimiento +
           '</td><td>' + usuario.fechaVinculacion + '</td><td>' + usuario.cargo + '</td><td>' + usuario.salario + '</td>' +
           '<td>' + botonConsultar + '</td><td>' + botonEliminar + '</td></tr>';
          listHtml+=usuarioHtml;
}
document.querySelector('#usuarios tbody').outerHTML=listHtml;
}

function getHeaders(){
    return{
        'Accept': 'application/json',
        'Content-Type': 'application/json',
        //('Authorization': localStorage.token
    };
}

async function eliminarUsuario(id){
      if(!confirm('¿Desea Eliminar el usuario con id ' + id + '?' )){
        return;
      }
      const request = await fetch('api/usuarios/' + id, {
        method: 'DELETE',
        headers:getHeaders()
      });
      location.reload()
}

async function consultarUsuario(id){
      const request = await fetch('api/consulta/' + id, {
        method: 'GET',
        headers:getHeaders()
      });
        const response = await request.text();
      alert(response)
}