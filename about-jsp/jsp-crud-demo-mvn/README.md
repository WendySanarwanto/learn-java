# jsp-crud-demo-mvn

A JSP based web application which does Creating, Retrieving, Updating, Deleting (CRUD) data on database, managed by Maven.

## How to run the page

1. Ensure that Ensure that you have installed [DockerCE](https://www.docker.com/community-edition#/download) in your machine.

2. Ensure that you have installed Apache Maven. Follow the installation's instructions in [here](https://www.tutorialspoint.com/maven/maven_environment_setup.htm) or, if you have Node.js in your machine, you can install it by following instructions in [here](https://www.npmjs.com/package/mvn).

3. Run `docker run --name tomcatdev -d -p 4040:8080 -e JAVA_OPTS='-Xmx1g' tomcat:9.0.6-jre9-slim` command for creating & running your 1st Tomcat Server container.

4. Run `docker run --name mysqldev -e MYSQL_ROOT_PASSWORD=test123 -p 3306:3306 -d mysql:8.0.2` command for creating & running your 1st MySQL Database as a container.

5. Create a new MySQL database and name it as `estore`. You can use GUI tools such as [DBeaver](https://dbeaver.jkiss.org/download/) in order to do this.

6. Clone the parent repository of this project.

7. Change directory to `./about-jsp/jsp-crud-demo-mvn` sub directory.

8. Restore the required table through executing `ddl.sql` script in a MySQL Client tool.

9. Run `mvn clean package && ./deploy.sh` command.

10. Browse the page on http://localhost:4040/main.jsp
