#!/usr/bin/env bash
echo "Clean local docker MySQL..."

mysql --protocol=tcp -P 13308 -u root -proot -e "DROP DATABASE IF EXISTS ecommerce_inventory_mysql;CREATE DATABASE IF NOT EXISTS ecommerce_inventory_mysql DEFAULT CHARACTER SET utf8mb4 DEFAULT COLLATE utf8mb4_unicode_ci;"