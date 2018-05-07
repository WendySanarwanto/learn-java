export APP_PATH=/me/wendysa/jspcrud;
export CATALINA_HOME=/usr/local/tomcat;
export TARGET_ROOT_DIR=$CATALINA_HOME/webapps/ROOT;
export WEB_INF=$TARGET_ROOT_DIR/WEB-INF;
export TARGET_DIR=$WEB_INF/classes;
export TOMCAT_CONTAINER_NAME=tomcatdev;

if [ -d "./dist" ]; then
  # Cleanup prior .jsp file(s)
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "rm -rf $TARGET_ROOT_DIR/main.jsp";

  # Cleanup prior .tld file(s)
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "rm -rf $WEB_INF/jsp-crud.tld";

  # Create lib directory if it does not exist
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "if [ ! -d \"$WEB_INF/lib\" ]; then mkdir -p $WEB_INF/lib; fi;";

  # Copy *.jsp files inside dist into $TOMCAT_CONTAINER_NAME:TARGET_ROOT_DIR
  for f in dist/*.jsp; do docker cp $f $TOMCAT_CONTAINER_NAME:$TARGET_ROOT_DIR; done;

  # Copy JSTL dependencies  
  for f in ../../libs/jstl/*.jar; do docker cp $f $TOMCAT_CONTAINER_NAME:$WEB_INF/lib/; done;

  # Copy MySQL Dependencies
  for f in ../../libs/mysql/*.jar; do docker cp $f $TOMCAT_CONTAINER_NAME:$WEB_INF/lib; done;

  # Copy Jackson dependencies
  for f in ../../libs/jackson/*.jar; do docker cp $f $TOMCAT_CONTAINER_NAME:$WEB_INF/lib; done;

  # Copy Tomcat dependencies
  for f in ../../libs/tomcat/*.jar; do docker cp $f $TOMCAT_CONTAINER_NAME:$WEB_INF/lib; done;

  # Copy .class files into classes folder
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "rm -rf $TARGET_DIR$APP_PATH";
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "mkdir -p $TARGET_DIR$APP_PATH";
  docker cp ./dist$APP_PATH/. $TOMCAT_CONTAINER_NAME:$TARGET_DIR$APP_PATH;

  # Copy .tld file(s) into WEB-INF folder 
  docker cp ./dist/jsp-crud.tld $TOMCAT_CONTAINER_NAME:$WEB_INF;
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
unset TARGET_ROOT_DIR;
unset TARGET_DIR;
unset TOMCAT_CONTAINER_NAME;
unset WEB_INF;
