CREATE TABLE chats (
    id SERIAL PRIMARY KEY,
    nom VARCHAR(100) NOT NULL,
    date_naissance DATE NOT NULL,
    photo TEXT NOT NULL
);