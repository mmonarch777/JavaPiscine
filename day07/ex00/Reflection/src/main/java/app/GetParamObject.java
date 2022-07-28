package app;

import java.lang.reflect.Parameter;
import java.util.Scanner;

public class GetParamObject {

    static Object getParamObject(Parameter parameter, Scanner scanner) {
        String paramName = parameter.getType().getSimpleName().toLowerCase();
        try {
            switch (paramName) {
                case "string":
                    return scanner.nextLine();
                case "int":
                case "integer":
                    return scanner.nextInt();
                case "boolean":
                    return scanner.nextBoolean();
                case "long":
                    return scanner.nextLong();
                case "double":
                    return scanner.nextDouble();
                default:
                    System.err.println("Error");
                    System.exit(-1);
            }
        } catch (Exception e) {
            System.err.println("error format");
            System.exit(-1);
        }
        return null;
    }
}