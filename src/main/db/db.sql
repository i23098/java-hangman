DROP DATABASE IF EXISTS ${db.hangman};

DROP USER IF EXISTS ${db.hangman.username};

CREATE USER ${db.hangman.username} with password '${db.hangman.password}';

CREATE DATABASE ${db.hangman}
OWNER ${db.hangman.username}
ENCODING 'UTF8';
