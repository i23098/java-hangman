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