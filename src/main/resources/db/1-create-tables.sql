CREATE TABLE `zona` (
  `id` int(11) NOT NULL,
  `latitud` double NOT NULL,
  `longitud` double NOT NULL,
  `metrosRedonda` int(11) NOT NULL,
  `nombreDescriptivo` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `categoria` (
  `id` int(11) NOT NULL,
  `cargoFijo` double NOT NULL,
  `cargoVariable` double NOT NULL,
  `consumoMax` int(11) NOT NULL,
  `consumoMin` int(11) NOT NULL,
  `nombre` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `hibernate_sequence` (
  `next_val` bigint(20) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `transformador` (
  `id` int(11) NOT NULL,
  `latitud` double NOT NULL,
  `longitud` double NOT NULL,
  `idZona` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_ZONA` (`idZona`),
  CONSTRAINT `FK_ZONA` FOREIGN KEY (`idZona`) REFERENCES `zona` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `usuario` (
  `id` int(11) NOT NULL,
  `apellido` varchar(255) NOT NULL,
  `domicilio` varchar(255) NOT NULL,
  `fechaAlta` date NOT NULL,
  `nombre` varchar(255) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `user` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `administrador` (
  `legajo` int(11) NOT NULL,
  `id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  CONSTRAINT `FK61htljkn2rhl5r8b02b31i3rr` FOREIGN KEY (`id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

CREATE TABLE `cliente` (
  `ahorroAutomatico` bit(1) NOT NULL,
  `numeroDoc` int(11) NOT NULL,
  `puntaje` int(11) NOT NULL,
  `telefono` int(11) NOT NULL,
  `tipoDoc` varchar(255) NOT NULL,
  `id` int(11) NOT NULL,
  `idCategoria` int(11) DEFAULT NULL,
  `idTransformador` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `UK_rtu1cb0svwawh39e4urldhnru` (`numeroDoc`),
  KEY `FK_CATEGORIA` (`idCategoria`),
  KEY `FK_TRANSFORMADOR` (`idTransformador`),
  CONSTRAINT `FK_CATEGORIA` FOREIGN KEY (`idCategoria`) REFERENCES `categoria` (`id`),
  CONSTRAINT `FK_TRANSFORMADOR` FOREIGN KEY (`idTransformador`) REFERENCES `transformador` (`id`),
  CONSTRAINT `FKc3su85u2uiowlhahitpowhtvo` FOREIGN KEY (`id`) REFERENCES `usuario` (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;
