# java-hangman

Java implementation of hangman.

## Motivation

This project can be used as an entry-point to learn about different Java Tools/Frameworks. Hangman is very simple game but has lot of room to expand the
complexity of the code.
- Hard-coded Single word -> Words per Topic in a Database
- Single App -> Client-Server
- CLI -> Web GUI
- Etc


## Game rules

This is one of the best known games worldwide, still, there's no harm in having the rules stated explicitly:

- A secret word is selected
- A blank line per letter is drawn
- The gallow is drawn
- Player tries to guess one letter. If exists, all occurrences of the letter are written. Otherwise another part of the man is drawn (Head, Body, one leg at a time, one arm at a time)
- Wins if word is complete before all man is drawn (i.e. 6 misses)

## DB

Makes use of postgreSQL database. There are 3 scripts that get packed into `build/libs/db.zip`

- `db.sql` Creates hangman database and hangman_user to access it. Should be executed as admin
- `schema.sql` Creates database schema. Should be executed as hangman_user
-``initDB.sql` Inserts some data so that the game is playable. Should be executed as hangman_user

And also 2 helper shell scripts

- `setupDB.sh` (Re)create DB and invokes `resetSchema.sh`
- `resetSchema.sh` (Re)create tables and inserts some initial words to be played
