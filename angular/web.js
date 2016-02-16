var gzippo = require('gzippo');
var express = require('express');
var morgan = require('morgan')
var app = express();

app.use(morgan('prod'));
app.use(gzippo.staticGzip("" + __dirname + "/dist"));
app.listen(80);
