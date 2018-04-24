# Cleanup content of ./dist folder
./clean.sh && \

# # Build the application's .java files with its dependencies.
# find . -name "*.java" -print | xargs javac -cp "./dependencies/*;." -d ./dist

# Copy all JSP files into dist
cp -r ./src/* ./dist/
