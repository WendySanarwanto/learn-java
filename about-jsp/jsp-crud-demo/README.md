# jsp-crud-demo

A JSP based web application which does Creating, Retrieving, Updating, Deleting (CRUD) data on database.

## How to run the page

1. Ensure that Ensure that you have installed [DockerCE](https://www.docker.com/community-edition#/download) in your machine.

2. Run `docker run --name tomcatdev -d -p 4040:8080 -e JAVA_OPTS='-Xmx1g' tomcat:9.0.6-jre9-slim` command for creating & running your 1st Tomcat Server container.

3. Run `docker run --name mysqldev -e MYSQL_ROOT_PASSWORD=test123 -p 3306:3306 -d mysql:8.0.2` command for creating & running your 1st MySQL Database as a container.

4. Create a new MySQL database and name it as `estore`. You can use GUI tools such as [DBeaver](https://dbeaver.jkiss.org/download/) in order to do this.

5. Clone the parent repository of this project.

6. Change directory to `./about-jsp/jsp-crud-demo` sub directory.

7. Restore the required table through executing `ddl.sql` script in a MySQL Client tool.

8. Run `build_deploy.sh` command.

9. Browse the page on http://localhost:4040/main.jsp
