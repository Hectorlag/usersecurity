# usersecurity
aplicacion con roles, usuario y permisos con spring security

# Proyecto de Seguridad con Spring Boot

Este proyecto es una implementación básica de autenticación y autorización utilizando Spring Security, con una arquitectura de roles y permisos. Se utiliza una base de datos para almacenar los usuarios, roles y permisos, y se accede a ellos mediante un `UserDetailsService`.

## Descripción

Este proyecto tiene como objetivo demostrar cómo manejar la autenticación de usuarios, asignación de roles y permisos, y protección de rutas en una aplicación Spring Boot. La relación entre las entidades es la siguiente:

- **User** tiene una relación de 1 a n con **Role**.
- **Role** tiene una relación de 1 a n con **Permission**.

El sistema permite el acceso a las rutas según los permisos y roles asignados a cada usuario.

## Tecnologías

- **Spring Boot**: Framework para desarrollar aplicaciones Java.
- **Spring Security**: Para gestionar la seguridad, autenticación y autorización.
- **Postman**: Herramienta para probar las APIs.
- **JPA/Hibernate**: Para la persistencia de datos en base de datos.
- **MySQL**: Base de datos para almacenar usuarios, roles y permisos.

## Estructura del Proyecto

- `com.example.logindos.model`: Contiene las entidades **UserSec**, **Role**, y **Permission**.
- `com.example.logindos.repository`: Contiene los repositorios de las entidades.
- `com.example.logindos.service`: Contiene el servicio `UserDetailsService` para la autenticación.
- `com.example.logindos.security.config`: Configuración de seguridad de Spring Security.

## Configuración

1. **Base de datos**: Asegúrate de tener MySQL configurado en tu entorno y crea las tablas necesarias para las entidades `UserSec`, `Role` y `Permission`.
2. **Archivo de configuración**: Configura tu archivo `application.properties` para conectar tu aplicación con la base de datos. Ejemplo:

   ```properties
   spring.datasource.url=jdbc:mysql://localhost:3306/mi_base_de_datos
   spring.datasource.username=usuario
   spring.datasource.password=contraseña
   spring.jpa.hibernate.ddl-auto=update
Configuración de seguridad: En la clase SecurityConfig, se ha configurado Spring Security para utilizar autenticación básica HTTP con un UserDetailsService que consulta la base de datos para verificar las credenciales del usuario.

Cifrado de contraseñas: En la configuración de seguridad, se utiliza un NoOpPasswordEncoder (para desarrollo). En producción, se recomienda usar un codificador de contraseñas más seguro, como BCryptPasswordEncoder.

## Uso
1. Clona este repositorio en tu máquina local:
git clone https://github.com/Hectorlag/usersecurity.git

2. Navega al directorio del proyecto y ejecuta el servidor Spring Boot:
 ./mvnw spring-boot:run

3. El servidor debería estar corriendo en http://localhost:8080


