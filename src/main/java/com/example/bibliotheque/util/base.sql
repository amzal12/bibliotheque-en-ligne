-- Table Personne avec une colonne "role"
DROP TABLE IF EXISTS Personne;
CREATE TABLE IF NOT EXISTS Personne (
    idPersonne INT(11) NOT NULL AUTO_INCREMENT PRIMARY KEY,   -- ID unique, incrémentation automatique
    nom VARCHAR(50) NULL,                                     -- Nom de la personne
    nomUtilisateur VARCHAR(255) NULL,                         -- Nom d'utilisateur de la personne
    telephone VARCHAR(20) NULL,                               -- Téléphone de la personne
    mail VARCHAR(50) NULL,                                    -- Email de la personne
    motDePasse VARCHAR(255) NULL,                             -- Mot de passe (si applicable)
    role ENUM('Utilisateur', 'Gestionnaire', 'Auteur') NULL   -- Rôle de la personne
);

-- Table Auteur qui réutilise idPersonne
DROP TABLE IF EXISTS Auteur;
CREATE TABLE Auteur (
    idPersonne INT PRIMARY KEY, -- Utilisation de idPersonne comme clé primaire
    nationalite VARCHAR(50),
    biographie TEXT,
    FOREIGN KEY (idPersonne) REFERENCES Personne(idPersonne)
);

-- Table Utilisateur sans redondance d'ID
DROP TABLE IF EXISTS Utilisateur;
CREATE TABLE Utilisateur (
    idPersonne INT PRIMARY KEY, -- Utilisation de idPersonne comme clé primaire
    FOREIGN KEY (idPersonne) REFERENCES Personne(idPersonne)
);

-- Table Gestionnaire sans redondance d'ID
DROP TABLE IF EXISTS Gestionnaire;
CREATE TABLE Gestionnaire (
    idPersonne INT PRIMARY KEY, -- Utilisation de idPersonne comme clé primaire
    FOREIGN KEY (idPersonne) REFERENCES Personne(idPersonne)
);

-- Table Document avec contrainte CHECK pour qteTotale et qteDisponible
DROP TABLE IF EXISTS Document;
CREATE TABLE Document (
    idDocument INT PRIMARY KEY,
    datePublication DATE,
    typeDocument ENUM('Livre', 'Journal', 'Magazine', 'OuvrageMultimedia'),
    editeur VARCHAR(50),
    titre VARCHAR(100),
    imageUrl VARCHAR(200),
    qteTotale INT,
    qteDisponible INT,
    CHECK (qteDisponible >= 0 AND qteDisponible <= qteTotale) -- Contrainte de quantité
);

-- Table Livre
DROP TABLE IF EXISTS Livre;
CREATE TABLE Livre (
    idDocument INT PRIMARY KEY,
    isbn VARCHAR(20),
    genre VARCHAR(50),
    nombrePages INT,
    FOREIGN KEY (idDocument) REFERENCES Document(idDocument)
);

-- Table Journal sans la colonne idJournal redondante
DROP TABLE IF EXISTS Journal;
CREATE TABLE Journal (
    idDocument INT PRIMARY KEY,
    periodicite VARCHAR(50),
    FOREIGN KEY (idDocument) REFERENCES Document(idDocument)
);

-- Table Magazine sans la colonne idMagazine redondante
DROP TABLE IF EXISTS Magazine;
CREATE TABLE Magazine (
    idDocument INT PRIMARY KEY,
    frequence VARCHAR(50),
    numParution INT,
    FOREIGN KEY (idDocument) REFERENCES Document(idDocument)
);

-- Table OuvrageMultimedia avec la colonne durée en minutes
DROP TABLE IF EXISTS OuvrageMultimedia;
CREATE TABLE OuvrageMultimedia (
    idDocument INT PRIMARY KEY,
    typeOuvrage VARCHAR(50),
    duree INT, -- Durée en minutes
    FOREIGN KEY (idDocument) REFERENCES Document(idDocument)
);

-- Table Emprunt avec contrainte CHECK sur dateRetour et dateEmprunt
DROP TABLE IF EXISTS Emprunt;
CREATE TABLE Emprunt (
    idEmprunt INT PRIMARY KEY,
    idUtilisateur INT,  -- Utilise idPersonne pour l'utilisateur (si role = 'Utilisateur')
    idGestionnaire INT, -- Utilise idPersonne pour le gestionnaire (si role = 'Gestionnaire')
    idDocument INT,
    dateEmprunt DATE,
    dateRetour DATE,
    etat ENUM('emprunté', 'retourné', 'endommagé'),
    FOREIGN KEY (idDocument) REFERENCES Document(idDocument),
    FOREIGN KEY (idUtilisateur) REFERENCES Personne(idPersonne), -- Référence à Personne pour l'utilisateur
    FOREIGN KEY (idGestionnaire) REFERENCES Personne(idPersonne), -- Référence à Personne pour le gestionnaire
    CHECK (dateRetour >= dateEmprunt) -- Vérification que la date de retour est après la date d'emprunt
);

-- Table Ecrire
DROP TABLE IF EXISTS Ecrire;
CREATE TABLE Ecrire (
    idAuteur INT,
    idDocument INT,
    PRIMARY KEY (idAuteur, idDocument),
    FOREIGN KEY (idAuteur) REFERENCES Auteur(idPersonne),
    FOREIGN KEY (idDocument) REFERENCES Document(idDocument)
);

