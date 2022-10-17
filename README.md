# # Lista de Clientes API

## SWAGGER
http://localhost:8080/api/cliente/swagger-ui.html

## REQUISITOS
1-Instalar java jdk 17
2-maven 3.8.x

## LEVANTAR PROYECTO
1- ejecutar el comando en la raiz del proyecto: mvn clean install
2- ejecutar la clase principal ClienteApplication.

## **Objetivo**
Microservicio desarrollado en JAVASpring boot
● API Rest documentada en Swagger
● Deployado enAWS o algún CLOUD + código subido en GITHUB
● Endpoint de Entrada POST /creacliente
○ Nombre
○ Apellido
○ Edad
○ Fecha de nacimiento
● Endpoint de salida GET /kpideclientes
○ Promedio edad entre todos los clientes
○ Desviación estándar entre las edades de todos los clientes
● Endpoint de salida GET /listclientes
○ Lista de personas con todos los datos + fecha probable de muerte de cada una