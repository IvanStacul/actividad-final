# Spring Boot, MySQL, JPA, Rest API

API Restful CRUD para un blog utilizando Spring Boot, Mysql, JPA e Hibernate.

Provee, valida y registra las operaciones relacionadas a usuarios, posts (publicaciones) y comentarios

Acompañado al repositorio hay una collección de Postman con consultas de ejemplo [Postman Collection](ActividadFinal.postman_collection.json)

## Steps to Setup

**1. Clonar la aplicación**

```bash
git clone https://github.com/IvanStacul/actividad-final.git
```

**2. Crear la base de datos MySQL**

```bash
create database informatorio
```

**3. Cambiar el usuario y contraseña con los valores que corresponden a tu instalación de MySQL**

- Abrir `src/main/resources/application.properties`
- Cambiar `spring.datasource.username` y `spring.datasource.password` con los valores que corresponden a tu instalación de MySQL

**4. Correr la aplicación usando maven**

```bash
mvn spring-boot:run
```

La aplicación comenzará en <http://localhost:8080>

## API Rest Specifications

### Users

| Method | Url                           | Description                                            | Sample Valid Request Body |
| ------ | ----------------------------- | ------------------------------------------------------ | ------------------------- |
| POST   | /api/v1/user                  | Agrega un usuario                                      | [JSON](#usercreate)       |
| GET    | /api/v1/user/all              | Retorna todos los usuarios                             |                           |
| GET    | /api/v1/user/{userId}         | Retorna el usuario con el id **userId**                |                           |
| DELETE | /api/v1/user/{userId}         | Borra un usuario con el id **userId**                  |                           |
| PUT    | /api/v1/user/{userId}         | Actualiza un usuario                                   | [JSON](#userupdate)       |
| GET    | /api/v1/user/city/{city}      | Devuelve los usuarios filtrados por la ciudad **city** |                           |
| GET    | /api/v1/user/afterDate/{date} | Devuelve los usuarios creados después de **date**      |                           |

### Posts

| Method | Url                              | Description                                             | Sample Valid Request Body |
| ------ | -------------------------------- | ------------------------------------------------------- | ------------------------- |
| POST   | /api/v1/post/{userId}            | Crea un nuevo post para el usuario con el **userId**    | [JSON](#postcreate)       |
| GET    | /api/v1/post/all                 | Devuelve todos los pots                                 |                           |
| GET    | /api/v1/post/{postId}            | Devuelve un post con el id **postId**                   |                           |
| GET    | /api/v1/post/filter/title/{word} | Filtra los post por una palabra (**word**) en el título |                           |
| GET    | /api/v1/post/notPublished        | Devuelve los post que no fueron publicados              |                           |
| PUT    | /api/v1/post/{id}                | Actualiza un post                                       | [JSON](#postupdate)       |
| DELETE | /api/v1/post/{id}                | Elimina un post                                         |                           |

### Comments

| Method | Url                                       | Description                                               | Sample Valid Request Body |
| ------ | ----------------------------------------- | --------------------------------------------------------- | ------------------------- |
| POST   | /api/v1/post/{postId}/comment             | Crea un nuevo comentario para el post con id = **postId** | [JSON](#commentcreate)    |
| GET    | /api/v1/comment                           | Devuelve todos los comentarios                            |                           |
| GET    | /api/v1/post/{postId}/comment             | Devuelve todos los comentarios de un post                 |                           |
| GET    | /api/v1/post/{postId}/comment/{limit}     | Devuelve los **limit** comentarios de un post             |                           |
| GET    | /api/v1/comment/{commentId}               | Devuelve el comentario con id = **commentId**             |                           |
| PUT    | /api/v1/post/{postId}/comment/{commentId} | Actualiza un comentario                                   | [JSON](#commentupdate)    |
| DELETE | /api/v1/post/{postId}/comment/{commentId} | Borra un comentario                                       |                           |
