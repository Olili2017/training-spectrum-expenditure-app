<?php
include 'conn.php';
if ($_SERVER["REQUEST_METHOD"] === 'POST')
{
    $email = $_POST["email"];
    $password = md5($_POST["password"]);

    $query = "INSERT INTO users (`email`,`password`)VALUES('$email','$password')";
    $result = $conn->query($query);
    if ($result)
    {
        $data["message"] = "User added";
        $data["status"] = true;
    }
    else
    {
        $data["message"] = "Already signed Up, Try Logining In";
        $data["status"] = false;
    }
}
else
{
    $data["message"] = "Invalid";
    $data["status"] = false;
}
echo json_encode($data);

