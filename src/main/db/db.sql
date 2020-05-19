DROP DATABASE IF EXISTS hangman;

DROP USER IF EXISTS hangman_user;

CREATE USER hangman_user with password 'password';

CREATE DATABASE hangman
OWNER hangman_user
ENCODING 'UTF8';
