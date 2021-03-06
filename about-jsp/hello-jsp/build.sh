# Cleanup content of ./dist folder
./clean.sh && \

# Build the application's .java files with its dependencies.
mkdir -p dist/bean-demo;
find . -name "*.java" -print | xargs javac -cp "../../libs/tomcat/*;." -d ./dist/bean-demo;

# Copy all JSP files into dist
cp -r ./src/action-demo ./dist/;
cp -r ./src/bean-demo/main.jsp ./dist/bean-demo/;
cp -r ./src/*.jsp ./dist/;
