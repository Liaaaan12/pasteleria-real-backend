# 🚀 Guía para usar Postman con Pastelería API

## ✅ Requisitos
1. **La aplicación debe estar corriendo** en `http://localhost:8080`
2. **Postman instalado** en tu computadora
3. **La colección JSON importada**

---

## 📥 Pasos para Importar la Colección

### Opción 1: Importar Directamente
1. Abre **Postman**
2. Click en el botón **"Import"** (esquina superior izquierda)
3. Selecciona **"Upload Files"**
4. Busca y selecciona `Postman_Collection_Pasteleria_API.json`
5. Click en **"Import"**

### Opción 2: Drag & Drop
1. Abre **Postman**
2. Arrastra y suelta el archivo `Postman_Collection_Pasteleria_API.json` al área principal de Postman

---

## 🔐 Configurar Autenticación (JWT Token)

### Paso 1: Login para obtener el token
1. En Postman, ve a **Autenticación → Iniciar sesión**
2. Cambia el email y contraseña según tus datos
3. Click en **Send**
4. Copia el token del response (debe ser algo como: `eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9...`)

### Paso 2: Configurar variable global
1. En la colección, haz click en **"Pastelería API"** (nombre de la colección)
2. Ve a la pestaña **"Variables"**
3. En la fila `token`, pega el token en la columna **"Current Value"**
4. Click en **"Save"**

---

## 🧪 Probar Endpoints

### 1. GET - Listar todos los productos
```
GET http://localhost:8080/api/productos
```
- **Autenticación:** NO requerida
- **Expected:** Array de productos

### 2. GET - Obtener producto por ID
```
GET http://localhost:8080/api/productos/1
```
- **Autenticación:** NO requerida
- **Cambiar:** `1` por el ID que desees

### 3. POST - Crear producto
```
POST http://localhost:8080/api/productos
```
- **Autenticación:** SI requerida (agregar header `Authorization: Bearer {{token}}`)
- **Body (JSON):**
```json
{
  "codigoProducto": "PROD003",
  "nombreProducto": "Cheesecake",
  "precioProducto": 35000,
  "descripcionProducto": "Delicioso cheesecake casero",
  "imagenProducto": "https://example.com/cheesecake.jpg",
  "stock": 15,
  "stockCritico": 3
}
```

### 4. PUT - Actualizar producto
```
PUT http://localhost:8080/api/productos/1
```
- **Autenticación:** SI requerida
- **Body (JSON):** Con los datos a actualizar

### 5. DELETE - Eliminar producto
```
DELETE http://localhost:8080/api/productos/1
```
- **Autenticación:** SI requerida

---

## ❌ Solucionar Problemas

### "Cannot GET /api/productos"
✅ **Solución:** La app no está corriendo. Ejecuta:
```bash
cd api
java -jar target\pasteleria-api-0.0.1-SNAPSHOT.jar
```

### "Connection refused" o "localhost refused to connect"
✅ **Solución:** 
- Verifica que estés usando `http://localhost:8080` (no `https`)
- Asegúrate de que el puerto 8080 esté disponible
- Reinicia la aplicación

### "401 Unauthorized"
✅ **Solución:** El token no está configurado o expiró
- Ve a **Autenticación → Iniciar sesión**
- Obtén un nuevo token
- Actualiza la variable `token` en la colección

### "400 Bad Request"
✅ **Solución:** Verifica que:
- El JSON esté bien formateado
- Los campos requeridos estén presentes
- Los tipos de datos sean correctos (strings, números, fechas)

---

## 🌐 Ver Documentación Interactiva

Abre en tu navegador:
```
http://localhost:8080/swagger-ui.html
```

---

## 💡 Tips
- Usa el environment de Postman para manejar diferentes URLs (localhost, staging, producción)
- Guarda el token en una variable global para reutilizarlo
- Usa pre-request scripts en Postman para automatizar el login
