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
    var id = 3003932;
    var query = 'INSERT INTO User_Events(User_ID, Event_name, location, Start_time, End_time, notes, Host_ID) VALUES('+id+',\''+name+'\',\''+loc+'\',\''+start+'\',\''+end+'\',\''+notes+'\',0)';
    con.escape(query);
    con.query(query, function(err, result) {
        if (err) throw err;
        console.log('added event');
    });
    res.location('calendar.html');
});

function usernameExists(username){
    return new Promise( (resolve, reject) => {
        var query = 'SELECT * FROM UserInfo WHERE Username=\'' + username + '\'';
        con.query(query, function (err, result) {
            if (err) throw err;
            if (result.length > 0) {
                resolve(true);
            }
        });
        resolve(false);
    });
}

function createAccount(username, password){
    return new Promise( (resolve, reject) => {
        var query = 'INSERT INTO UserInfo(Username, User_Password) VALUES(' + '\'' + username + '\',\'' + password + '\')';
        con.query(query, function (err, result) {
            if (err) throw err;
            var id = result.insertId;
            console.log('added user with id ' + id);
            resolve(id);
        });
    })
}

function validatePassword(username, password){
    return new Promise( (resolve, reject) => {
        var query = 'SELECT * FROM UserInfo WHERE Username=\'' + username + '\' AND User_Password=\'' + password + '\'';
        con.query(query, function (err, result) {
            if (err) throw err;
            console.log('result');
            console.log(result);
            console.log(result.length);
            if (result.length > 0) {
                console.log('true');
                resolve(true);
            } else {
                resolve(false);
            }
        });
    });
}

function getIdFromName(username){
    return new Promise( (resolve, reject) => {
        var query = 'SELECT * FROM UserInfo WHERE Username=\'' + username + '\'';
        con.query(query, function (err, result) {
            if (err) throw err;
            if (result.length > 0) {
                resolve(result[0].Username);
            } else {
                reject("errorstring");
            }
        });
    })
    
}

// login servlet
app.get('/login', (req, res) => {
    let username = req.query.username;
    let password = req.query.password;
    console.log('uname ' + username);
    console.log('pword ' + password);
    let response;

    usernameExists(username).then((uname) => {
        if (uname) {
            response = "uname";
        }
    });

    validatePassword(username, password).then((valid) => {
        console.log(valid);
        if (valid) {
            console.log(username);
            getIdFromName(username).then((uname) => {
                response = {
                    username: username,
                    id: uname
                };
            }).catch((error) => {
                console.log(error);
            });
        } else {
            response = "pword";
        }
        console.log(response);
        res.send(response);
    });
});

// registration servlet
app.get('/register',  (req, res) => {
    let username = req.query.username;
    let password = req.query.password;
    let confirmpassword = req.query.confirmpassword;
    let response;
    usernameExists(username).then((uname) =>{
        if (uname) {
            response = 'uname';
        }
    });
    if (!password === confirmpassword){
        response = 'pword';
    }
    else{
        createAccount(username, password).then((id) => {
            response = {
                username: username,
                id: id
            };
        });
    }
    res.send(response);
});

// guest login servlet
app.get('/guestlogin', (req, res) => {
    response = {
        username: 'guest',
        id: '-1'};
    res.send(response);
});

app.listen(port, () => console.log(`NODE TEST SERVER HOSTED ON PORT ${port}!`));

