export APP_PATH=/me/wendysa/helloservlet;
export CATALINA_HOME=/usr/local/tomcat;
export TARGET_DIR=$CATALINA_HOME/webapps/ROOT/WEB-INF/classes;
export TOMCAT_CONTAINER_NAME=tomcatdev;
export WEB_INF=$CATALINA_HOME/webapps/ROOT/WEB-INF;

# Ensure that `classes` folder in $TOMCAT_CONTAINER_NAME:$TARGET_DIR directory has been created
docker exec -it $TOMCAT_CONTAINER_NAME sh -c "mkdir -p $TARGET_DIR";

# Copy everything inside dist into $TOMCAT_CONTAINER_NAME:$TARGET_DIR directory
if [ -d "./dist" ]; then
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "rm -rf $TARGET_DIR$APP_PATH";
  docker cp ./dist/. $TOMCAT_CONTAINER_NAME:$TARGET_DIR;
fi

# Copy web.xml file into $TOMCAT_CONTAINER_NAME:$WEB_INF directory
docker cp ./web.xml $TOMCAT_CONTAINER_NAME:$WEB_INF;

# Shutdown the Tomcat server
docker exec -it $TOMCAT_CONTAINER_NAME sh -c "./bin/shutdown.sh";

# Restart the Tomcat server
docker exec -it tomcatdev sh -c "./bin/shutdown.sh";
docker start $TOMCAT_CONTAINER_NAME;

unset APP_PATH;
unset CATALINA_HOME;
unset TARGET_DIR;
unset TOMCAT_CONTAINER_NAME;
unset WEB_INF;
