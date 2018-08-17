/*
Navicat MySQL Data Transfer

Source Server         : conexion_MYSQL
Source Server Version : 50505
Source Host           : localhost:3306
Source Database       : ferre_cali

Target Server Type    : MYSQL
Target Server Version : 50505
File Encoding         : 65001

Date: 2018-08-17 07:56:21
*/
CREATE DATABASE ferre_cali;
USE ferre_cali;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for empleado
-- ----------------------------
DROP TABLE IF EXISTS `empleado`;
CREATE TABLE `empleado` (
  `id_empleado` int(11) NOT NULL AUTO_INCREMENT,
  `folio_empleado` varchar(6) NOT NULL,
  `nombre` varchar(25) NOT NULL,
  `ape_pat` varchar(30) NOT NULL,
  `ape_mat` varchar(30) NOT NULL,
  `calle` varchar(30) NOT NULL,
  `numero` char(3) NOT NULL,
  `colonia` varchar(30) NOT NULL,
  `telefono` varchar(10) NOT NULL,
  `edad` int(10) unsigned NOT NULL,
  PRIMARY KEY (`id_empleado`),
  UNIQUE KEY `folio_empleado` (`folio_empleado`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of empleado
-- ----------------------------
INSERT INTO `empleado` VALUES ('3', 'EIS001', 'Steph', 'Islas', 'Santos', '10 de mayo', '49', 'La Cañada', '7757578912', '69');
INSERT INTO `empleado` VALUES ('4', 'JTG002', 'Jessica', 'Tellez', 'Galindo', 'El arbol', '128', 'El paraiso', '7759865123', '17');
INSERT INTO `empleado` VALUES ('8', 'OEL003', 'Oscar', 'Escamilla', 'Luqueño', '15 de sep', '156', 'El paraiso', '775785123', '21');
INSERT INTO `empleado` VALUES ('10', '', 'oscar', 'escamilla', 'luqueño', 'paraiso1', '222', 'paraiso', '7739292', '20');

-- ----------------------------
-- Table structure for productos
-- ----------------------------
DROP TABLE IF EXISTS `productos`;
CREATE TABLE `productos` (
  `id_producto` int(11) NOT NULL AUTO_INCREMENT,
  `folio_producto` varchar(5) NOT NULL,
  `nombre` varchar(30) NOT NULL,
  `existencia` float unsigned NOT NULL,
  `existencia_um` varchar(25) NOT NULL,
  `precio_unitario` float unsigned NOT NULL,
  `marca` varchar(30) NOT NULL,
  `fecha_ingreso` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `descripcion` varchar(60) DEFAULT 'Producto utilizable',
  `categoria` varchar(25) NOT NULL,
  `imagen` varchar(100) NOT NULL,
  `id_proveedor` int(11) NOT NULL,
  PRIMARY KEY (`id_producto`),
  UNIQUE KEY `folio_producto` (`folio_producto`),
  KEY `id_proveedor` (`id_proveedor`),
  CONSTRAINT `productos_ibfk_1` FOREIGN KEY (`id_proveedor`) REFERENCES `proveedor` (`id_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of productos
-- ----------------------------
INSERT INTO `productos` VALUES ('3', 'LAUC4', 'serrote', '8', '   ', '150', 'truper', '2018-08-10 22:23:52', 'Acero inoxidable', 'general ', '289-large_default.jpg', '2');
INSERT INTO `productos` VALUES ('4', 'TRU34', 'Resanador', '8', '1L', '55', 'Comex', '2018-08-10 22:25:35', 'Producto utilizable', 'Carpinteria', 'river-resanador-pino.jpg', '1');
INSERT INTO `productos` VALUES ('5', 'WAL45', 'Brocas', '10', '', '200', 'Wallt', '2018-08-10 22:27:09', 'Diferentes medidas ', 'General', '34GR21_AS01.jpg', '1');
INSERT INTO `productos` VALUES ('6', 'TUG45', 'Tubo', '8', 'm', '90', 'truper', '2018-08-17 04:26:29', 'Galvanizado', 'Plomeria', 'descarga.jpg', '2');
INSERT INTO `productos` VALUES ('7', 'DFDF4', 'Pija', '1000', 'pulg', '3', 'Generico', '2018-08-17 04:32:31', 'Para carpinteria', 'Carpinteria', 'Pija-Negra.jpg', '1');
INSERT INTO `productos` VALUES ('12', 'LDFF3', 'Llave ', '34', 'pulg', '35', 'Geerico', '2018-08-17 04:49:27', 'de paso', 'Plomeria', 'descarga (1).jpg', '2');
INSERT INTO `productos` VALUES ('15', 'SDF45', 'Cepillo ', '3', '', '250', 'Wallt', '2018-08-17 04:52:52', 'Carpintero', 'Carpinteria', 'cepillo_carpintero.png', '3');
INSERT INTO `productos` VALUES ('17', 'RTGF3', 'Conectores PVC', '50', 'pulg', '40', 'Generico', '2018-08-17 05:08:08', 'Producto utilizable', 'Plomeria', '50-tm_home_default.jpg', '2');
INSERT INTO `productos` VALUES ('22', 'RTFG5', 'Llave', '20', 'pulg', '50', 'Generico', '2018-08-17 05:20:32', 'Cromo', 'Plomeria', '5AXM4_AS01.jpg', '1');
INSERT INTO `productos` VALUES ('24', 'NG875', 'Sierra', '5', '', '1500', 'Volt', '2018-08-17 05:22:47', '', 'Carpinteria', '289-large_default.jpg', '2');
INSERT INTO `productos` VALUES ('26', 'SDAD2', 'Cemento', '100', 'bulto', '105', 'Cemex', '2018-08-17 05:44:53', 'Producto utilizable', 'Construccion', 'cemex_cemento_tolteca.jpg', '1');
INSERT INTO `productos` VALUES ('28', 'DES45', 'Desarmador', '30', '', '60', 'Wallt', '2018-08-17 05:49:08', 'Distintas medidas ', 'General', '155101.jpg', '3');
INSERT INTO `productos` VALUES ('29', 'PUN74', 'Puntas ', '20', 'bulto', '150', 'wallt', '2018-08-17 05:50:59', 'Producto utilizable', 'General', 'images.jpg', '2');
INSERT INTO `productos` VALUES ('31', 'PRE51', 'Llave de presion', '20', '', '180', 'Truper', '2018-08-17 05:53:48', 'Producto utilizable', 'General', 'presion.jpg', '1');
INSERT INTO `productos` VALUES ('32', 'TUE45', 'Tuercas', '200', 'Distintas medidas ', '5', 'Generico', '2018-08-17 06:38:12', 'Producto utilizable', 'General', 'tuercas.jpg', '2');
INSERT INTO `productos` VALUES ('33', 'TOR34', 'Tornillo', '200', 'Distontas medidas', '5', 'Generico', '2018-08-17 06:41:11', 'Rosca fina', 'General', '0994925000.jpg', '2');
INSERT INTO `productos` VALUES ('35', 'ALL34', 'LLave allen ', '6', 'Todas las medidas', '100', 'Truper', '2018-08-17 06:44:23', 'Juego de llaves Allen', 'General', 'llaves-allen-std-extralargas-10-pz.jpg', '2');
INSERT INTO `productos` VALUES ('36', 'DAD05', 'Juego de dados', '8', 'Todas las medidas', '300', 'Staneli', '2018-08-17 06:47:55', 'Productos', 'General', 'dados.jpg', '1');
INSERT INTO `productos` VALUES ('38', 'SAP07', 'Sapo de baño', '9', 'Pieza', '70', 'Generico', '2018-08-17 06:49:03', 'Producto utilizable', 'Plomeria', '49346.png', '3');

-- ----------------------------
-- Table structure for proveedor
-- ----------------------------
DROP TABLE IF EXISTS `proveedor`;
CREATE TABLE `proveedor` (
  `id_proveedor` int(11) NOT NULL AUTO_INCREMENT,
  `folio_proveedor` float NOT NULL,
  `nombre_empresa` varchar(30) NOT NULL,
  `calle` varchar(30) NOT NULL,
  `numero` varchar(3) NOT NULL,
  `colonia` varchar(30) NOT NULL,
  `telefono` varchar(10) DEFAULT NULL,
  PRIMARY KEY (`id_proveedor`),
  UNIQUE KEY `folio_proveedor` (`folio_proveedor`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of proveedor
-- ----------------------------
INSERT INTO `proveedor` VALUES ('1', '12345', 'truper', 'durango', '354', 'centro', '7751634567');
INSERT INTO `proveedor` VALUES ('2', '112233', 'wallt', 'F.Soto', '456', 'centro', '7756432129');
INSERT INTO `proveedor` VALUES ('3', '11323', 'Oscar Escamilla', 'Durango', '302', 'paraiso', '7751306549');

-- ----------------------------
-- Table structure for usuarios
-- ----------------------------
DROP TABLE IF EXISTS `usuarios`;
CREATE TABLE `usuarios` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `contrasena` varchar(255) NOT NULL,
  `rol` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of usuarios
-- ----------------------------
INSERT INTO `usuarios` VALUES ('1', 'admin', 'admin', '1');
INSERT INTO `usuarios` VALUES ('2', 'user', 'user', '2');
INSERT INTO `usuarios` VALUES ('3', 'oscar', 'oscar', null);
INSERT INTO `usuarios` VALUES ('4', 'sdsd', '12345', null);

-- ----------------------------
-- Table structure for ventas
-- ----------------------------
DROP TABLE IF EXISTS `ventas`;
CREATE TABLE `ventas` (
  `id_venta` int(11) NOT NULL AUTO_INCREMENT,
  `folio_venta` varchar(5) NOT NULL,
  `fecha` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP,
  `total` float unsigned NOT NULL,
  `cantidad` float unsigned NOT NULL,
  `precio` float unsigned NOT NULL,
  `unidemed` varchar(40) NOT NULL,
  `id_empleado` int(11) NOT NULL,
  `id_producto` int(11) NOT NULL,
  PRIMARY KEY (`id_venta`),
  KEY `id_empleado` (`id_empleado`),
  KEY `id_producto` (`id_producto`),
  CONSTRAINT `ventas_ibfk_1` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`),
  CONSTRAINT `ventas_ibfk_2` FOREIGN KEY (`id_producto`) REFERENCES `productos` (`id_producto`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of ventas
-- ----------------------------
