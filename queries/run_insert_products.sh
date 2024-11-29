#!/bin/bash

# Variables
USER="root"
PASSWORD="root"
DATABASE="two_weeks_backend"
SQL_FILE="./insert_products.sql"

# Command to run SQL script
mysql -u $USER -p$PASSWORD $DATABASE < $SQL_FILE
