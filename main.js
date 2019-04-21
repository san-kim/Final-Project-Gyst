const { app, BrowserWindow } = require('electron');
let fs = require('fs');
let os = require('os');
let sql = require('sql.js');
const REDDIT = 0;
const FACEBOOK = 1;
const TWITTER = 2;
const YOUTUBE = 3;
const AMAZON = 4;
const INSTAGRAM = 5;
const NETFLIX = 6;
const TWITCH = 7;



// Keep a global reference of the window object, if you don't, the window will
// be closed automatically when the JavaScript object is garbage collected.
let win;

function getHistory(){
    var history_file;
    // FIXME: can't get current username --> current implementation doesn't work
    if (process.platform == 'win32'){
        // if current platform is windows
        let currentUser = os.userInfo("username");
        history_file = "C:\\Users\\" + currentUser + "\\AppData\\Local\\Google\\Chrome\\UserData\\Default\\History";
    }
    else if (process.platform == 'darwin'){
        let currentUser = os.userInfo("username");
        currentUser = "achakicherla"; // for testing purposes
        history_file = "/Users/" + currentUser + "/Library/Application\ Support/Google/Chrome/Default/History";
    }
    else{
        let currentUser = os.userInfo("username");
        history_file = "/home/" + currentUser + "/.config/google-chrome/default/History";
    }



    var databaseFile = fs.readFileSync(history_file);
    var db = new sql.Database(databaseFile);

    var urlMap = new Map(); // create new map
   

    // now get url activity from each site

    // REDDIT
    var result = db.exec("SELECT * FROM urls WHERE url LIKE \'%reddit%\'");

    var output = result.values().next().value.values;
    var redditArray = [];

    for (let i = 0; i < output.size(); i++){
        let obj = output[i];
        redditArray.push(obj[1]);
    }

    urlMap.set(REDDIT, redditArray); // add reddit urls to map

    // FACEBOOK
    result = db.exec("SELECT * FROM urls WHERE url LIKE \'%facebook%\'");

    output = result.values().next().value.values;
    var facebookArray = [];

    for (let i = 0; i < output.size(); i++) {
        let obj = output[i];
        facebookArray.push(obj[1]);
    }

    urlMap.set(FACEBOOK, facebookArray); // add facebook urls to map

    // TWITTER
    result = db.exec("SELECT * FROM urls WHERE url LIKE \'%twitter%\'");

    output = result.values().next().value.values;
    var twitterArray = [];

    for (let i = 0; i < output.size(); i++) {
        let obj = output[i];
        twitterArray.push(obj[1]);
    }

    urlMap.set(TWITTER, twitterArray); // add twitter urls to map

    // YOUTUBE
    result = db.exec("SELECT * FROM urls WHERE url LIKE \'%youtube%\'");

    output = result.values().next().value.values;
    var youtubeArray = [];

    for (let i = 0; i < output.size(); i++) {
        let obj = output[i];
        youtubeArray.push(obj[1]);
    }

    urlMap.set(YOUTUBE, youtubeArray); // add youtube urls to map

    // AMAZON
    result = db.exec("SELECT * FROM urls WHERE url LIKE \'%amazon%\'");

    output = result.values().next().value.values;
    var amazonArray = [];

    for (let i = 0; i < output.size(); i++) {
        let obj = output[i];
        amazonArray.push(obj[1]);
    }

    urlMap.set(AMAZON, amazonArray); // add amazon urls to map

    // INSTAGRAM
    result = db.exec("SELECT * FROM urls WHERE url LIKE \'%instagram%\'");

    output = result.values().next().value.values;
    var instaArray = [];

    for (let i = 0; i < output.size(); i++) {
        let obj = output[i];
        instaArray.push(obj[1]);
    }

    urlMap.set(INSTAGRAM, instaArray); // add instagram urls to map

    // NETFLIX
    result = db.exec("SELECT * FROM urls WHERE url LIKE \'%netflix%\'");

    output = result.values().next().value.values;
    var netflixArray = [];

    for (let i = 0; i < output.size(); i++) {
        let obj = output[i];
        netflixArray.push(obj[1]);
    }

    urlMap.set(NETFLIX, netflixArray); // add netflix urls to map

    // TWITCH
    result = db.exec("SELECT * FROM urls WHERE url LIKE \'%twitch%\'");

    output = result.values().next().value.values;
    var twitchArray = [];

    for (let i = 0; i < output.size(); i++) {
        let obj = output[i];
        twitchArray.push(obj[1]);
    }

    urlMap.set(TWITCH, twitchArray); // add twitch urls to map

    // TODO: get all viewing time on each site
    // chrome time to unix epoch time = 11 644 473 600 seconds
    // Now iterate through all of the IDs --> check if time is > current time - 24 hours (86400 seconds)

    // start with REDDIT usage
    let currArray = urlMap.get(REDDIT);
    for (let i = 0; i < currArray.size(); i++){
        var request = "SELECT * FROM visits WHERE";
    }
}

function createWindow() {
    // Create the browser window.

    // current width/height is 1280x720 (720p)
    // this can change depending on CSS, etc.
    // leave as 720p until needed
    
    let w = 1280;
    let h = 720;
    getHistory();
    win = new BrowserWindow({ width: w, height: h });

    // Load index.html in window
    win.loadFile('calendar.html');

    // TODO: add support for connecting electron frontend to tomcat server
    // MUST START TOMCAT SERVER BEFORE LAUNCHING APP IF THIS IS THE CASE
    // i.e. localhost:8080//app/index.html
    
    // let appURL = "https://github.com";

    // win.loadURL(appURL);

    // Open the DevTools.
    win.webContents.openDevTools();

    // Emitted when the window is closed.
    win.on('closed', () => {
        // Dereference the window object, usually you would store windows
        // in an array if your app supports multi windows, this is the time
        // when you should delete the corresponding element.
        win = null;
    });
}

// This method will be called when Electron has finished
// initialization and is ready to create browser windows.
// Some APIs can only be used after this event occurs.
app.on('ready', createWindow);

// Quit when all windows are closed.
app.on('window-all-closed', () => {
    // On macOS it is common for applications and their menu bar
    // to stay active until the user quits explicitly with Cmd + Q

    // FIXME: not sure if this needs to be fixed, but not quitting when all windows are closed is bad practice imo (even for mac apps)
    // darwin == macOS keyword

    // if (process.platform !== 'darwin') {
    //     app.quit()
    // }

    // so now app closes when all windows are closed (REGARDLESS OF OPERATING SYSTEM)
    app.quit();

});

app.on('activate', () => {
    // On macOS it's common to re-create a window in the app when the
    // dock icon is clicked and there are no other windows open.
    if (win === null) {
        createWindow();
    }
});

// In this file you can include the rest of your app's specific main process
// code. You can also put them in separate files and require them here.