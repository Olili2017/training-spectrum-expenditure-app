<?php
include 'conn.php';
if ($_SERVER["REQUEST_METHOD"] === 'POST')
{
    $email = $_POST["email"];
    $password = md5($_POST["password"]);

    $query = "SELECT * FROM users WHERE email = '$email' AND password = '$password' LIMIT 1";
    $result = $conn->query($query);

    if ($result->num_rows > 0)
    {
        $userRecords = $result->fetch_assoc();
        $data["message"] = "Login successful";
        $data["data"] = array("id"=>$userRecords['id'], "email"=>$userRecords['email'], );
        $data["status"] = true;
    }
    else
    {
        $data["message"] = "Invalid Credentials";
        $data["status"] = false;
    }
}
else
{
    $data["message"] = "Invalid";
    $data["status"] = false;
}
echo json_encode($data);

