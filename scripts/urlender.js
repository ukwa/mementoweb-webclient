#!/usr/bin/env phantomjs --ignore-ssl-errors=yes

// 
// https://github.com/ariya/phantomjs/blob/master/examples/rasterize.js
//

var fs = require('fs');
var system = require('system');

var page = new WebPage(),
    address, output, size;

if ( system.args.length != 4 ) {
    console.log('Usage: urlender.js URL urlsfilename renderfilename');
    phantom.exit();

} else {

    // Open file:
    urlf = fs.open( system.args[2], 'w' );

    // Track requests
    page.onResourceRequested = function (request) {
        console.log("Requesting "+request.url);
        urlf.writeLine( request.url );
    };

    // Set up parameters
    address = system.args[1];
    output = system.args[3];
    page.viewportSize = { width: 800, height: 800 };
    // These parameters apply to .pdf
    //page.paperSize = { format: 'A4', orientation: 'portrait', margin: '1cm' };
    // page.zoomFactor = 

    // Start 
    console.log("Opening...");
    page.open(address, function (status) {
        if (status !== 'success') {
            console.log('Unable to load the address!');
        } else {
            window.setTimeout(function () {
                page.render(output);
                urlf.close();
                phantom.exit();
            }, 500);
        }
    });
}
