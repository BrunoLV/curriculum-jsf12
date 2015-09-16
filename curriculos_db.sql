-- phpMyAdmin SQL Dump
-- version 4.0.10deb1
-- http://www.phpmyadmin.net
--
-- Máquina: localhost
-- Data de Criação: 13-Set-2015 às 23:27
-- Versão do servidor: 5.5.44-0ubuntu0.14.04.1
-- versão do PHP: 5.5.9-1ubuntu4.11

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de Dados: `curriculos_db`
--

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_cargo`
--

CREATE TABLE IF NOT EXISTS `tb_cargo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(70) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Extraindo dados da tabela `tb_cargo`
--

INSERT INTO `tb_cargo` (`id`, `nome`) VALUES
(11, 'Analista de Sistemas Jr'),
(12, 'Analista de Sistemas Pl'),
(13, 'Analista de Sistemas Sr'),
(14, 'Analista Programador Java');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_curriculo`
--

CREATE TABLE IF NOT EXISTS `tb_curriculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `id_usuario` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK4AB209DBB0A105EC` (`id_usuario`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=14 ;

--
-- Extraindo dados da tabela `tb_curriculo`
--

INSERT INTO `tb_curriculo` (`id`, `id_usuario`) VALUES
(9, 2),
(10, 2),
(11, 2),
(12, 2),
(13, 2);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_empresa`
--

CREATE TABLE IF NOT EXISTS `tb_empresa` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `nome` (`nome`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=6 ;

--
-- Extraindo dados da tabela `tb_empresa`
--

INSERT INTO `tb_empresa` (`id`, `nome`) VALUES
(2, 'Allianz Seguros'),
(3, 'Azul Seguros'),
(4, 'Itau Seguros'),
(5, 'Porto Seguro Seguros');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_exp_prof_curriculo`
--

CREATE TABLE IF NOT EXISTS `tb_exp_prof_curriculo` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_inicio` date NOT NULL,
  `data_saida` date DEFAULT NULL,
  `id_cargo` int(11) DEFAULT NULL,
  `id_curriculo` int(11) DEFAULT NULL,
  `id_empresa` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `id` (`id`),
  KEY `FK981710D9CDF8E068` (`id_curriculo`),
  KEY `FK981710D9FEE1A0A2` (`id_empresa`),
  KEY `FK981710D95B1B9308` (`id_cargo`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=32 ;

--
-- Extraindo dados da tabela `tb_exp_prof_curriculo`
--

INSERT INTO `tb_exp_prof_curriculo` (`id`, `data_inicio`, `data_saida`, `id_cargo`, `id_curriculo`, `id_empresa`) VALUES
(27, '2015-09-17', '2015-09-30', 14, 12, 4),
(29, '2015-09-08', '2015-09-23', 14, 11, 4),
(30, '2015-09-16', '2015-09-30', 12, 13, 2),
(31, '2015-09-09', '2015-09-30', 13, 13, 5);

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_senha`
--

CREATE TABLE IF NOT EXISTS `tb_senha` (
  `id_usuario` int(11) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_usuario`
--

CREATE TABLE IF NOT EXISTS `tb_usuario` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `nome` varchar(50) NOT NULL,
  `email` varchar(100) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `email` (`email`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Extraindo dados da tabela `tb_usuario`
--

INSERT INTO `tb_usuario` (`id`, `nome`, `email`) VALUES
(2, 'Bruno Luiz Viana', 'brunolviana@yahoo.com.br'),
(3, 'Douglas Viana', 'douglas.viana@yahoo.com.br');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_usuario_papel`
--

CREATE TABLE IF NOT EXISTS `tb_usuario_papel` (
  `id_usuario` int(11) NOT NULL,
  `papel` varchar(70) NOT NULL,
  PRIMARY KEY (`id_usuario`,`papel`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb_usuario_papel`
--

INSERT INTO `tb_usuario_papel` (`id_usuario`, `papel`) VALUES
(2, 'ADMIN');

-- --------------------------------------------------------

--
-- Estrutura da tabela `tb_usuario_senha`
--

CREATE TABLE IF NOT EXISTS `tb_usuario_senha` (
  `id_usuario` int(11) NOT NULL,
  `senha` varchar(255) NOT NULL,
  PRIMARY KEY (`id_usuario`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Extraindo dados da tabela `tb_usuario_senha`
--

INSERT INTO `tb_usuario_senha` (`id_usuario`, `senha`) VALUES
(2, '123456');

--
-- Constraints for dumped tables
--

--
-- Limitadores para a tabela `tb_curriculo`
--
ALTER TABLE `tb_curriculo`
  ADD CONSTRAINT `FK4AB209DBB0A105EC` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id`);

--
-- Limitadores para a tabela `tb_exp_prof_curriculo`
--
ALTER TABLE `tb_exp_prof_curriculo`
  ADD CONSTRAINT `FK981710D95B1B9308` FOREIGN KEY (`id_cargo`) REFERENCES `tb_cargo` (`id`),
  ADD CONSTRAINT `FK981710D9CDF8E068` FOREIGN KEY (`id_curriculo`) REFERENCES `tb_curriculo` (`id`),
  ADD CONSTRAINT `FK981710D9FEE1A0A2` FOREIGN KEY (`id_empresa`) REFERENCES `tb_empresa` (`id`);

--
-- Limitadores para a tabela `tb_senha`
--
ALTER TABLE `tb_senha`
  ADD CONSTRAINT `tb_senha_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id`);

--
-- Limitadores para a tabela `tb_usuario_papel`
--
ALTER TABLE `tb_usuario_papel`
  ADD CONSTRAINT `tb_usuario_papel_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id`);

--
-- Limitadores para a tabela `tb_usuario_senha`
--
ALTER TABLE `tb_usuario_senha`
  ADD CONSTRAINT `tb_usuario_senha_ibfk_1` FOREIGN KEY (`id_usuario`) REFERENCES `tb_usuario` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
