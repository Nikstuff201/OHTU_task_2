# Fuel Consumption and Trip Cost Calculator

## Description
This is a JavaFX application that calculates fuel consumption and total trip cost.  
The application supports multiple languages (English, French, Japanese, Persian) and stores all data in a MySQL database.  
Localization strings are loaded dynamically from the database instead of property files.

---

## Requirements
- Java 17 or higher
- Maven
- MySQL
- DataGrip (or any SQL client)

---

## Database Setup

### Option 1: Using DataGrip (Recommended)

1. Open DataGrip
2. Create a new MySQL connection
3. Use the following settings:
    - Host: localhost
    - Port: 3306
    - User: root
    - Password: your password

---

### Step 1: Create Database

Run:

CREATE DATABASE fuel_calculator_localization;
USE fuel_calculator_localization;

---

### Step 2: Create Tables

Calculations Table:

CREATE TABLE calculations (
id INT AUTO_INCREMENT PRIMARY KEY,
distance DOUBLE,
consumption DOUBLE,
price DOUBLE,
total_fuel DOUBLE,
total_cost DOUBLE,
language_code VARCHAR(10),
created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

Localization Table:

CREATE TABLE localization_strings (
id INT AUTO_INCREMENT PRIMARY KEY,
`key` VARCHAR(100),
value TEXT,
language VARCHAR(10)
);

---

### Step 3: Insert Sample Data

INSERT INTO localization_strings (`key`, value, language) VALUES

-- English
('distance.label', 'Distance (km)', 'en'),
('consumption.label', 'Fuel Consumption (L/100 km)', 'en'),
('price.label', 'Fuel Price (per liter)', 'en'),
('calculate.button', 'Calculate Trip Cost', 'en'),
('result.label', 'Total fuel needed: {0} L | Total cost: {1}', 'en'),
('invalid.input', 'Wrong input', 'en'),

-- French
('distance.label', 'Distance (km)', 'fr'),
('consumption.label', 'Consommation de carburant (L/100 km)', 'fr'),
('price.label', 'Prix du carburant (par litre)', 'fr'),
('calculate.button', 'Calculer le coût du trajet', 'fr'),
('result.label', 'Carburant nécessaire : {0} L | Coût total : {1}', 'fr'),
('invalid.input', 'Entrée invalide', 'fr'),

-- Japanese
('distance.label', '距離 (km)', 'ja'),
('consumption.label', '燃料消費量 (L/100 km)', 'ja'),
('price.label', '燃料価格 (1リットルあたり)', 'ja'),
('calculate.button', '旅行の費用を計算', 'ja'),
('result.label', '必要な燃料: {0} L | 合計費用: {1}', 'ja'),
('invalid.input', '無効な入力', 'ja'),

-- Persian
('distance.label', 'مسافت (کیلومتر)', 'fa'),
('consumption.label', 'مصرف سوخت (لیتر در 100 کیلومتر)', 'fa'),
('price.label', 'قیمت سوخت (برای هر لیتر)', 'fa'),
('calculate.button', 'محاسبه هزینه سفر', 'fa'),
('result.label', 'سوخت مورد نیاز: {0} لیتر | هزینه کل: {1}', 'fa'),
('invalid.input', 'ورودی نامعتبر', 'fa');

---

## Database Connection Configuration

The application connects to the database using:

private static final String URL = "jdbc:mysql://localhost:3306/fuel_calculator_localization";
private static final String USER = "root";
private static final String PASSWORD = "your_password";

Important:
- Replace "your_password" with your actual MySQL password
- Make sure MySQL is running on port 3306

---

## Running the Application

Run using Maven:

mvn javafx:run

Or run the project directly from IntelliJ IDEA.

---

## Features
- Fuel consumption calculation
- Trip cost calculation
- Multi-language support (database-based)
- Data persistence in database
- Dynamic UI localization

---

## Summary

To run the project:
1. Create database
2. Create tables
3. Insert sample data
4. Configure database connection
5. Run the application

---

## Author
Nikita Rybakov