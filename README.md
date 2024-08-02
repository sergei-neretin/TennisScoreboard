## The TennisScoreboard project
Web application that implements a scoreboard for a tennis match.

The author of the terms of reference is https://github.com/zhukovsd
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