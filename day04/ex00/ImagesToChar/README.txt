# making directory
rm -rf target
mkdir target

# compiling files
javac src/java/edu/school21/printer/*/*.java -d target

# starting program
java -classpath target edu.school21.printer.app.Program . 0 /Users/mmonarch/Desktop/it.bmp