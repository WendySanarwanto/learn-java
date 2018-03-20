# Contacts Demo

This is a simple command line program written by using Java programming language, which demonstrate basic syntax and concepts of Java language such as Variables, Class, Object, Method, Package, etc.

## How to run the demo

1. Ensure that you have installed JDK and setup your Java's environment variables (e.g. JAVA_HOME, PATH).

2. Clone the parent repository of this project.

3. Change directory to `./contact-demo` sub directory.

4. Invoke `./buildrun.sh` command. 

## How to run the demo using Mysql Database server.

1. Ensure that you have installed [DockerCE](https://www.docker.com/community-edition#/download) in your machine.

2. Run `docker run --name mysqldev -e MYSQL_ROOT_PASSWORD=test123 -p 3306:3306 -d mysql:8.0.2` command for creating & running your 1st MySQL Database as a container.

3. Run `docker ps -a` and confirm that `mysqldev` container is up and running. To stop the container, run `docker stop mysqldev`. To run the stopped container, run `docker start mysqldev`.

4. Create a new MySQL database and name it as `contacts-demo`. You can use GUI tools such as [DBeaver](https://dbeaver.jkiss.org/download/) in order to do this.

5. Clone the parent repository of this project.

6. Change directory to `./contact-demo` sub directory.

7. Restore the required database through executing `ddl.sql` script in a MySQL client tool.

8. Invoke `buildrun_mysql.sh`.
