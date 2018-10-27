# SGE-G4
### Desarrolladores: Matias Vivone, Martin Gauna, Gustavo Di Peppe, Juan Conde y Gonzalo Giliberti
# README
### Este proyecto tiene como proposito el Trabajo practico anual de la materia Diseño de sistemas
# 
## Contacto:
### Por medio de mail a cualquiera de los siguiente mails:
### dgusti89@gmail.com, matiasvivone@hotmail.com, martinj.gauna@gmail.com, juanma.conde@hotmail.com o gonzalogiliberti@gmail.com
# 
## Documentos de entregas:
### carpeta SGE-G4/Documentacion
#
## para correr test:
### posicionarse sobre la raiz del proyecto y correr el comando "mvn test"
### _nota: asegurarse de que la variable JAVA_HOME esté apuntando a un jdk y no a un jre_ 


Administrador:

	- Hogar y consumo:
		Tabla con 2 columnas, una para los hogares y otra con la sumatoria de consumos para ese hogar.

	- Reportes hechos en persistencia persistencia
		tabla con unica columna? que liste los reportes

	-Alta dispositivo:
		Formulario con todos los campos necesarios para crear un dispositivo.
		Analizar de que tipo de input es cada campo.

Cliente (c/u va a ser un tab diferente?): 
    
	- Estado del hogar:
		- tabla con ultimas mediciones (del usuario/ Hogar)
		- Consumo del ultimo periodo
		- Tabla con el estado de los dispositivos
		- reglas activas
	- Consulta de consumo por periodo
		tabla que liste periodos o un combo para seleccionar el periodo y luego mostrar el consumo correspondiente
	- Carga de archivo de dispositivo
		Formulario que tenga:
			Inputbox que abra los files de la pc y deje subir un dispositivo
			ver si hay que agregar mas campos o el archivo tiene todo
	- Ejecucion del simples
		ver que mierda es esto y hacerlo (puto)
	-	ABM de Reglas y dispositivos
		Tabla de todas las relgas/dispositivos (aplica el mismo diseño para ambos). 
			Modificacion
				Al hacer click en una nos lleva a otra pantallaa que nos muestra los campos de la regla y va a tener un boton de edit
				Este botton abre un pop up (el mismo que se usa para la creacion de reglas) para modificar o hace que todos los campos de la regla se transformen en inputs (Elegir una alternativa)
				Meter boton para eliminar en algun lado.

			Alta
				(depende de que elegimos en la modificacion) si es un pop up o un form con los inputs vacios

			Baja, boton al final de la fila en la tabla que muestra las reglas ?



Preguntas a hacer:
- Hogar == usuario? 
	El Hogar es unico y uno solo por usuario?