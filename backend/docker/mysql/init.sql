-- Create the database if it doesn't exist (this might be redundant since MYSQL_DATABASE already does this)
CREATE DATABASE IF NOT EXISTS api_gateway_db;

-- Create a new user if it doesn't exist
CREATE USER IF NOT EXISTS 'api_user'@'%' IDENTIFIED BY 'password';

-- Grant all privileges on the database to the new user
GRANT ALL PRIVILEGES ON api_gateway.* TO 'api_user'@'%';

-- Apply the changes
FLUSH PRIVILEGES;
