# Comandos necesarios para el funcionamiento del programa
## Creacion de la base de datos
```bash
docker compose up
```
o
```bash
docker-compose up
```

## Eliminacion de las tablas
Ejecutar el siguiente comando y a continuación ingresar la contraseña del usuario, en linux es probable que pida permisos de super usuario **(sudo)**
```bash
docker exec -it [id_contenedor] mysql -u [usuario] -p
```
Luego de acceder al SQL
```SQL
-- Seleccionar la base de datos
USE [nombre_db];
```
    
```SQL
-- Desactivar la verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 0;

-- Eliminar tablas
DROP TABLE IF EXISTS facturaProducto;
DROP TABLE IF EXISTS factura;
DROP TABLE IF EXISTS producto;
DROP TABLE IF EXISTS cliente;

-- Reactivar la verificación de claves foráneas
SET FOREIGN_KEY_CHECKS = 1;
```
