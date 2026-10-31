
# Tienda de Juguetes - Sistema de Inventario

## Descripción

implementación de DAOS y base de datos

## Tecnologías utilizadas

- Java
- Java Swing
- JDBC
- MySQL
- phpMyAdmin
- Visual Studio Code
- MySQL Connector/J

## Arquitectura

El proyecto utiliza el patrón Modelo-Vista-Controlador (MVC).

### Modelo
- Producto.java: representa los datos de un juguete.
- ProductoDAO.java: realiza las operaciones en MySQL.

### Vista
- VistaGeneral.java: contiene la interfaz gráfica.

### Controlador
- ControladorGeneral.java: administra las acciones del usuario.

### Conexión
- Conexion.java: establece la conexión con MySQL.

### Inicio
- InicioTienda.java: ejecuta la aplicación.

## Funcionalidades

1. Registrar nuevos juguetes.
2. Buscar productos por código.
3. Actualizar información de productos.
4. Eliminar juguetes del inventario.
5. Mostrar todos los productos.
6. Limpiar los campos del formulario.

## Base de datos

Nombre: inventario_juguetes

Tabla: productos

Campos:
- id_producto
- nombre
- categoria
- precio
- cantidad

El archivo database/inventario_juguetes.sql contiene
las instrucciones para crear la base de datos.

## Requisitos

- Java JDK instalado.
- MySQL funcionando en el puerto 3306.
- MySQL Connector/J incluido en la carpeta lib.
- Visual Studio Code o una terminal compatible.

## Instalación

1. Descargar o clonar el repositorio.
2. Crear la base de datos importando el archivo SQL
   desde phpMyAdmin.
3. Verificar la configuración de MySQL.
4. Configurar las variables de entorno DB_USER
   y DB_PASSWORD.
5. Compilar las clases Java.
6. Ejecutar la clase tienda_juguetes.InicioTienda.

## Ejecución en Windows PowerShell

Desde la carpeta principal del proyecto:

Configurar el usuario:

    $env:DB_USER = "root"
    $env:DB_PASSWORD = ""

Crear la carpeta de compilación:

    New-Item -ItemType Directory -Force bin

Compilar:

    javac -encoding UTF-8 -cp "lib/*" -d bin src/Conexion/Conexion.java src/tienda_juguetes/modelos/Producto.java src/tienda_juguetes/modelos/ProductoDAO.java src/tienda_juguetes/vista/VistaGeneral.java src/tienda_juguetes/controlador/ControladorGeneral.java src/tienda_juguetes/InicioTienda.java

Ejecutar:

    java -cp "bin;lib/*" tienda_juguetes.InicioTienda

Si MySQL requiere contraseña, debe configurarse
DB_PASSWORD con la contraseña correspondiente.

## Seguridad

Las credenciales de MySQL se configuran mediante
variables de entorno y no se almacenan en el repositorio.

## Propósito académico

Proyecto desarrollado para aplicar conceptos de
programación orientada a objetos, arquitectura MVC,
interfaces gráficas y conexiones a bases de datos.
