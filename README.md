# Company Manager

This app allows add, viewing, updating companies and adding, viewing their owners. It is built with a Java backend (REST APIs) and an Angular frontend.

This guide contains the deployment instructions and the commands to test various flows.

## Version
0.0.1

## Services

The frontend & backend services are available on the following links:
- Frontend
``` sh
http://ec2-54-200-129-123.us-west-2.compute.amazonaws.com/#/
```
- Backend
``` sh
http://ec2-54-200-129-123.us-west-2.compute.amazonaws.com:1435
```

## Installation
Clone the repoistory. It contains both the server & client source code.

### Backend
* [Java] - Download and install the latest version of Java (link is for MacOSX)
* Make sure to set JAVA_HOME correctly.

### Frontend
* [NodeJS] - Download and install the latest version of NodeJS (5.6.0 at the time of writing this guide) (alternatively you can use [nvm] to do that if you already have that installed)
* [NPM] - Download and install the latest version of NPM

## Deployment
### Backend
* cd into the backend directory
``` sh
cd rest_server
./start-server.sh
```
* The backend will start on port 1435 by default (can be modified in the server config)

### Frontend
* Run the following commands to prepare the frontend server
``` sh
cd angular 
npm install
npm install grunt grunt-cli express morgan forever -g --save
bower install
```
* Run the following command to run the frontend server in dev mode (port 9000 by default)
``` sh
grunt serve
```

* To run the frontend server in prod mode, we wil use nginx as the webserver. Assuming that you have installed nginx and setup its config to correctly point to the dist/ folder
``` sh
grunt build
service nginx restart
```

## Testing

Once the backend is deployed, you can test the various REST APIs using the commands below.

* Add a new company
```sh
curl -i -H "Accept: application/json" -X POST -d "company={name:”company1”,address:”address1”,city:”city1”,country:”country1”,email:”email1”,phone:”phone1”,owners:[{firstName:"of",lastName:"lf",phone:"134343",email:"e@e.com"}]}" http://localhost:1435/company
```
* Update an existing company
```sh
curl -i -H "Accept: application/json" -X PUT -d "company={id:1,name:”company1”,address:”address1”,city:”city1”,country:”country1”,email:”email1”,phone:”phone1”,owners:[{firstName:"of",lastName:"lf",phone:"134343",email:"e@e.com"}]}" http://localhost:1435/company
```
* Get all companies
```sh
curl http://localhost:1435/company
```
* Get one company
```sh
curl http://localhost:1435/company?id=1
```
* Add owners for a company
``` sh
curl -i -H "Accept: application/json" -X PATCH -d "companyId=1&owner={firstName:"usman",lastname:"kha",email:"a@a.com",phone:"34343"}" http://localhost:1435/company
```

## Development

There's plenty to do to make this application better and more usable. For example:

### Authentication
Authentication can be added using:
- One of the popular OAuth gateways (Google, Facebook, Twitter, Github etc).
- Email/password based mechanism.

In either case, once an authentication system is in place, the server would be generating sessions. To make sure that only authenticated users can hit the remaining APIs, the code would need to be refactored like below
- All client requests will request for a *session_id* from the server before invoking any APIs. If an existing *session_id* exists against a user, it will be returned as such, otherwise a new one will be created and associated with that user. This *session_id* will only be valid for a certain period of time (after which it will expire). The *session_id* will be an SHA256 digest of the *user_id*, *salt* (a long secret key which only the server knows) and *nonce* (a one time token, usually the current timestamp).
- All subsequent client requests should contain this *session_id* while invoking the APIs.
- The server will only allow requests which contain a *session_id* that is valid and has not expired yet.

### Redundancy
To make the service redundant, the backend server can be deployed on multiple nodes (> 1) and put under a load balancer to provide an uninterrupted access to the API. The backend server will also contain a *heartbeat checker* for the load balancer, using which the load balancer will assess the accessibility of each service node. Using this approach, if one of the service nodes goes down, the load balancer can route all requests to the other available nodes, therefore keeping the service alive and fault tolerant.

**Free Software, Hell Yeah!**

[//]: # (These are reference links used in the body of this note and get stripped out when the markdown processor does its job. There is no need to format nicely because it shouldn't be seen. Thanks SO - http://stackoverflow.com/questions/4823468/store-comments-in-markdown-syntax)

   [node.js]: <http://nodejs.org>
   [Java]: <https://java.com/en/download/help/mac_install.xml>
   [npm]: <https://www.npmjs.com>
   [NodeJS]: <https://nodejs.org/>

