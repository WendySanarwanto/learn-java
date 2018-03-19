# Create dist folder if it has not been created
if [ ! -d "./dist" ]; then
  mkdir dist
fi

# Wipe out everything inside ./dist folder
rm -rf ./dist/**
