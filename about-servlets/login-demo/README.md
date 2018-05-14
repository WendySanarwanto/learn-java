# login-demo

A simple web application which performs Login and showing landing page for the authenticated user, built using Java Servlet.

## How to run the application

1. Ensure that you have installed JDK and setup your Java's environment variables (e.g. JAVA_HOME, PATH).

2. Ensure that Ensure that you have installed [DockerCE](https://www.docker.com/community-edition#/download) in your machine.

3. Run `docker run --name tomcatdev -d -p 4040:8080 -e JAVA_OPTS='-Xmx1g' tomcat:9.0.6-jre9-slim` command for creating & running your 1st Tomcat Server container.

4. Run `docker ps -a` and confirm that `tomcatdev` container is up and running. To stop the container, run `docker stop tomcatdev`. To turn on the stopped container, run `docker start mysqldev` command.

5. Clone the parent repository of this project.

6. Change directory to `about-servlets/login-demo` sub directory.

7. Run `build_deploy.sh` command.

8. Browser the landing page on http://localhost:4040/Landing. Click the `Login` link on the page , to do login into the application. Once you have logged in, you should see different welcome heading text on the Landing page. Do login using username: `wendy_s` and password: `test123`. Click `Logout` link on the Landing page to perform logout.
