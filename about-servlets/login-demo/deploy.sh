export APP_PATH=/me/wendysa/logindemo;
export CATALINA_HOME=/usr/local/tomcat;
export TARGET_ROOT_DIR=$CATALINA_HOME/webapps/ROOT;
export WEB_INF=$TARGET_ROOT_DIR/WEB-INF;
export TARGET_DIR=$WEB_INF/classes;
export TOMCAT_CONTAINER_NAME=tomcatdev;

# Ensure that `classes` folder in $TOMCAT_CONTAINER_NAME:$TARGET_DIR directory has been created
docker exec -it $TOMCAT_CONTAINER_NAME sh -c "mkdir -p $TARGET_DIR";

if [ -d "./dist" ]; then
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "rm -rf $TARGET_DIR$APP_PATH";
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "rm -rf $TARGET_ROOT_DIR/*.html";

  # # Ensure that `classes` folder in $TOMCAT_CONTAINER_NAME:$TARGET_DIR directory has been created
  # docker exec -it $TOMCAT_CONTAINER_NAME sh -c "mkdir -p $TARGET_DIR$APP_PATH";

  # Copy .class files inside dist into $TOMCAT_CONTAINER_NAME:$TARGET_DIR directory  
  docker cp ./dist/. $TOMCAT_CONTAINER_NAME:$TARGET_DIR;

  # # Copy *.html files inside dist into $TOMCAT_CONTAINER_NAME:TARGET_ROOT_DIR
  # docker cp ./dist/*.html $TOMCAT_CONTAINER_NAME:$TARGET_ROOT_DIR;
fi

# Copy web.xml file into $TOMCAT_CONTAINER_NAME:$WEB_INF directory
docker cp ./web.xml $TOMCAT_CONTAINER_NAME:$WEB_INF;

# Shutdown the Tomcat server
docker exec -it $TOMCAT_CONTAINER_NAME sh -c "./bin/shutdown.sh";

# Restart the Tomcat server
sleep 2s;
docker start $TOMCAT_CONTAINER_NAME;

unset APP_PATH;
unset CATALINA_HOME;
unset TARGET_DIR;
unset TOMCAT_CONTAINER_NAME;
unset WEB_INF;
