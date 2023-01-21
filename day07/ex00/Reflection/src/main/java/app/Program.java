package app;

import static app.Service.*;

import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.io.IOException;
import java.io.PrintStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.Set;

public class Program {
    public static void main(String[] args) throws IOException {
//        PrintStream out = new PrintStream(Files.newOutputStream(Paths.get("out.log")));
//        System.setErr(out);
        Reflections reflections = new Reflections("classes", new SubTypesScanner(false));
        Set<Class<?>> set = reflections.getSubTypesOf(Object.class);

        System.out.println("Classes:");
        for (Class<?> obj : set) {
            System.out.println(obj.getSimpleName());
        }
        System.out.println("--------------------------------------");
        System.out.println("Enter class name:");
        try (Scanner sc = new Scanner(System.in)) {
            String name = sc.nextLine();
            Class<?> selectedClass = checkClass(set, name);
            if (selectedClass == null) {
                System.out.println("Wrong class name");
                System.exit(-1);
            }
            System.out.println("--------------------------------------");
            printFieldsAndMethods(selectedClass);
            System.out.println("--------------------------------------");
            System.out.println("Let's create an object.");
            Object obj = createObject(selectedClass, sc);
            System.out.println("--------------------------------------");
            System.out.println("Enter name of the field for changing:");
            updateField(selectedClass, obj, sc);
            System.out.println("--------------------------------------");
            System.out.println("Enter name of the method for call");
            callMethod(obj, sc);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
//        out.close();
    }
}
