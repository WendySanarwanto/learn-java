# form-submission

A simple Java servlet which just renders a page that displays `name` and `email` values that were submitted from a HTML form file.

## How to run the servlet

1. Ensure that you have installed JDK and setup your Java's environment variables (e.g. JAVA_HOME, PATH).

2. Ensure that Ensure that you have installed [DockerCE](https://www.docker.com/community-edition#/download) in your machine.

3. Run `docker run --name tomcatdev -d -p 4040:8080 -e JAVA_OPTS='-Xmx1g' tomcat:9.0.6-jre9-slim` command for creating & running your 1st Tomcat Server container.

4. Run `docker ps -a` and confirm that `tomcatdev` container is up and running. To stop the container, run `docker stop tomcatdev`. To turn on the stopped container, run `docker start mysqldev` command.

5. Clone the parent repository of this project.

6. Change directory to `about-servlets/form-submission` sub directory.

7. Run `build_deploy.sh` command.

8. Browse the submission form on http://localhost:4040/ContactEntry.html . Upon submitting the form, the browser will redirect to the servlet on http://localhost:4040/ContactEntry .
