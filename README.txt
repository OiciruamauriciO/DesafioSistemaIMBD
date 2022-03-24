Importante: 
La aplicación debe considerar código tal, que exista una inicialización para los valores ROLE_ADMIN y ROLE_USER, antes de cualquier uso sobre ella, es decir, la 
tabla ROLE del modelo en base de datos, debe tener antes de cualquier actividad sobre ella, definidos los campos role_id y role_name con 1 - USER y 2 - ADMIN. 
Solo para el ejercicio de esta aplicación se ha considerado que cada usuario nuevo que se crea, tiene el rol user por defecto, aunque exista la implementación 
en el servicio para definir o ingresar a un usuario con el rol administrador. Dichos campos deben darse con un commit, para que quede permanentemente en la bd. 
Para fines de esta prueba, se considera que no hay problema, si dichos insert ocurren en el motor de la base de datos directamente, desde un principio. 