## Test task:
A simple TCP/IP server in Java that will open a Socket and start listening for incoming data on a chosen port. 
Upon receiving a message during login (the client sends the user ID while logging into their account), 
the server will retrieve all vehicles and insurance offers for the given user and send them back as a response. 
After receiving the data, the client should get all insured vehicles in a readable table format in their browser.

### Tech stack:
Java, 
Gradle,
PostgreSQL,
Node.js,
Vue

```bash
#### Backend Setup & Run

💿 Install Java 8 & Node js 19

🚀 Run with docker-compose

### Test:
Code is tested with integration and unit tests. 

You can run it with your IDE or with CLI: 

./gradlew test

❗️ If you`re going to run integration tests, 
remember that docker container has to be started before running 
this command.

TestData and schemes included in backend/src/test/resources/

# server at localhost:8080
CLI:

./gradlew run

Or just run it on your IDE

#### Frontend Build Setup

# install dependencies
$ npm install

# serve with hot reload at localhost:3000
$ npm run dev 

# build for production and launch server
$ npm run build
```
