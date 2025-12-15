# Pastelería Real - API REST

API REST desarrollada con Spring Boot para la gestión de la Pastelería Real. Implementa autenticación JWT, operaciones CRUD completas y documentación Swagger.

## Tecnologías Utilizadas

- **Spring Boot 3.4.0**: Framework principal
- **Spring Security**: Autenticación y autorización
- **JWT (JSON Web Tokens)**: Autenticación basada en tokens
- **Spring Data JPA**: Persistencia de datos
- **H2 Database**: Base de datos en memoria (desarrollo)
- **MySQL**: Base de datos para producción
- **Swagger/OpenAPI**: Documentación de API
- **Lombok**: Reducción de código boilerplate
- **Maven**: Gestión de dependencias

## Requisitos

- Java 17 o superior
- Maven 3.6+

## Instalación y Ejecución

### 1. Clonar el repositorio

```bash
git clone <URL_DEL_REPOSITORIO>
cd pasteleria-api
```

### 2. Compilar el proyecto

```bash
./mvnw clean package
```

### 3. Ejecutar la aplicación

```bash
./mvnw spring-boot:run
```

La aplicación estará disponible en: `http://localhost:8080`

## Endpoints Principales

### Autenticación

- **POST** `/api/auth/login` - Iniciar sesión y obtener token JWT

### Productos

- **GET** `/api/productos` - Listar todos los productos
- **GET** `/api/productos/{id}` - Obtener producto por ID
- **GET** `/api/productos/codigo/{codigo}` - Obtener producto por código
- **GET** `/api/productos/categoria/{categoriaId}` - Listar productos por categoría
- **POST** `/api/productos` - Crear producto (requiere autenticación)
- **PUT** `/api/productos/{id}` - Actualizar producto (requiere autenticación)
- **DELETE** `/api/productos/{id}` - Eliminar producto (requiere rol admin)

### Categorías

- **GET** `/api/categorias` - Listar todas las categorías
- **GET** `/api/categorias/{id}` - Obtener categoría por ID
- **POST** `/api/categorias` - Crear categoría (requiere rol admin)
- **DELETE** `/api/categorias/{id}` - Eliminar categoría (requiere rol superadmin)

## Documentación Swagger

Una vez iniciada la aplicación, accede a la documentación interactiva de la API:

- **Swagger UI**: `http://localhost:8080/swagger-ui.html`
- **API Docs (JSON)**: `http://localhost:8080/api-docs`

## Autenticación JWT

### Flujo de autenticación

1. **Login**: Enviar credenciales a `/api/auth/login`
   ```json
   {
     "correo": "ana.maria@gmail.cl",
     "password": "password123"
   }
   ```

2. **Respuesta**: Recibir token JWT
   ```json
   {
     "token": "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...",
     "tipo": "Bearer",
     "correo": "ana.maria@gmail.cl",
     "nombre": "Ana María",
     "apellidos": "Pérez Soto",
     "tipoUsuario": "SuperAdmin"
   }
   ```

3. **Uso del token**: Incluir en el header `Authorization` de las peticiones protegidas
   ```
   Authorization: Bearer eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...
   ```

## Roles de Usuario

- **SUPERADMIN**: Acceso completo a todas las funcionalidades
- **ADMINISTRADOR**: Gestión de productos y categorías
- **VENDEDOR**: Gestión de productos
- **CLIENTE**: Solo lectura de productos

## Usuarios de Prueba

| Correo | Password | Rol | Hash Password |
|--------|----------|-----|---------------|
| ana.maria@gmail.cl | password123 | SuperAdmin | 61be55a8e2f6b4e172338bddf184d6dbee29c98853e0a0485ecee7f27b9af0b4 |
| luis.felipe@gmail.com | password123 | Administrador | 61be55a8e2f6b4e172338bddf184d6dbee29c98853e0a0485ecee7f27b9af0b4 |
| claudia.isabel@duoc.cl | password123 | Cliente | 61be55a8e2f6b4e172338bddf184d6dbee29c98853e0a0485ecee7f27b9af0b4 |

**Nota**: El hash corresponde a "password123" (SHA-256). En producción, se debe usar BCrypt.

## Configuración de Base de Datos

### H2 (Desarrollo - Por defecto)

La configuración actual usa H2 en memoria. Para acceder a la consola H2:

- URL: `http://localhost:8080/h2-console`
- JDBC URL: `jdbc:h2:mem:pasteleriadb`
- Usuario: `sa`
- Password: (vacío)

### MySQL (Producción)

Para usar MySQL, modifica `application.properties`:

```properties
# Comentar configuración H2
# spring.datasource.url=jdbc:h2:mem:pasteleriadb
# spring.h2.console.enabled=true

# Descomentar y configurar MySQL
spring.datasource.url=jdbc:mysql://localhost:3306/pasteleriadb
spring.datasource.username=tu_usuario
spring.datasource.password=tu_password
spring.jpa.database-platform=org.hibernate.dialect.MySQLDialect
spring.jpa.hibernate.ddl-auto=update
```

## Estructura del Proyecto

```
src/main/java/com/pasteleria/api/
├── config/          # Configuraciones (Security, CORS, Data Initializer)
├── controller/      # Controladores REST
├── dto/             # Data Transfer Objects
├── entity/          # Entidades JPA
├── repository/      # Repositorios JPA
├── security/        # Componentes de seguridad (JWT, Filters)
└── service/         # Lógica de negocio
```

## Características Implementadas

### ✅ Conexión a Base de Datos
- Configuración JPA/Hibernate
- Modelos de datos: Usuario, Producto, Categoría, Dirección
- Relaciones entre entidades

### ✅ API REST con Spring Boot
- Endpoints CRUD completos
- Validación de datos con Bean Validation
- Manejo de excepciones

### ✅ Documentación Swagger
- Interfaz interactiva para probar endpoints
- Documentación automática de la API
- Anotaciones OpenAPI

### ✅ Autenticación JWT
- Generación y validación de tokens
- Filtro de autenticación
- Gestión de roles y permisos

### ✅ Seguridad
- Spring Security configurado
- Endpoints protegidos por roles
- CORS configurado para frontend

### ✅ Integración Frontend-Backend
- CORS habilitado para `http://localhost:5173` y `http://localhost:3000`
- API RESTful lista para consumo

## Testing

Para ejecutar los tests:

```bash
./mvnw test
```

## Compilar para Producción

```bash
./mvnw clean package -DskipTests
```

El archivo JAR se generará en: `target/pasteleria-api-0.0.1-SNAPSHOT.jar`

Para ejecutar el JAR:

```bash
java -jar target/pasteleria-api-0.0.1-SNAPSHOT.jar
```

## Contacto y Soporte

Para consultas o reportar problemas, contacta al equipo de desarrollo.

---

**Desarrollado para Evaluación Parcial 3 - DSY1104 Desarrollo Fullstack II**
