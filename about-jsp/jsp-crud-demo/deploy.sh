# export APP_PATH=/me/wendysa/jspcrud;
export CATALINA_HOME=/usr/local/tomcat;
export TARGET_ROOT_DIR=$CATALINA_HOME/webapps/ROOT;
export WEB_INF=$TARGET_ROOT_DIR/WEB-INF;
export TARGET_DIR=$WEB_INF/classes;
export TOMCAT_CONTAINER_NAME=tomcatdev;

if [ -d "./dist" ]; then
  # Cleanup prior .jsp file(s)
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "rm -rf $TARGET_ROOT_DIR/main.jsp";

  # Create lib directory if it does not exist
  docker exec -it $TOMCAT_CONTAINER_NAME sh -c "if [ ! -d \"$WEB_INF/lib\" ]; then mkdir -p $WEB_INF/lib; fi;";

  # Copy *.jsp files inside dist into $TOMCAT_CONTAINER_NAME:TARGET_ROOT_DIR
  for f in dist/*.jsp; do docker cp $f $TOMCAT_CONTAINER_NAME:$TARGET_ROOT_DIR; done;

  # Copy JSTL dependencies  
  for f in ../../libs/jstl/*.jar; do docker cp $f $TOMCAT_CONTAINER_NAME:$WEB_INF/lib/; done;
fi

# Shutdown the Tomcat server
docker exec -it $TOMCAT_CONTAINER_NAME sh -c "./bin/shutdown.sh";

# Restart the Tomcat server
sleep 2s;
docker start $TOMCAT_CONTAINER_NAME;

# unset APP_PATH;
unset CATALINA_HOME;
unset TARGET_DIR;
unset TOMCAT_CONTAINER_NAME;
unset WEB_INF;
