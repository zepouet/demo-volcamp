-- Création de la table chats
CREATE TABLE IF NOT EXISTS chats (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    date_naissance DATE NOT NULL,
    photo BYTEA NOT NULL
);

-- Exemple d'insertion de chats avec des images en Base64 (pour simuler des photos)
INSERT INTO chats (nom, date_naissance, photo) VALUES 
('Minou', '2019-04-12', DECODE('iVBORw0KGgoAAAANSUhEUgAAAAUA', 'base64')), 
('Felix', '2018-09-23', DECODE('R0lGODlhEAAQAMQfABwZZgQK', 'base64')), 
('Gribouille', '2020-01-01', DECODE('Qk02aQAAAAAAADYAAAAoAAAA', 'base64'));

-- Ajoute plus de chats si nécessaire avec des chaînes d'images en Base64 différentes.
