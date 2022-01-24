// Call the dataTables jQuery plugin
$(document).ready(function() {
    //on ready
});

async function registrarUsuarios(){
    let datos={};
    let verificar
    datos.nombre=document.getElementById('txtNombre').value
    datos.apellido=document.getElementById('txtApellido').value
    datos.tipoDocumento=document.getElementById('txtTipoDocumento').value
    datos.numeroDocumento=document.getElementById('txtNumeroDocumento').value
    datos.fechaNacimiento=document.getElementById('txtFechaNacimiento').value
    datos.fechaVinculacion=document.getElementById('txtFechaVinculacion').value
    datos.cargo=document.getElementById('txtCargo').value
    datos.salario=document.getElementById('txtSalario').value

    const request = await fetch('api/registro', {
            method: 'POST',
            headers: {
              'Accept': 'application/json',
              'Content-Type': 'application/json'
            },
            body: JSON.stringify(datos)
          });
    const response = await request.text();
    if(response=='OK'){
        alert("usuario ingresado con exito")
    }else if (response=='errorDate'){
        alert("las fechas ingresadas no estan correctas verifiquelas por favor")
    }else if (response=="errorNacimiento"){
        alert("el empleado es menor de edad, verifique la fecha por favor")
    }else if (response=="errorVinculacion"){
        alert("la fecha de nacimiento, no puede ser mayor a la de vinculación")
    }else if(response=='FAIL'){
        alert("Todos los campos son obligatorios, verifique la informacion diligenciada por favor")
    }
}

