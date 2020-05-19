#!/bin/bash
PGPASSWORD=${db.admin.password} psql -h ${db.host} -p ${db.port} -U ${db.admin.username} < db.sql
./resetSchema.sh