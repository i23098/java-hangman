#!/bin/bash
PGPASSWORD=password psql -h 127.0.0.1 -p 5432 -U hangman_user -d hangman < schema.sql
PGPASSWORD=password psql -h 127.0.0.1 -p 5432 -U hangman_user -d hangman < initDB.sql