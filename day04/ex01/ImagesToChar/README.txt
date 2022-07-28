# making directory
rm -rf target
mkdir target

# compiling files
javac src/java/edu/school21/printer/*/*.java -d target

# copy resources
cp -R src/resources target/.

# make jar archive
jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target .

# starting program
java -jar target/images-to-chars-printer.jar . 0