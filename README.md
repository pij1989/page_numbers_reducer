# Page_numbers_reducer

This application, which receives a line with page numbers from the client via HTTP, reduces this line and sends the short form to the client

## Build

Clone project from Github repository https://github.com/pij1989/page_numbers_reducer.
Go to the project directory:
```
cd .../page_numbers_reducer
```
Perform command in command line:
```
mvn package
```

## Deploy

Install Apache Tomcat 9.x server before deployment. In server directory named config find XML file with name tomcat-users, open and complete the following installation: 
```
<role rolename="tomcat"/>
<role rolename="manager-script"/>
<user username="tomcat" password="tomcat" roles="tomcat,manager-script"/>
```
Go to the server directory named bin and launch the server from the command line: 
```
startup
```
After that go to the project directory and run the command in the command line:
```
mvn tomcat7:deploy
```

## Deploy in Docker

After building go to the project directory and run the command in the command line:
```
docker-compose up
```