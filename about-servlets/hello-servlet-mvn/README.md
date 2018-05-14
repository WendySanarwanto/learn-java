# hello-servlet-mvn

A simple Java servlet which just prints __Hello Servlet__ big header on a web page, and managed by Maven.

## How to run the servlet

1. Ensure that you have installed JDK and setup your Java's environment variables (e.g. JAVA_HOME, PATH).

2. Ensure that you have installed Apache Maven. Follow the installation's instructions in [here](https://www.tutorialspoint.com/maven/maven_environment_setup.htm) or, if you have Node.js in your machine, you can install it by following instructions in [here](https://www.npmjs.com/package/mvn).

3. Ensure that Ensure that you have installed [DockerCE](https://www.docker.com/community-edition#/download) in your machine.

4. Run `docker run --name tomcatdev -d -p 4040:8080 -e JAVA_OPTS='-Xmx1g' tomcat:9.0.6-jre9-slim` command for creating & running your 1st Tomcat Server container.

5. Run `docker ps -a` and confirm that `tomcatdev` container is up and running. To stop the container, run `docker stop tomcatdev`. To turn on the stopped container, run `docker start mysqldev` command.

6. Clone the parent repository of this project.

7. Change directory to `about-servlets/hello-servlet-mvn` sub directory.

8. Run `mvn clean package` command.

9. Run `./deploy.sh` command.

9. Browse the servlet on http://localhost:4040/hello-servlet-1.0/hello.
