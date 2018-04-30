# hello-jsp

A simple JSP web application which just prints header and simple text on a web page.

## How to run the page

1. Ensure that Ensure that you have installed [DockerCE](https://www.docker.com/community-edition#/download) in your machine.

2. Run `docker run --name tomcatdev -d -p 4040:8080 -e JAVA_OPTS='-Xmx1g' tomcat:9.0.6-jre9-slim` command for creating & running your 1st Tomcat Server container.

3. Run `docker ps -a` and confirm that `tomcatdev` container is up and running. To stop the container, run `docker stop tomcatdev`. To turn on the stopped container, run `docker start mysqldev` command.

4. Clone the parent repository of this project.

5. Change directory to `about-jsp/hello-jsp` sub directory.

6. Run `build_deploy.sh` command.

7. Browse the page on http://localhost:4040/hello.jsp
