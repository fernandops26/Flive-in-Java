$(document).ready(function(){
    $('.button-collapse').sideNav();
    
    $('select').material_select();
           
     $('.modal-trigger').leanModal({
         dismissible: false
     });
     
     $('.btElminarAlbum').on('click',function(){
         $('#modalElimAlbum').fadeToggle();
     });
     
     $('.collapsible').collapsible({
      accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
    });
    
    $('.cbCategoria').material_select();
    
     $('.materialboxed').materialbox();
     
     
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
    


$('.modal-trigger').on('click',function(){
    setTimeout(function(){
        activarModales();
    },500);
});

    
    
    $(window).load(function(){  
            
        var $container = $('#listaPubEnPerfil');
        if($container!=null){
            $container.masonry({
              columnWidth: '.itemPubEnPerfil',
              itemSelector: '.itemPubEnPerfil'
            });
       }
    });
    
    
    $("#busqueda__contenedor").find('.n-tag').keyup(function(e){
        if (e.keyCode == 13) {
            console.log("Enter");
            var items =$("#busqueda").materialtags('items');
            var tagsConcat="";
            for (var i = 0; i < items.length; i++) {
               tagsConcat+=items[i] +" ";
            }
            setTimeout(function(){
                window.location.href = '/Flive/user/busqueda.xhtml?consulta='+tagsConcat; 
            
            },1);
           
            
        }else{
            var escribiendo=$(this).val();
            var input =document.getElementById('formBusqueda:inputBusqHidden');
            input.value=escribiendo;
            setTimeout(function(){
                $(input).trigger('change');
            },1);
            
//            $("#inputBusqHidden").val($(this).val());
        }
    });

});

function cargarMasonry(){
    setTimeout(function(){
        var $container = $('#listaPubEnPerfil');
        if($container){
            $container.imagesLoaded(function(){
            $container.masonry({
              columnWidth: '.itemPubEnPerfil',
              itemSelector: '.itemPubEnPerfil'
            });
        });
       }
    },300);
    
}

function cerrarModal(obj,nombreModal){
    var actual=$(obj);
    var modal=actual.parents(nombreModal)
    modal.closeModal();
    activarModales();
}

function abrirModal(modal){
    $(modal).openModal();
}


function activarModales(){
    setTimeout(function(){
        $('.modal-trigger').leanModal({
         dismissible: false
     });
     
      $('select').material_select();
      
      $('.collapsible').collapsible({
      accordion : false // A setting that changes the collapsible behavior to expandable instead of the default accordion style
    });
    },500);
    
     
}
function asignarRemovedoresClase(){
     $('.modal').removeClass("ui-dialog");
   $('.modal').removeClass("ui-widget");
   $('.modal').removeClass("ui-widget-content");
   $('.modal').removeClass("ui-corner-all");
   $('.modal').removeClass("ui-shadow");
   $('.modal').removeClass("ui-hidden-container");
   $('.modal').removeClass("ui-draggable");
   $('.modal').removeClass("ui-resizable");
   $('.modal').removeAttr("style");
   $('.modal').find("select").removeAttr("class");
   $('.modal').find("select").addClass("browser-default");
   
   $('.modal').addClass('modal');
//   $('.modal').remo
}

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
 
 
 function pasarTagsInputSearch(){
     var items =$("#busqueda").materialtags('items');
     var input =document.getElementById('#busqueda');
     
     var tagsConcat="";
     for (var i = 0; i < items.length; i++) {
        tagsConcat+=items[i] +" ";
    }
    
    console.log(tagsConcat);
 }
 
 function press(){
     console.log("asd");
 }
 
 

        