<?php 
error_reporting(0);
date_default_timezone_set('Africa/Nairobi');
$servername = "localhost";
$db_username = "";
$passwordlocal = "";
$databaseName = "";
$conn = new mysqli($servername, $db_username, $passwordlocal, $databaseName);
if ($conn->connect_error) {
    die("Connection failed: " . $conn->connect_error);
}
$pdo = new PDO("mysql:host=$servername;databaseName=$databaseName", $db_username, $passwordlocal);
$pdo->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
?>