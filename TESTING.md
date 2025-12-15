# Tests y Postman - Pastelería API

## Ejecutar Tests

### Con Maven
```bash
cd api
.\mvnw.cmd test
```

### Test específico
```bash
.\mvnw.cmd test -Dtest=ProductoControllerTest
```

## Importar Colección en Postman

1. Abre Postman
2. Click en "Import" (arriba a la izquierda)
3. Selecciona "Upload Files"
4. Busca el archivo `Postman_Collection_Pasteleria_API.json`
5. Click en "Import"

## Variables de Postman

La colección incluye una variable `{{token}}` que debes configurar:

1. Después de importar la colección, haz click en el nombre "Pastelería API"
2. Ve a la pestaña "Variables"
3. En la variable `token`, pega el JWT token que obtienes del endpoint `/api/auth/login`

## Endpoints Disponibles

### Productos
- `GET /api/productos` - Listar todos
- `GET /api/productos/{id}` - Obtener por ID
- `GET /api/productos/codigo/{codigo}` - Obtener por código
- `GET /api/productos/categoria/{categoriaId}` - Listar por categoría
- `POST /api/productos` - Crear (requiere autenticación)
- `PUT /api/productos/{id}` - Actualizar (requiere autenticación)
- `DELETE /api/productos/{id}` - Eliminar (requiere autenticación)

### Categorías
- `GET /api/categorias` - Listar todas
- `GET /api/categorias/{id}` - Obtener por ID
- `POST /api/categorias` - Crear (requiere autenticación)

### Autenticación
- `POST /api/auth/register` - Registrarse
- `POST /api/auth/login` - Iniciar sesión

## Swagger UI
Accede a `http://localhost:8080/swagger-ui.html` para ver la documentación interactiva
