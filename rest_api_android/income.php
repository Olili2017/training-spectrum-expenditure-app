<?php
include 'conn.php';
if ($_SERVER["REQUEST_METHOD"] === 'POST')
{
    $reason = $_POST["name"];
    $amount = $_POST["amount"];
    $date = $_POST["date"];

    $query = "INSERT INTO income (`name`,`amount`,`date`)VALUES('$reason','$amount','$date')";
    $result = $conn->query($query);
    if ($result)
    {
        $data["message"] = "Income added";
        $data["status"] = true;
    }
    else
    {
        $data["message"] = "Failed";
        $data["status"] = false;
    }
}
else
{
    $data["message"] = "Invalid";
    $data["status"] = false;
}
echo json_encode($data);

