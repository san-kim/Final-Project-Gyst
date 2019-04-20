const { app, BrowserWindow } = require('electron');

// Keep a global reference of the window object, if you don't, the window will
// be closed automatically when the JavaScript object is garbage collected.
let win;

function createWindow() {
    // Create the browser window.

    // current width/height is 1280x720 (720p)
    // this can change depending on CSS, etc.
    // leave as 720p until needed 
    let w = 1280;
    let h = 720;

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