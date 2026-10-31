
CREATE DATABASE IF NOT EXISTS inventario_juguetes;

USE inventario_juguetes;

CREATE TABLE IF NOT EXISTS productos (
    id_producto VARCHAR(10) PRIMARY KEY,
    nombre VARCHAR(60) NOT NULL,
    categoria VARCHAR(40) NOT NULL,
    precio DECIMAL(12,2) NOT NULL,
    cantidad INT NOT NULL DEFAULT 0
);
