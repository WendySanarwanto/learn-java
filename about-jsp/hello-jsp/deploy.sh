export CATALINA_HOME=/usr/local/tomcat;
export TARGET_ROOT_DIR=$CATALINA_HOME/webapps/ROOT;
export WEB_INF=$TARGET_ROOT_DIR/WEB-INF;
export TOMCAT_CONTAINER_NAME=tomcatdev;

if [ -d "./dist" ]; then
  # Cleanup prior .jsp file(s)
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "rm $TARGET_ROOT_DIR/hello.jsp";
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "rm -rf $TARGET_ROOT_DIR/action-demo";

  # Copy *.jsp files inside dist into $TOMCAT_CONTAINER_NAME:TARGET_ROOT_DIR
  for f in dist/*.jsp; do docker cp $f $TOMCAT_CONTAINER_NAME:$TARGET_ROOT_DIR; done;
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "mkdir -p $TARGET_ROOT_DIR/action-demo";
  for f in dist/action-demo/*.jsp; do docker cp $f $TOMCAT_CONTAINER_NAME:$TARGET_ROOT_DIR/action-demo; done;
fi

# # Copy web.xml file into $TOMCAT_CONTAINER_NAME:$WEB_INF directory
# docker cp ./web.xml $TOMCAT_CONTAINER_NAME:$WEB_INF;

# Shutdown the Tomcat server
docker exec -it $TOMCAT_CONTAINER_NAME sh -c "./bin/shutdown.sh";

# Restart the Tomcat server
sleep 2s;
docker start $TOMCAT_CONTAINER_NAME;

unset CATALINA_HOME;
unset TOMCAT_CONTAINER_NAME;
unset WEB_INF;
