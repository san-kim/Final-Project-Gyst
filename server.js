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
            console.log(result);
            if (result.length > 0) {
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
    console.log('uname: ' + username);
    console.log('pword: ' + password);
    let response = "";

    usernameExists(username).then((uname) => {
        if (uname) {
            response = "uname";
        }
        res.send(response);
    });

    validatePassword(username, password).then((valid) => {
        if (valid) {
            getIdFromName(username).then((uname) => {
                response = {
                    username: username,
                    id: uname
                };
            }).catch((error) => {
                console.log(error);
            });
        } 
        else {
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

// get all todo events
app.get('/gettodo', (req, res) =>{
    // let username = req.query.username;
    let userid = req.query.id;
    let response = "";
    getTodoEvents(userid).then((result) => {
        for (var i = 0; i < result.length; i++){
            response += "<a onclick='showtodoinfo(" + i + ");'>" + result[i].name + "</a><br>";
        }
    });
    res.send(response);
});

// get todo ending soonest
app.get('/getnexttodo', (req, res) => {
    // let username = req.query.username;
    let userid = req.query.id;
    let response = "";
    getTodoEvents(userid).then((result) => {
        response = result[0].name;
    });
    res.send(response);
});

app.post('/addtodo', (req, res) => {
    let userid = req.query.id;
    let response;
    let event = {
        userid: userid,
        name: req.query.name,
        location: req.query.location,
        start: req.query.start,
        end: req.query.end,
        notes: req.query.notes,
        block: false
    };
    addTodoEvent(event);
});

function addTodoEvent(event){
    var query = 'INSERT INTO ToDoEvents(User_ID, Event_name, location, Start_time, End_time, User_block, notes) VALUES(' + event.userid + ",\'" + event.name + "\',\'" + event.location + "\',\'" + event.start + "\',\'" + event.end + "\'," + event.block + ",\'"  + event.notes + "\')";
    con.query(query, function (err, result){
        if (err) throw err;
        console.log('added todo event');
    });
}

// get info for a todo with given event id
app.get('/showtodoinfo', (req, res) => {
    let userid = req.query.id;
    let eventid = req.query.eventid;
    let response = "";
    getTodoEvent(eventid).then((event) => {
        response += "<p id='todotitle'>To Do Event Details</p>";
        response += "<p>event name: " + event.eventname + "</p>";
        response += "<p>location: " + event.location + "</p>";
        response += "<p>start: " + event.start + "</p>";
        response += "<p>end: " + event.end + "</p>";
        response += "<p>notes: " + event.notes + "</p>";
        response += "<button id='closebutton' onclick='exittodoinfo();'>close</button>";
    });
    res.send(response);
});

function getTodoEvent(id){
    return new Promise( (resolve, reject) => {
        var query = 'SELECT * FROM ToDoEvents WHERE Event_ID=' + id;
        con.query(query, function (err, result) {
            if (err) throw err;
            var event = {
                eventname: result[0].Event_name,
                location: result[0].location,
                start: result[0].Start_time,
                end: result[0].End_time,
                notes: result[0].notes,
            };
            resolve(event);
        });
    });
}

function getTodoEvents(id){
    return new Promise( (resolve, reject) => {
        var query = 'SELECT * FROM ToDoEvents WHERE User_ID=' + id + ' ORDER BY End_time ASC';
        con.query(query, function (err, result) {
            if (err)  throw err;
            var array = [];
            if (result.length < 1){
                reject('no to-do events');
            }
            for (var i = 0; i < result.length; i++){
                array.push({
                    user: id,
                    name: result[i].Event_name,
                    loc: result[i].location,
                    start: result[i].Start_time,
                    end: result[i].End_time
                });
            }
            resolve(array);
        });
    });
}

app.listen(port, () => console.log(`NODE TEST SERVER HOSTED ON PORT ${port}!`));

