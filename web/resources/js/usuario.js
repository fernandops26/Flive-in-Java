$(document).ready(function(){
    $('.button-collapse').sideNav();
    
    $('select').material_select();
           
     $('.modal-trigger').leanModal({
         dismissible: false
     });
     
    $('.linkToLogin').on('click',function(){
        $('#formSignup').toggleClass('ocultar_formulario');
        $('#formLogin').toggleClass('ocultar_formulario');
    });
    
     $('.linkToSignup').on('click',function(){
        $('#formLogin').toggleClass('ocultar_formulario');
        $('#formSignup').toggleClass('ocultar_formulario');
    });
    
    
    $('#notificacion').on('click',function(){
        $('#notificacion__lista').toggleClass('notificacion__lista--activa');
        $('#opciones__perfil').removeClass('opciones--activo');
    });
    
    $('#perfil').on('click',function(){
        $('#opciones__perfil').toggleClass('opciones--activo');
        $('#notificacion__lista').removeClass('notificacion__lista--activa');
    });
  // Hide sideNav
  
  
    $('.tooltipped').tooltip({delay: 50});
    
   
  
});

 function muestraTags(){
//  var form=document.getElementById("");
          var tags=$("#cajaTags");
                  var items=tags.materialtags('items');
                  var input =document.getElementById('formulario:titulo');
  var total="";
    for (var i = 0; i < items.length; i++) {
        total+=items[i] +" ";
    }
    console.log(total);
    input.value=total;
 }
 
 function pasarTagsInput(){
     var items =$("#cajaTags").materialtags('items');
     var input =document.getElementById('formulario:tagsConcatenados');
     
     var tagsConcat="";
     for (var i = 0; i < items.length; i++) {
        tagsConcat+=items[i] +" ";
    }
    console.log(tagsConcat);
    input.value=tagsConcat;
 }
        