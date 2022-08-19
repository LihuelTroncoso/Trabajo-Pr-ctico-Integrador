-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Servidor: localhost:3306
-- Tiempo de generación: 23-11-2021 a las 15:43:58
-- Versión del servidor: 8.0.26
-- Versión de PHP: 7.4.23

use mydb;

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `mydb`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `estudios` (IN `obraSocial` VARCHAR(30), IN `nombreDoctor` VARCHAR(30), IN `dniIngresado` INT(9), IN `estudio` VARCHAR(50))  BEGIN
declare codigo_doctor int;
declare codigo_obra_social int;
declare codigo_estudio int;
declare codigo_paciente int;
select id_doctor into codigo_doctor from doctor where nombre = nombreDoctor;
select id_obrasocial into codigo_obra_social from obrasocial where nombre = obraSocial;
select id_estudio into codigo_estudio from estudio where nombre = estudio;
select id_paciente into codigo_paciente from paciente where dni = dniIngresado;
insert into paciente_has_estudio (Paciente_id_Paciente, Estudio_id_Estudio, obrasocial_id_obrasocial, doctor_id_Doctor) VALUES (codigo_paciente, codigo_estudio, codigo_obra_social, codigo_doctor);
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `doctor`
--

CREATE TABLE `doctor` (
  `id_Doctor` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `n_matricula` int NOT NULL,
  `especialidad` varchar(45) NOT NULL,
  `direccion` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `doctor`
--

INSERT INTO `doctor` (`id_Doctor`, `nombre`, `n_matricula`, `especialidad`, `direccion`) VALUES
(4, 'Horacio Rodriguez', 123123, 'Cardiologia', 'Congreso 123'),
(5, 'Carlos Alberto Miculicich', 12341234, 'Cardiologia', 'Congreso 123'),
(6, 'Maria Gomez', 1234512345, 'Radiografias', 'Cabildo 123');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `doctor_has_paciente`
--

CREATE TABLE `doctor_has_paciente` (
  `Doctor_id_Doctor` int NOT NULL,
  `Paciente_id_Paciente` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `estudio`
--

CREATE TABLE `estudio` (
  `id_Estudio` int NOT NULL,
  `descripcion` varchar(45) NOT NULL,
  `precio` double NOT NULL,
  `nombre` varchar(45) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `estudio`
--

INSERT INTO `estudio` (`id_Estudio`, `descripcion`, `precio`, `nombre`) VALUES
(2, 'Con imagen con contraste', 5000, 'icc'),
(3, 'Con imagen sin contraste', 3000, 'isc'),
(4, 'Laboratorio', 2000, 'l');

--
-- Disparadores `estudio`
--
DELIMITER $$
CREATE TRIGGER `estudio_AFTER_UPDATE` AFTER UPDATE ON `estudio` FOR EACH ROW BEGIN
INSERT INTO historial_precio_estudio(h_precio, h_estudio) VALUES (OLD.precio, OLD.id_Estudio);
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_pacientes`
--

CREATE TABLE `historial_pacientes` (
  `idhistorial_pacientes` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `historial_precio_estudio`
--

CREATE TABLE `historial_precio_estudio` (
  `h_id` int NOT NULL,
  `h_precio` int DEFAULT NULL,
  `h_estudio` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `historial_precio_estudio`
--

INSERT INTO `historial_precio_estudio` (`h_id`, `h_precio`, `h_estudio`) VALUES
(0, 5000, NULL),
(1, 6000, '2');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `obrasocial`
--

CREATE TABLE `obrasocial` (
  `id_obraSocial` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;

--
-- Volcado de datos para la tabla `obrasocial`
--

INSERT INTO `obrasocial` (`id_obraSocial`, `nombre`, `email`) VALUES
(2, 'OSDE', 'OSDE@OSDE.com'),
(3, 'MEDIFE', 'MEDIFE@MEDIFE.com'),
(4, 'Swiss Medical', 'SWISSMEDICAL@SWISMEDICAL.com');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente`
--

CREATE TABLE `paciente` (
  `id_Paciente` int NOT NULL,
  `nombre` varchar(45) NOT NULL,
  `obra_social` varchar(45) CHARACTER SET utf8 COLLATE utf8_general_ci DEFAULT NULL,
  `direccion` varchar(45) DEFAULT NULL,
  `apellido` varchar(45) NOT NULL,
  `dni` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `paciente`
--

INSERT INTO `paciente` (`id_Paciente`, `nombre`, `obra_social`, `direccion`, `apellido`, `dni`) VALUES
(13, 'Roberto', NULL, 'asdasd', 'Martinez', 131313),
(14, 'asdasd', NULL, 'asdasd', 'asdasdasd', 43500670),
(15, 'Martina', NULL, 'asdasd', 'Gimenez', 1234567),
(16, 'roberto', NULL, 'asdasd', 'Gimenez', 4350080),
(17, 'asdasd', NULL, 'asdasd', 'asdasd', 123123),
(18, 'asdasd', NULL, 'asdasd', 'asdasd', 123123),
(19, 'asdasd', NULL, 'asdasd', 'asdasd', 123123);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `paciente_has_estudio`
--

CREATE TABLE `paciente_has_estudio` (
  `id_Historial_Paciente` int NOT NULL,
  `Paciente_id_Paciente` int NOT NULL,
  `Estudio_id_Estudio` int NOT NULL,
  `doctor_id_Doctor` int NOT NULL,
  `obrasocial_id_obraSocial` int NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb3;

--
-- Volcado de datos para la tabla `paciente_has_estudio`
--

INSERT INTO `paciente_has_estudio` (`id_Historial_Paciente`, `Paciente_id_Paciente`, `Estudio_id_Estudio`, `doctor_id_Doctor`, `obrasocial_id_obraSocial`) VALUES
(2, 14, 2, 4, 2),
(3, 15, 2, 4, 2),
(4, 16, 3, 5, 4);

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `doctor`
--
ALTER TABLE `doctor`
  ADD PRIMARY KEY (`id_Doctor`);

--
-- Indices de la tabla `doctor_has_paciente`
--
ALTER TABLE `doctor_has_paciente`
  ADD PRIMARY KEY (`Doctor_id_Doctor`,`Paciente_id_Paciente`),
  ADD KEY `fk_Doctor_has_Paciente_Paciente1_idx` (`Paciente_id_Paciente`),
  ADD KEY `fk_Doctor_has_Paciente_Doctor1_idx` (`Doctor_id_Doctor`);

--
-- Indices de la tabla `estudio`
--
ALTER TABLE `estudio`
  ADD PRIMARY KEY (`id_Estudio`);

--
-- Indices de la tabla `historial_pacientes`
--
ALTER TABLE `historial_pacientes`
  ADD PRIMARY KEY (`idhistorial_pacientes`);

--
-- Indices de la tabla `historial_precio_estudio`
--
ALTER TABLE `historial_precio_estudio`
  ADD PRIMARY KEY (`h_id`);

--
-- Indices de la tabla `obrasocial`
--
ALTER TABLE `obrasocial`
  ADD PRIMARY KEY (`id_obraSocial`);

--
-- Indices de la tabla `paciente`
--
ALTER TABLE `paciente`
  ADD PRIMARY KEY (`id_Paciente`);

--
-- Indices de la tabla `paciente_has_estudio`
--
ALTER TABLE `paciente_has_estudio`
  ADD PRIMARY KEY (`id_Historial_Paciente`),
  ADD KEY `fk_Paciente_has_Estudio_Estudio1_idx` (`Estudio_id_Estudio`),
  ADD KEY `fk_Paciente_has_Estudio_Paciente_idx` (`Paciente_id_Paciente`),
  ADD KEY `fk_paciente_has_estudio_doctor1_idx` (`doctor_id_Doctor`),
  ADD KEY `fk_paciente_has_estudio_obrasocial1_idx` (`obrasocial_id_obraSocial`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `doctor`
--
ALTER TABLE `doctor`
  MODIFY `id_Doctor` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- AUTO_INCREMENT de la tabla `estudio`
--
ALTER TABLE `estudio`
  MODIFY `id_Estudio` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `historial_precio_estudio`
--
ALTER TABLE `historial_precio_estudio`
  MODIFY `h_id` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT de la tabla `obrasocial`
--
ALTER TABLE `obrasocial`
  MODIFY `id_obraSocial` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- AUTO_INCREMENT de la tabla `paciente`
--
ALTER TABLE `paciente`
  MODIFY `id_Paciente` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=20;

--
-- AUTO_INCREMENT de la tabla `paciente_has_estudio`
--
ALTER TABLE `paciente_has_estudio`
  MODIFY `id_Historial_Paciente` int NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `doctor_has_paciente`
--
ALTER TABLE `doctor_has_paciente`
  ADD CONSTRAINT `fk_Doctor_has_Paciente_Doctor1` FOREIGN KEY (`Doctor_id_Doctor`) REFERENCES `doctor` (`id_Doctor`),
  ADD CONSTRAINT `fk_Doctor_has_Paciente_Paciente1` FOREIGN KEY (`Paciente_id_Paciente`) REFERENCES `paciente` (`id_Paciente`);

--
-- Filtros para la tabla `paciente_has_estudio`
--
ALTER TABLE `paciente_has_estudio`
  ADD CONSTRAINT `fk_paciente_has_estudio_doctor1` FOREIGN KEY (`doctor_id_Doctor`) REFERENCES `doctor` (`id_Doctor`),
  ADD CONSTRAINT `fk_Paciente_has_Estudio_Estudio1` FOREIGN KEY (`Estudio_id_Estudio`) REFERENCES `estudio` (`id_Estudio`),
  ADD CONSTRAINT `fk_paciente_has_estudio_obrasocial1` FOREIGN KEY (`obrasocial_id_obraSocial`) REFERENCES `obrasocial` (`id_obraSocial`),
  ADD CONSTRAINT `fk_Paciente_has_Estudio_Paciente` FOREIGN KEY (`Paciente_id_Paciente`) REFERENCES `paciente` (`id_Paciente`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
