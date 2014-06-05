var http = require('http'),
    httpProxy = require('http-proxy');

// Configuration
var proxyPort = 8000;

// Launche the proxy
var proxy = httpProxy.createProxyServer({});

var server = require('http').createServer(function(req, res) {
  console.log(req.method, req.url); 
  //, req.headers);
  proxy.web(req, res, { target: req.url });
});

console.log("Proxy listening on port " + proxyPort);
server.listen(proxyPort);
