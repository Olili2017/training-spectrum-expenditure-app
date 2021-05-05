<?php
include 'conn.php';
if ($_SERVER["REQUEST_METHOD"] === 'GET')
{
    
    $fetchData = array();
    $query = "SELECT * FROM income ORDER BY id DESC LIMIT 50";
    $result = $conn->query($query);
    while($rows = $result->fetch_assoc()){
        $fetchData[] = $rows;
    }
    $data["income"] = $fetchData;

    $query = "SELECT * FROM expense ORDER BY id DESC LIMIT 50";
    $result = $conn->query($query);
    $fetchData2 = array();
    while($rows = $result->fetch_assoc()){
        $fetchData2[] = $rows;
    }

    $data["expense"] = $fetchData2;
    $data["status"] = true;

}
else
{
    $data["message"] = "Invalid";
    $data["status"] = false;
}

echo json_encode($data);
