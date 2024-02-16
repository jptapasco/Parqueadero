-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 20-10-2023 a las 09:30:59
-- Versión del servidor: 10.4.28-MariaDB
-- Versión de PHP: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `parqueadero`
--
DROP DATABASE IF EXISTS parqueadero;
CREATE DATABASE parqueadero;
USE parqueadero;
-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `asignacion`
--

CREATE TABLE `asignacion` (
  `id` int(11) NOT NULL,
  `id_parqueadero` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `asignacion`
--

INSERT INTO `asignacion` (`id`, `id_parqueadero`, `id_usuario`, `create_at`, `update_at`) VALUES
(1, 1, 1, '2023-10-20 07:08:34', '2023-10-20 07:10:43'),
(2, 2, 2, '2023-10-20 07:08:34', '2023-10-20 07:10:48'),
(3, 3, 3, '2023-10-20 07:08:34', '2023-10-20 07:13:02'),
(4, 4, 4, '2023-10-20 07:08:34', '2023-10-20 07:13:06'),
(5, 5, 5, '2023-10-20 07:08:34', '2023-10-20 07:13:09'),
(6, 6, 6, '2023-10-20 07:08:34', '2023-10-20 07:13:12'),
(7, 7, 7, '2023-10-20 07:08:34', '2023-10-20 07:13:15'),
(8, 8, 8, '2023-10-20 07:08:34', '2023-10-20 07:13:22'),
(9, 9, 9, '2023-10-20 07:08:34', '2023-10-20 07:13:59'),
(10, 10, 10, '2023-10-20 07:08:34', '2023-10-20 07:13:52'),
(11, 11, 11, '2023-10-20 07:08:34', '2023-10-20 07:13:47'),
(12, 12, 12, '2023-10-20 07:08:34', '2023-10-20 07:13:44');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `parqueadero`
--

CREATE TABLE `parqueadero` (
  `nit` int(11) NOT NULL,
  `nombre` varchar(50) NOT NULL,
  `direccion` varchar(100) NOT NULL,
  `telefono` int(11) NOT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `parqueadero`
--

INSERT INTO `parqueadero` (`nit`, `nombre`, `direccion`, `telefono`, `create_at`, `update_at`) VALUES
(1, 'Parqueadero A', 'Calle 123', 1234567890, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(2, 'Parqueadero B', 'Avenida XYZ', 2147483647, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(3, 'Parqueadero C', 'Carrera 456', 2147483647, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(4, 'Parqueadero D', 'Calle Principal', 1111111111, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(5, 'Parqueadero E', 'Avenida Central', 2147483647, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(6, 'Parqueadero F', 'Calle Secundaria', 2147483647, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(7, 'Parqueadero G', 'Avenida de Prueba', 2147483647, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(8, 'Parqueadero H', 'Calle de Ejemplo', 2147483647, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(9, 'Parqueadero I', 'Avenida Imaginaria', 2147483647, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(10, 'Parqueadero J', 'Calle Desconocida', 2147483647, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(11, 'Parqueadero K', 'Avenida Lejanopp', 2147483647, '2023-10-20 06:49:51', '2023-10-20 07:28:47'),
(12, 'Parqueadero L', 'Calle Remota', 2147483647, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(13, 'Parqueadero M', 'Avenida Virtual', 2147483647, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(14, 'Parqueadero N', 'Calle Irreal', 2147483647, '2023-10-20 06:49:51', '2023-10-20 06:49:51'),
(15, 'Parqueadero O', 'Avenida de Sueño', 2147483647, '2023-10-20 06:49:51', '2023-10-20 06:49:51');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `persona`
--

CREATE TABLE `persona` (
  `id` int(11) NOT NULL,
  `nombre` varchar(50) DEFAULT NULL,
  `apellidos` varchar(50) DEFAULT NULL,
  `cedula` varchar(15) DEFAULT NULL,
  `celular` int(11) DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `persona`
--

INSERT INTO `persona` (`id`, `nombre`, `apellidos`, `cedula`, `celular`, `create_at`, `update_at`) VALUES
(1, 'Juan', 'Pérez', '123456789', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:52:52'),
(2, 'María', 'López', '987654321', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:52:57'),
(3, 'Carlos', 'González', '456789012', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:53:02'),
(4, 'Luis', 'Ramírez', '789012345', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:53:06'),
(5, 'Ana', 'Martínez', '234567890', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:53:10'),
(6, 'Laura', 'Sánchez', '678901234', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:53:14'),
(7, 'Diego', 'Hernández', '345678901', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:53:17'),
(8, 'Sofía', 'Torres', '012345678', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:53:23'),
(9, 'Pedro', 'Suárez', '567890123', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:53:26'),
(10, 'Elena', 'asas', '901234567', 2147483647, '2023-10-20 06:51:02', '2023-10-20 07:29:15'),
(11, 'Miguel', 'Rojas', '890123456', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:53:36'),
(12, 'Isabel', 'Ortega', '456789012', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:53:42'),
(13, 'Javier', 'Vargas', '678901234', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:53:46'),
(14, 'Mónica', 'Jiménez', '123456789', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:53:53'),
(15, 'Raúl', 'Silva', '555555555', 2147483647, '2023-10-20 06:51:02', '2023-10-20 06:53:58'),
(33, 'paco', 'pwre', '1008', 122112, '2023-10-20 07:28:18', '2023-10-20 07:28:18');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `registro_vehiculos`
--

CREATE TABLE `registro_vehiculos` (
  `placa` varchar(7) NOT NULL,
  `responsable` varchar(100) DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `registro_vehiculos`
--

INSERT INTO `registro_vehiculos` (`placa`, `responsable`, `create_at`, `update_at`) VALUES
('ABC123', 'Juan Pérez', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('BCD890', 'Manuel Torres', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('DEF456', 'Luis González', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('EFG123', 'Lucía Cordero', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('GHI789', 'María Rodríguez', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('HIJ456', 'Pedro Mendoza', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('JKL012', 'Carlos Martínez', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('KLM789', 'Isabel Ortega', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('MNO345', 'Sofía Herrera', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('NOP012', 'Javier Ríos', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('PQR678', 'Andrés Sánchez', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('STU901', 'Laura Jiménez', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('VWX234', 'Diego Castro', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('XYZ789', 'Ana López', '2023-10-20 07:09:33', '2023-10-20 07:09:33'),
('YZA567', 'Natalia Vargas', '2023-10-20 07:09:33', '2023-10-20 07:09:33');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `tarifas`
--

CREATE TABLE `tarifas` (
  `id` int(11) NOT NULL,
  `tipo_vehiculo` enum('Camion','Camioneta','Carro','Moto') DEFAULT NULL,
  `Tarifa` float(10,2) DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `tarifas`
--

INSERT INTO `tarifas` (`id`, `tipo_vehiculo`, `Tarifa`, `create_at`, `update_at`) VALUES
(1, 'Camion', 10.00, '2023-10-19 23:44:31', '2023-10-19 23:44:31'),
(2, 'Camioneta', 8.50, '2023-10-19 23:44:31', '2023-10-19 23:44:31'),
(3, 'Carro', 51.00, '2023-10-19 23:44:31', '2023-10-20 06:42:38'),
(4, 'Moto', 3.00, '2023-10-19 23:44:31', '2023-10-19 23:44:31');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `ticket`
--

CREATE TABLE `ticket` (
  `id` int(11) NOT NULL,
  `placa` varchar(7) DEFAULT NULL,
  `id_asignacion` int(11) DEFAULT NULL,
  `id_tarifa` int(11) DEFAULT NULL,
  `total` float DEFAULT NULL,
  `create_entrada` timestamp NOT NULL DEFAULT current_timestamp(),
  `salida` timestamp NULL DEFAULT NULL,
  `update_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `ticket`
--

INSERT INTO `ticket` (`id`, `placa`, `id_asignacion`, `id_tarifa`, `total`, `create_entrada`, `salida`, `update_at`) VALUES
(54, 'ABC123', 1, 1, NULL, '2023-10-20 07:14:03', NULL, '2023-10-20 07:14:32'),
(55, 'XYZ789', 2, 2, NULL, '2023-10-20 07:14:03', NULL, '2023-10-20 07:14:22'),
(56, 'DEF456', 3, 1, NULL, '2023-10-20 07:14:03', NULL, '2023-10-20 07:14:43'),
(57, 'GHI789', 4, 2, NULL, '2023-10-20 07:14:03', NULL, '2023-10-20 07:14:58'),
(58, 'JKL012', 5, 1, NULL, '2023-10-20 07:14:03', NULL, '2023-10-20 07:15:33'),
(59, 'MNO345', 6, 2, NULL, '2023-10-20 07:14:03', NULL, '2023-10-20 07:15:41'),
(60, 'PQR678', 7, 1, NULL, '2023-10-20 07:14:03', NULL, '2023-10-20 07:16:16'),
(61, 'STU901', 8, 2, NULL, '2023-10-20 07:14:03', NULL, '2023-10-20 07:16:21'),
(62, 'VWX234', 9, 1, 5, '2023-10-20 07:14:03', '2023-10-20 04:15:00', '2023-10-20 07:14:03'),
(63, 'YZA567', 10, 2, 7.5, '2023-10-20 07:14:03', '2023-10-20 05:30:00', '2023-10-20 07:14:03'),
(64, 'BCD890', 11, 1, 5, '2023-10-20 07:14:03', '2023-10-20 06:45:00', '2023-10-20 07:14:03'),
(65, 'EFG123', 12, 2, 7.5, '2023-10-20 07:14:03', '2023-10-20 08:15:00', '2023-10-20 07:14:03');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `id_persona` int(11) DEFAULT NULL,
  `email` varchar(50) DEFAULT NULL,
  `contrasenia` varchar(250) DEFAULT NULL,
  `tipo` enum('ADMIN','VENDEDOR') DEFAULT NULL,
  `estado` enum('ACTIVO','INACTIVO') DEFAULT NULL,
  `create_at` timestamp NOT NULL DEFAULT current_timestamp(),
  `update_at` timestamp NOT NULL DEFAULT current_timestamp() ON UPDATE current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuario`
--

INSERT INTO `usuario` (`id`, `id_persona`, `email`, `contrasenia`, `tipo`, `estado`, `create_at`, `update_at`) VALUES
(1, 1, 'juan@example.com', '123', 'ADMIN', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:23:04'),
(2, 2, 'maria@example.com', 'contrasena2', 'VENDEDOR', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:18:38'),
(3, 3, 'carlos@example.com', 'contrasena3', 'VENDEDOR', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:18:43'),
(4, 4, 'luis@example.com', '1234', 'VENDEDOR', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:29:51'),
(5, 5, 'ana@example.com', 'contrasena5', 'ADMIN', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:18:48'),
(6, 6, 'laura@example.com', 'contraseña6', 'VENDEDOR', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:07:52'),
(7, 7, 'diego@example.com', 'contrasena7', 'VENDEDOR', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:18:59'),
(8, 8, 'sofia@example.com', 'contrasena8', 'VENDEDOR', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:19:58'),
(9, 9, 'pedro@example.com', 'contrasena9', 'ADMIN', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:20:04'),
(10, 10, 'elena@example.com', 'contrasena10', 'VENDEDOR', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:20:09'),
(11, 11, 'miguel@example.com', 'contrasena11', 'ADMIN', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:20:15'),
(12, 12, 'isabel@example.com', 'contrasena12', 'ADMIN', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:20:20'),
(13, 13, 'javier@example.com', 'contrasena13', 'VENDEDOR', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:20:26'),
(14, 14, 'monica@example.com', 'contrasena14', 'ADMIN', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:20:30'),
(15, 15, 'raul@example.com', 'contrasena15', 'VENDEDOR', 'ACTIVO', '2023-10-20 07:04:47', '2023-10-20 07:20:35'),
(63, 33, 'locha@mail.com', '1234', 'VENDEDOR', 'ACTIVO', '2023-10-20 07:28:18', '2023-10-20 07:28:18');

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_parqueadero` (`id_parqueadero`),
  ADD KEY `id_usuario` (`id_usuario`);

--
-- Indices de la tabla `parqueadero`
--
ALTER TABLE `parqueadero`
  ADD PRIMARY KEY (`nit`);

--
-- Indices de la tabla `persona`
--
ALTER TABLE `persona`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `registro_vehiculos`
--
ALTER TABLE `registro_vehiculos`
  ADD PRIMARY KEY (`placa`);

--
-- Indices de la tabla `tarifas`
--
ALTER TABLE `tarifas`
  ADD PRIMARY KEY (`id`);

--
-- Indices de la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_asignacion` (`id_asignacion`),
  ADD KEY `id_tarifa` (`id_tarifa`),
  ADD KEY `placa` (`placa`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id`),
  ADD KEY `id_persona` (`id_persona`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `asignacion`
--
ALTER TABLE `asignacion`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=78;

--
-- AUTO_INCREMENT de la tabla `persona`
--
ALTER TABLE `persona`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=34;

--
-- AUTO_INCREMENT de la tabla `tarifas`
--
ALTER TABLE `tarifas`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `ticket`
--
ALTER TABLE `ticket`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=69;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=64;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `asignacion`
--
ALTER TABLE `asignacion`
  ADD CONSTRAINT `asignacion_ibfk_1` FOREIGN KEY (`id_parqueadero`) REFERENCES `parqueadero` (`nit`),
  ADD CONSTRAINT `asignacion_ibfk_2` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id`);

--
-- Filtros para la tabla `ticket`
--
ALTER TABLE `ticket`
  ADD CONSTRAINT `ticket_ibfk_1` FOREIGN KEY (`id_asignacion`) REFERENCES `asignacion` (`id`),
  ADD CONSTRAINT `ticket_ibfk_2` FOREIGN KEY (`id_tarifa`) REFERENCES `tarifas` (`id`),
  ADD CONSTRAINT `ticket_ibfk_3` FOREIGN KEY (`placa`) REFERENCES `registro_vehiculos` (`placa`);

--
-- Filtros para la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD CONSTRAINT `usuario_ibfk_1` FOREIGN KEY (`id_persona`) REFERENCES `persona` (`id`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
