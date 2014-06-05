
var casper = require('casper').create();
var url = 'http://www.lequipe.fr';

casper.start(url, function() {
    var js = this.evaluate(function() {
        return document;
    });
    this.echo(JSON.stringify(js));
});
casper.run();
