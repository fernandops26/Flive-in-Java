$(document).ready(function(){
    $('.button-collapse').sideNav();
    
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
  
  
  $(document).ready(function(){
    $('.tooltipped').tooltip({delay: 50});
  });
        
  
});
