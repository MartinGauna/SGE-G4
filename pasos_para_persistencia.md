##Lista de pasos para la persistencia en entrega 3.

1. Modificar en **src/main/resources/META-INF/persistence.xml** los datos de la base que tenga cada uno configurada.

        <property name="hibernate.archive.autodetection" value="class"/>
        <property name="hibernate.connection.driver_class" value="com.mysql.jdbc.Driver" />
        <property name="hibernate.connection.url" value="jdbc:mysql://localhost:3306/sge" />
        <property name="hibernate.connection.username" value="root" />
        <property name="hibernate.connection.password" value="root" />

1. Verificar en persistence.xml que las clases que tienen que persistirse estén agregadas
	    
	    <class>ar.edu.utn.frba.dds.Usuario</class>
        <class>ar.edu.utn.frba.dds.Cliente</class>
        <class>ar.edu.utn.frba.dds.Administrador</class>
        <class>ar.edu.utn.frba.dds.Categoria</class>
        <class>ar.edu.utn.frba.dds.Transformador</class>
        <class>ar.edu.utn.frba.dds.Zona</class>

1. Crear el schema desde MySQL Workbench:	

    	CREATE DATABASE IF NOT EXISTS SGE;
	    use SGE;
	
	
-  Desde Intellij tambien se puede agregar la base que creamos como data source para ir viendo el detalle de las tablas

    [creacion de data sources en Intellij](https://www.jetbrains.com/help/idea/managing-data-sources.html#d1262242e18746)




---


###Contenido de src/main/resources/db:

*IMPORTANTE: A medida que se persistan nuevas clases **atualicen los scripts** agregado las tablas nuevas*

- **0-drop-tables.sql**: para hacer drop del schema y tablas a usar 
- **1-create-tables.sql**: export de las tablas generadas por Hibernate 
(si bien se puede correr a mano, no es necesario ya que al correrse cualquier test se generan las tablas automaticamente).
- **DER.png**: DER de las tablas actuales.


---


#####Notas: 

-  Al correr cualquiera de los test de persistencia, si las tablas no se encuentran creadas, se generan y ejecutan automaticamente los scripts de creación de tablas al momento de la ejecución del test
-  Si se corre el mismo Test mas de una ves y no se hace una limpieza de las tablas usadas, seguramente el mismo falle por violacion de constraints (como por ejemplo la constraint de UNIQUE en el campo numeroDoc de la clase Cliente)
    - Para facilitar la ejecucion de la misma prueba en **src/main/resources/db/0-drop-tables.sql**
- para generar DER (desde MySQL Workbench y teniendo el schema creado junto con sus bases):
[link](https://dev.mysql.com/doc/workbench/en/wb-reverse-engineer-live.html)
    - Al generar un nuevo DER, acordarse de reemplazar el que esta en src/main/resources/db