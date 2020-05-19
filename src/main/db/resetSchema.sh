#!/bin/bash
PGPASSWORD=${db.hangman.password} psql -h ${db.host} -p ${db.port} -U ${db.hangman.username} -d ${db.hangman} < schema.sql
PGPASSWORD=${db.hangman.password} psql -h ${db.host} -p ${db.port} -U ${db.hangman.username} -d ${db.hangman} < initDB.sql