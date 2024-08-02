## The TennisScoreboard project
Web application that implements a scoreboard for a tennis match.

The author of the terms of reference is https://github.com/zhukovsd

## Project Motivation
Create a client-server application with web interface
Get hands-on experience with Hibernate ORM
Build a simple web interface without third-party libraries
Get familiar with the MVC(S) architectural pattern

## Build and Run

In the root directory, execute the build using Gradle Wrapper:

For Unix:

```bash
./gradlew clean build
```
For Windows:

```cmd
gradlew.bat clean build
```

Deploy the WAR file to your Apache Tomcat.

Copy the WAR file to the `webapps` directory of your Tomcat installation.
Start your servlet container.

For Unix:

```bash
<tomcat-directory>/bin/startup.sh
```
For Windows:

```cmd
<tomcat-directory>\bin\startup.bat
```