# Cleanup content of ./dist folder
./clean.sh && \

# Build the application's .java files with its dependencies.
find . -name "*.java" -print | xargs javac -Xlint:unchecked -cp "../../libs/jackson/*;../../libs/tomcat/*;." -d ./dist

# Copy all public assets into dist
cp -r ./src/*.jsp ./dist/;
cp -r ./src/*.tld ./dist/;
