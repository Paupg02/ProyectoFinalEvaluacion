# ProyectoFinalEvaluacion
Idea:
  -
1. App de una tienda de ropa.
2. [Splash e icono.](https://github.com/Paupg02/ProyectoFinalEvaluacion/edit/main/README.md#splash-e-icono)
3. [Registro con Google.](https://github.com/Paupg02/ProyectoFinalEvaluacion/edit/main/README.md#registro-con-google)
4. [Pantalla inicial con imagen de la tienda y fragment con imágenes para cambiar de activity.](https://github.com/Paupg02/ProyectoFinalEvaluacion/edit/main/README.md#pantalla-inicial)
5. [Localización con Google maps, con marcador y área en color para área de reparto.](https://github.com/Paupg02/ProyectoFinalEvaluacion/edit/main/README.md#google-maps)
6. [Activity con barra de búsqueda, recycler con los productos.](https://github.com/Paupg02/ProyectoFinalEvaluacion/edit/main/README.md#productos)
7. Fragment para elegir los detalles del producto seleccionado y poder añadirlo al pedido.
8. En los detalles del producto se verá un pequeño video del mismo.
9. Al terminar el pedido pasaremos a otro activity en el que aparecerán los productos elegidos y podremos modificarlos o borrarlos. 
10. Guardamos en firebase el pedido.
11. Consultamos los que están sin recibir y para confirmarlos habrá que echar una foto. 

***
Creo un proyecto en `Android Studio` para poder empezar.

Durante todo el proyeto he ido haciendo el hard code creando un `strings.xml` para el Español aparte del que viene de default que lo use para el Ingles.

![HadrCode](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/HadrCode.png)
***
Splash e icono:
 -
 1. Icono: 
    - Para crear el icono primero guardo la imagen que quiero que sea mi icono en el direcctorio `res\drawable` despues creo en la carpeta `res` una `Image Assert` en la que elijo la imagen guardada anteriormente, la ajusto para que se vea bien y le pongo un color de fondo.
     
    ![Icono](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/icono.png)

    - Al guardarlo no se borran todos los "restos" del icono de dafault asi que hay que borralos a mano.

 2. Splash:
    - Para crear el splash lo primero es crear en el direcctorio `res\drawable` un `Drawable Resource File` le poneos un nombre y modificamos el archivo para que tenga el color de fondo que se quiera y la imagen del icono.

    ![SplashXML](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/SplashXML.png)
    
    - Despues vamos a `res\values\themes.xml` y añadimos un `style` nuevo en el que mostremos el `xml` creado antes.

    ![SplashThemes](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/SplashThemes.png)

    -  A continuación en `java\com\example\proyectofinalevaluacin_paulaplazaguirado\View` creo un nuevo `Activity` sin `layout` en la que hago un intent al `MainActivity`.

    ![SplashActivity](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/SplashActivity.png)

    -  Y por ultimo modificamos el `manifest` para que se ejecute este `Activity` recien creado nada más lanzar la app y el tema creado anteriormente.

    ![SplashManifest](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/SplashManifest.png)
 
*** 
Registro con Google:
 -
  1. Primero hago el layout del `MainActivity`.
  
        ![RegistroLayout](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/RegistroLayout.png)

  2. Despues con `Tools\Firebase` conecto el proyecto con mi Firebase y añado las dependencias para poder logearme con Google.

        ![RegistroDependencias](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/RegistroDependencias.png)

  3. Luego hago un `gradle signingReport` para sacar la clave SHA-1 de mi ordenador y añadrilo en la configuracion de mi proyecto en firebase.

        ![RegistroGradle](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/RegistroGradle.png)

  4. En `java\com\example\proyectofinalevaluacin_paulaplazaguirado\Model` creo la clase `Prefs` para controlar los inicio de sesion.

        ![RegistroPrefs](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/RegistroPrefs.png)

  5. Por ultimo modifico el `MainActivity` para poder hacer el login con ayuda de `Tools\Firebase`.

        ![RegistroActivity](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/RegistroActivity.png)

***
Pantalla inicial:
  -
1. Creo un nuevo `Activity` para la pantalla despues del `login`.

    ![InicioLayout](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/InicioLayout.png)

2. Creo un `Fragment` para el menu de la app en el que voy a tener 4 imagenes descargadas anteriormente, una para ir al mapa, una para mostrar un recycler con los productos, una para mostrar un recycler con los pedidos y la ultima para cerrar sesion en la app.

    ![Menu](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/Menu.png)

3. Creo los `Activitys` necesarios para las opciones de la app y modifico el primer `Activity` para guardar las preferencias y darle funcionalidad al `Fragment`.

    ![InicioActivity](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/InicioActivity.png)

***
Google maps:
  -
  1. Usando el `Activity` creado antes para el mapa de Google modificamos el layout para que tenga un titulo, el mapa de Google y el `Fragment` de menú.
  
        ![MapaLayout](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/MapaLayout.png)

  2. Modifico el `Activity` del mapa para pedir los permisos de localizacion, colocar un marcador de donde está la tienda, que haga un zoom a este y que pinte un circulo desde el marcador para señalar el area de reparto.
  
        ![MapaActivity](https://github.com/Paupg02/ProyectoFinalEvaluacion/blob/main/MapaActivity.png)
***
Productos 
-
1. Creo toda la estructura para una base de datos `SQLite` con `ROOM` empezando por la `DataClass`, el `DAO`, y la `DataBase`, despues el `Repository`, el `ViewModel` y el `ViewModelFactory` y por ultimo un `Event` para mostrar mensajes.
2. Creo un layout  con un `CardView` para la vista de cada objeto del recycler.
3. Creo la clase `RecyclerAdapter` y `ViewHolder` para mostrar los registros del recycler.

***