var http = require('http'),
    httpProxy = require('http-proxy')
    fs = require('fs');

// Configuration
var proxyPort = 8000;
var filename = 'savedRequests.txt';

// Launche the proxy
var proxy = httpProxy.createProxyServer({});

var server = require('http').createServer(function(req, res) {
  console.log(req.method, req.url); 

  fs.appendFile(filename, req.url + '\n', function (err) {
	  proxy.web(req, res, { target: req.url });
  });
  //, req.headers);
});

console.log('Proxy listening on port ' + proxyPort);
server.listen(proxyPort);
