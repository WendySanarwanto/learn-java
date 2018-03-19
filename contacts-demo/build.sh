# Cleanup content of ./dist folder
./clean.sh && \

# Build the application's .java files with its dependencies.
find . -name "*.java" -print | xargs javac -cp ".;../dependencies/**.jar"  -d ./dist && \

# Cleanup .class files within source directory
rm -rf ./me/wendysa/contactsdemo/**/*.class ./me/wendysa/contactsdemo/*.class

# created /lib sub-directory inside /dist directory
mkdir ./dist/lib && \

# copy all dependency files into /dist/lib folder
cp ./dependencies/**.jar ./dist/lib && \

# change the directory into the dist folder
cd ./dist && \

# Build the application as a .jar file along with required dependency file(s)
# jar cfm contacts-demo.jar ../Manifest.txt ./lib ./me && \
jar cfm contacts-demo.jar ../Manifest.txt ./me && \

# Cleanup other artefacts
rm -rf ./me && \

# Go back to the parent folder
cd ..
