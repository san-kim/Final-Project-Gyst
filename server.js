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
var current_user;


function initializeMySQL(){
    con.connect(function (err) {
        if (err) throw err;
        console.log("Connected!");
    });
}

initializeMySQL();

app.get('/getevents', (req, res) => {
    var id = req.query.id;
    var array = [];
    var query = 'SELECT * FROM User_Events WHERE User_ID=' + id;
    con.query(query, function (err, result) {
        if (err) throw err;
        for (let i = 0; i < result.length; i++){
            let topush = {
                title: result[i].Event_name,
                start: result[i].Start_time,
                end: result[i].End_time,
                id: result[i].Event_ID,
                url: "javascript: displayEventDetails("+result[i].Event_ID+")"
            };
            array.push(topush);
            // console.log(array[i]);
        }
        res.json(array);
    });    
});

app.get('/addevent', (req, res) => {
    var name = req.query.eventname;
    var loc = req.query.location;
    var start = req.query.start;
    var end = req.query.end;
    var notes = req.query.notes;
    var id = req.query.id;
    var eventid = generateID();
    var query = "INSERT INTO User_Events(Event_ID, User_ID, Event_name, location, Start_time, End_time, notes, Host_ID) VALUES("+eventid+","+id+","+name+","+loc+","+start+","+end+","+notes+","+0+")";
    var values = [];
    values.push([
        eventid,
        id,
        name,
        loc,
        start,
        end,
        notes
    ]);
    con.escape(query);
    con.query(query, function(err, result) {
        if (err) throw err;
        console.log('added event with id ' + eventid);
    });
    res.location('calendar.html');
});



function generateID(){
    var id = Math.floor(Math.random() * 5000000);
    while (idExists(id)){
        id = Math.floor(Math.random() * 5000000);
    }
    return id;
}

function idExists(id){
    var query = 'SELECT * FROM User_Events WHERE Event_ID=' + id;
    con.query(query, function (err, result) {
        if (err) throw err;
        if (result.length > 0){
            return true;
        }
    });
    return false;
}

function usernameExists(username){
    var query = 'SELECT * FROM UserInfo WHERE Username=' + username;
    con.query(query, function (err, result) {
        if (err) throw err;
        if (result.length > 0) {
            return true;
        }
    });
    return false;
}

function createAccount(username, password){
    var id = generateID();
    var query = 'INSERT INTO UserInfo(User_ID, Username, User_Password) VALUES(' + id + ',\'' + username + '\',\'' + password + '\')';
     con.query(query, function (err, result) {
         if (err) throw err;
         console.log('added user with id ' + id);
     });
}

/*
app.get('/account_servlet', (req, res) => {
    var registering = req.param.registering;
    if (registering != null){
        var response = "";
        if (registering.equals('true')){
            var username = req.param.username;
            var password = req.param.password;
            var confirimpassword = request.param.confirmpassword;
            if (usernameExists(username)){
                response  += "This username is already taken. ";
            }
            else if (!password.equals(confirmpassword)){
                response += "The passwords do not match.";
            }
            else{
                response += 
            }
        }
    }
});
*/


app.listen(port, () => console.log(`NODE TEST SERVER HOSTED ON PORT ${port}!`));
