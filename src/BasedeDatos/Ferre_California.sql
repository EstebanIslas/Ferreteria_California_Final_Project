CREATE database IF NOT EXISTS ferre_cali DEFAULT CHARACTER SET utf8 ;
USE ferre_cali;

CREATE TABLE `empleado` (
	`id_empleado` INT(11) NOT NULL AUTO_INCREMENT,
	`folio_empleado` VARCHAR(6) NOT NULL,
	`nombre` VARCHAR(25) NOT NULL,
	`ape_pat` VARCHAR(30) NOT NULL,
	`ape_mat` VARCHAR(30) NOT NULL,
	`calle` VARCHAR(30) NOT NULL,
	`numero` CHAR(3) NOT NULL,
	`colonia` VARCHAR(30) NOT NULL,
	`telefono` VARCHAR(10) NOT NULL,
	`edad` INT(10) UNSIGNED NOT NULL,
	PRIMARY KEY (`id_empleado`),
	UNIQUE INDEX `folio_empleado` (`folio_empleado`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=9
;

CREATE TABLE `proveedor` (
	`id_proveedor` INT(11) NOT NULL AUTO_INCREMENT,
	`folio_proveedor` FLOAT NOT NULL,
	`nombre_empresa` VARCHAR(30) NOT NULL,
	`calle` VARCHAR(30) NOT NULL,
	`numero` VARCHAR(3) NOT NULL,
	`colonia` VARCHAR(30) NOT NULL,
	`telefono` VARCHAR(10) NULL DEFAULT NULL,
	PRIMARY KEY (`id_proveedor`),
	UNIQUE INDEX `folio_proveedor` (`folio_proveedor`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;


CREATE TABLE `productos` (
	`id_producto` INT(11) NOT NULL AUTO_INCREMENT,
	`folio_producto` VARCHAR(5) NOT NULL,
	`nombre` VARCHAR(30) NOT NULL,
	`existencia` FLOAT UNSIGNED NOT NULL,
	`existencia_um` VARCHAR(25) NOT NULL,
	`precio_unitario` FLOAT UNSIGNED NOT NULL,
	`marca` VARCHAR(30) NOT NULL,
	`fecha_ingreso` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`descripcion` VARCHAR(60) NOT NULL DEFAULT 'Producto utilizable',
	`categoria` VARCHAR(25) NOT NULL,
	`imagen` VARCHAR(100) NOT NULL,
	`tam_img` LONGBLOB NOT NULL,
	`id_proveedor` INT(11) NOT NULL,
	PRIMARY KEY (`id_producto`),
	UNIQUE INDEX `folio_producto` (`folio_producto`),
	INDEX `id_proveedor` (`id_proveedor`),
	CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
AUTO_INCREMENT=5
;


CREATE TABLE `ventas` (
	`id_venta` INT(11) NOT NULL AUTO_INCREMENT,
	`folio_venta` VARCHAR(5) NOT NULL,
	`fecha` TIMESTAMP NOT NULL DEFAULT CURRENT_TIMESTAMP,
	`total` FLOAT UNSIGNED NOT NULL,
	`cantidad` FLOAT UNSIGNED NOT NULL,
	`precio` FLOAT UNSIGNED NOT NULL,
	`unidemed` VARCHAR(40) NOT NULL,
	`id_empleado` INT(11) NOT NULL,
	`id_producto` INT(11) NOT NULL,
	PRIMARY KEY (`id_venta`),
	INDEX `id_empleado` (`id_empleado`),
	INDEX `id_producto` (`id_producto`),
	CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`),
	CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`)
)
COLLATE='utf8_general_ci'
ENGINE=InnoDB
;
