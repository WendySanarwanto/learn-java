# Create dist folder if it has not been created
if [ ! -d "./dist" ]; then
  mkdir dist
fi

# Cleanup .class files within source directory
rm -rf ./me/wendysa/contactsdemo/**/*.class ./me/wendysa/contactsdemo/*.class

# Wipe out everything inside ./dist folder
rm -rf ./dist/**
