<?php
$conn = mysqli_connect('', "", "", "");
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
    while($rows = $result->fetch_assoc()){
        $fetchData[] = $rows;
    }

    $data["expense"] = $fetchData;
    $data["status"] = true;

}
else
{
    $data["message"] = "Invalid";
    $data["status"] = false;
}

echo json_encode($data);

