-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Hôte : 127.0.0.1
-- Généré le : lun. 04 mars 2024 à 23:04
-- Version du serveur : 10.4.28-MariaDB
-- Version de PHP : 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données : `pidev`
--

-- --------------------------------------------------------

--
-- Structure de la table `annonce`
--

CREATE TABLE `annonce` (
  `id_Annonce` int(11) NOT NULL,
  `Nom_du_plat` varchar(255) DEFAULT NULL,
  `Description_du_plat` text DEFAULT NULL,
  `prix` float DEFAULT NULL,
  `userID` int(11) DEFAULT NULL,
  `Ingredients` text DEFAULT NULL,
  `Categorie_de_plat` varchar(255) DEFAULT NULL,
  `image_plat` varchar(255) DEFAULT NULL,
  `quantite` int(11) NOT NULL,
  `adresse` varchar(255) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `annonce`
--

INSERT INTO `annonce` (`id_Annonce`, `Nom_du_plat`, `Description_du_plat`, `prix`, `userID`, `Ingredients`, `Categorie_de_plat`, `image_plat`, `quantite`, `adresse`) VALUES
(158, '00', '00', 0, 2, '00', '00', 'C:\\Users\\nourb\\OneDrive\\Desktop\\MEKLA\\Couscous.jpg', 0, '00'),
(159, 'aa', 'aa', 1, 2, 'aa', 'aa', 'C:\\Users\\nourb\\OneDrive\\Desktop\\MEKLA\\mloukheya.jpg', 1, 'a'),
(160, 'aa', 'aa', 12, 5, 'aa', 'aa', 'C:\\Users\\nourb\\OneDrive\\Desktop\\Exam\\Gestion projetCovoiturage esprit\\diag cas utilisation covoiturage.PNG', 12, 'aaa'),
(161, 'aa', 'aa', 0, 5, 'aaa', 'aaa', 'C:\\Users\\nourb\\OneDrive\\Desktop\\Exam\\Gestion projetCovoiturage esprit\\nv diag class conception covoiturage reserver place.PNG', 0, 'q'),
(166, '00', '00', 0, 2, '00', '00', 'C:\\Users\\nourb\\OneDrive\\Desktop\\MEKLA\\Couscous.jpg', 0, '00'),
(167, 'AA', 'AA', 11, 2, 'AAA', 'AA', 'C:\\Users\\nourb\\OneDrive\\Desktop\\MEKLA\\Couscous.jpg', 11, 'AA'),
(168, 'carbonara', '00', 0, 2, '00', '00', 'C:\\Users\\nourb\\OneDrive\\Desktop\\MEKLA\\mloukheya.jpg', 0, '00');

-- --------------------------------------------------------

--
-- Structure de la table `client`
--

CREATE TABLE `client` (
  `id_client` int(11) NOT NULL,
  `nom` varchar(255) DEFAULT NULL,
  `prenom` varchar(255) DEFAULT NULL,
  `numero_telephone` varchar(20) DEFAULT NULL,
  `adresse_email` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `commande`
--

CREATE TABLE `commande` (
  `id_commande` int(11) NOT NULL,
  `id_client` int(11) DEFAULT NULL,
  `prix_total` int(11) DEFAULT NULL,
  `adresse` varchar(255) DEFAULT NULL,
  `id_panier` int(11) DEFAULT NULL,
  `etat_livraison` varchar(255) DEFAULT NULL,
  `tempsCommande` timestamp NOT NULL DEFAULT current_timestamp(),
  `archive` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `commande`
--

INSERT INTO `commande` (`id_commande`, `id_client`, `prix_total`, `adresse`, `id_panier`, `etat_livraison`, `tempsCommande`, `archive`) VALUES
(1, 1, 0, 'hh', 1, 'Ramassage direct', '2024-03-04 15:28:34', 0),
(2, 1, 1, 'HH', 1, 'Ramassage direct', '2024-03-04 15:30:15', 0),
(3, 1, 0, 'hh', 1, 'Ramassage direct', '2024-03-04 15:34:04', 0),
(4, 1, 24, 'aa', 1, 'Ramassage direct', '2024-03-04 19:05:29', 0),
(5, 1, 0, 'AA', 1, 'Ramassage direct', '2024-03-04 19:08:22', 0),
(6, 1, 0, 'hjhgh', 1, 'Ramassage direct', '2024-03-04 19:11:00', 0),
(7, 1, 0, 'kljk', 1, 'Ramassage direct', '2024-03-04 19:13:15', 0),
(8, 2, 0, 'k', 2, 'Ramassage direct', '2024-03-04 19:14:45', 1),
(9, 1, 0, 'klj', 1, 'Ramassage direct', '2024-03-04 19:24:13', 0),
(10, 2, 26, 'khkj', 2, 'Ramassage direct', '2024-03-04 19:29:33', 1),
(11, 2, 0, 'llll', 2, 'Ramassage direct', '2024-03-04 22:02:19', 0);

-- --------------------------------------------------------

--
-- Structure de la table `commentaire`
--

CREATE TABLE `commentaire` (
  `commentaireId` int(11) NOT NULL,
  `userId` int(11) DEFAULT NULL,
  `id_Annonce` int(11) DEFAULT NULL,
  `comment` varchar(255) DEFAULT NULL,
  `likes_count` int(11) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Structure de la table `panier`
--

CREATE TABLE `panier` (
  `id_panier` int(11) NOT NULL,
  `id_client` int(11) DEFAULT NULL,
  `dateCreation` date DEFAULT NULL,
  `dateModification` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `panier`
--

INSERT INTO `panier` (`id_panier`, `id_client`, `dateCreation`, `dateModification`) VALUES
(1, 1, NULL, NULL),
(2, 2, NULL, NULL),
(4, 4, NULL, NULL),
(5, 5, NULL, NULL),
(6, 6, NULL, NULL);

-- --------------------------------------------------------

--
-- Structure de la table `panier_annonce`
--

CREATE TABLE `panier_annonce` (
  `id_panier` int(11) DEFAULT NULL,
  `id_annonce` int(11) DEFAULT NULL,
  `id_panier_annonce` int(11) NOT NULL,
  `quantite` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `panier_annonce`
--

INSERT INTO `panier_annonce` (`id_panier`, `id_annonce`, `id_panier_annonce`, `quantite`) VALUES
(4, 168, 13, 1),
(4, 160, 14, 3),
(4, 158, 15, 1),
(5, 161, 19, 1);

-- --------------------------------------------------------

--
-- Structure de la table `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `Nom` varchar(255) NOT NULL,
  `Prenom` varchar(255) NOT NULL,
  `Email` varchar(255) NOT NULL,
  `Numero` varchar(255) NOT NULL,
  `Password` varchar(255) NOT NULL,
  `Role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Déchargement des données de la table `user`
--

INSERT INTO `user` (`UserID`, `Nom`, `Prenom`, `Email`, `Numero`, `Password`, `Role`) VALUES
(1, 'Nour', 'Boujenoui', '11', '9999999', '11', 'client'),
(2, 'x', 'y', '00', '', '00', 'chef'),
(3, '', '', '22', '', '22', 'client'),
(4, '', '', '33', '', '33', 'chef'),
(5, '', '', '44', '', '44', 'admin'),
(6, '', '', 'aa', '', 'aa', 'livreur');

--
-- Index pour les tables déchargées
--

--
-- Index pour la table `annonce`
--
ALTER TABLE `annonce`
  ADD PRIMARY KEY (`id_Annonce`),
  ADD KEY `FK_UserID_Annonce` (`userID`);

--
-- Index pour la table `commande`
--
ALTER TABLE `commande`
  ADD PRIMARY KEY (`id_commande`),
  ADD KEY `FK_Client` (`id_client`);

--
-- Index pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD PRIMARY KEY (`commentaireId`),
  ADD KEY `annonceId` (`id_Annonce`),
  ADD KEY `FK_UserID` (`userId`);

--
-- Index pour la table `panier`
--
ALTER TABLE `panier`
  ADD PRIMARY KEY (`id_panier`),
  ADD KEY `id_client` (`id_client`);

--
-- Index pour la table `panier_annonce`
--
ALTER TABLE `panier_annonce`
  ADD PRIMARY KEY (`id_panier_annonce`),
  ADD KEY `fk_panier_annonce` (`id_annonce`),
  ADD KEY `fk_panier` (`id_panier`);

--
-- Index pour la table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`),
  ADD UNIQUE KEY `Email` (`Email`);

--
-- AUTO_INCREMENT pour les tables déchargées
--

--
-- AUTO_INCREMENT pour la table `annonce`
--
ALTER TABLE `annonce`
  MODIFY `id_Annonce` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=169;

--
-- AUTO_INCREMENT pour la table `commande`
--
ALTER TABLE `commande`
  MODIFY `id_commande` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=12;

--
-- AUTO_INCREMENT pour la table `commentaire`
--
ALTER TABLE `commentaire`
  MODIFY `commentaireId` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=148;

--
-- AUTO_INCREMENT pour la table `panier_annonce`
--
ALTER TABLE `panier_annonce`
  MODIFY `id_panier_annonce` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=35;

--
-- AUTO_INCREMENT pour la table `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;

--
-- Contraintes pour les tables déchargées
--

--
-- Contraintes pour la table `annonce`
--
ALTER TABLE `annonce`
  ADD CONSTRAINT `FK_UserID_Annonce` FOREIGN KEY (`userID`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commande`
--
ALTER TABLE `commande`
  ADD CONSTRAINT `FK_Client` FOREIGN KEY (`id_client`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `commentaire`
--
ALTER TABLE `commentaire`
  ADD CONSTRAINT `FK_UserID` FOREIGN KEY (`userId`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `commentaire_ibfk_1` FOREIGN KEY (`id_Annonce`) REFERENCES `annonce` (`id_Annonce`);

--
-- Contraintes pour la table `panier`
--
ALTER TABLE `panier`
  ADD CONSTRAINT `id_client` FOREIGN KEY (`id_client`) REFERENCES `user` (`UserID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Contraintes pour la table `panier_annonce`
--
ALTER TABLE `panier_annonce`
  ADD CONSTRAINT `fk_panier` FOREIGN KEY (`id_panier`) REFERENCES `panier` (`id_panier`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `fk_panier_annonce` FOREIGN KEY (`id_annonce`) REFERENCES `annonce` (`id_Annonce`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
