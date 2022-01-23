// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on ready
});

async function registrarUsuarios(){
    let datos={};
    datos.nombre=document.getElementById('txtFirstName').value
    datos.apellido=document.getElementById('txtLastName').value
    datos.email=document.getElementById('txtEmail').value
    datos.password=document.getElementById('txtPassword').value
    datos.telefono=document.getElementById('txtPhone').value

    let repetirPassword=document.getElementById('txtConfirmPassword').value
    if(repetirPassword!=datos.password){
        alert('la contraseña es diferente')
        return;
    }
  const request = await fetch('api/usuarios', {
    method: 'POST',
    headers: {
      'Accept': 'application/json',
      'Content-Type': 'application/json'
    },
    body: JSON.stringify(datos)
  });
  alert("la cuenta fue creada con exito")
  window.location.href='login.html'
}

