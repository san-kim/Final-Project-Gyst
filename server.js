const express = require('express');
const app = express();
const port = 3000;
const mysql = require('mysql');
const con = mysql.createConnection({
    host: "localhost",
    user: "root",
    password: "password123",
    database: "Gyst",
    insecureAuth: true
});


function initializeMySQL(){
    con.connect(function (err) {
        if (err) throw err;
        console.log("Connected!");
    });
}

initializeMySQL();

// function getEvents(){

// }


app.get('/getevents', (req, res) => {
    var id = req.query.id;
    var array = [];
    var query = 'SELECT * FROM User_Events WHERE User_ID=' + id;
    con.query(query, function (err, result) {
        if (err) throw err;
        // console.log(result.length);
        for (let i = 0; i < result.length; i++){
            let topush = {
                title: result[i].Event_name,
                start: result[i].Start_time,
                end: result[i].End_time,
                id: result[i].Event_ID
            };
            // console.log(topush);
            array.push(topush);
            console.log(array[i]);
            // console.log("title: " + result[i].Event_name);
            // console.log("start: " + result[i].Start_time);
            // console.log("end: " + result[i].End_time);
            // console.log("id: " + result[i].Event_ID);
        }
        res.send(array);
    });
    
    // console.log(array);
    
});



app.listen(port, () => console.log(`Example app listening on port ${port}!`));

