rm -rf target

javac src/java/edu/school21/printer/*/*.java -d target
cp -R src/resources target/


jar cfm target/images-to-chars-printer.jar src/manifest.txt -C target .

java -jar target/images-to-chars-printer.jar . 0
