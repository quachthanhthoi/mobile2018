// var fs = require('fs');
import routes from './routes';
import bodyParser from 'body-parser';
import express from 'express';
import * as errorHandler from './errorHandler';

var app = express();

const APP_PORT = '8080';
const APP_HOST = '127.0.0.1';

app.set('port', APP_PORT);
app.set('host', APP_HOST);

app.locals.title = 'Quang Tran Ex2';
app.locals.version = '1.0.0';

app.use(bodyParser.json());

app.use('/api', routes);

app.use(errorHandler.bodyParser);
app.use(errorHandler.notFoundApi);

app.listen(app.get('port'), app.get('host'), () => {
  console.log('info', `Server started at http://${app.get('host')}:${app.get('port')}`);
});

export default app;

