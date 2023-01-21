#Put the file ti.bmp to directory ImagesToChar.
#Or change path "./it.bmp" on the path to the file ti.bmp

javac src/java/edu/school21/printer/*/*.java -d target
java -cp ./target/ edu.school21.printer.app.Program . 0 ./it.bmp