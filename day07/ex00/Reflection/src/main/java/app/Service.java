package app;

import java.lang.reflect.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.Set;

public class Service {
    public static Class<?> checkClass(Set<Class<?>> set, String name) {
        for (Class<?> obj : set) {
            if (obj.getSimpleName().equals(name)) {
                return obj;
            }
        }
        return null;
    }
    public static void printFieldsAndMethods(Class<?> selectedClass) {
        Field[] fields = selectedClass.getDeclaredFields();
        System.out.println("fields:");
        for (Field field : fields) {
            System.out.printf("        %s %s\n", field.getType().getSimpleName(), field.getName() );
        }

        Method[] methods = selectedClass.getDeclaredMethods();
        System.out.println("methods:");
        for (Method method : methods) {
            if (method.getName().equals("toString")) {
                continue;
            }
            String returnType = method.getReturnType().getSimpleName();
            String name = method.getName();
            Class<?>[] param = method.getParameterTypes();

            if (param.length > 0) {
                StringBuilder builder = new StringBuilder(param[0].getSimpleName());
                for (int i = 1; i < param.length; i++) {
                    builder.append(", ");
                    builder.append(param[i].getSimpleName());
                }
                System.out.printf("        %s %s(%s)\n", returnType, name, builder);
            } else {
                System.out.printf("        %s %s()\n", returnType, name);
            }
        }
    }

    public static Object getParameter(Class<?> clazz, Scanner sc) {
        String paramName = clazz.getSimpleName().toLowerCase();
        try {
            switch (paramName) {
                case "string":
                    return sc.nextLine();
                case "int":
                case "integer":
                    return sc.nextInt();
                case "boolean":
                    return sc.nextBoolean();
                case "long":
                    return sc.nextLong();
                case "double":
                    return sc.nextDouble();
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

    public static Object createObject(Class<?> selectedClass, Scanner sc) {
        Constructor<?> constructor = null;

        for (Constructor<?> c : selectedClass.getConstructors()) {
            if (c.getParameterTypes().length > 0) {
                constructor = c;
                break;
            }
        }
        ArrayList<Object> parameters = new ArrayList<>();
        Field[] fields = selectedClass.getDeclaredFields();
        Class<?>[] param =  constructor.getParameterTypes();
        for (int i = 0; i < param.length; i ++) {
            System.out.printf("%s (%s):\n", fields[i].getName(), param[i].getSimpleName());
            parameters.add(getParameter(param[i], sc));
        }

        try {
            Object object = constructor.newInstance(parameters.toArray());
            System.out.println("Object created: " + object);
            return object;
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            System.err.println("error format");
        }
        return null;
    }

    public static void updateField(Class<?> selectedClass,Object obj, Scanner sc) {
        String field = sc.next();
        try {
            Field fieldForUpdate = selectedClass.getDeclaredField(field);
            fieldForUpdate.setAccessible(true);

            System.out.printf("Enter %s value\n", fieldForUpdate.getType().getSimpleName());
            String value = sc.next();

            try {
                switch (fieldForUpdate.getType().getSimpleName().toLowerCase()) {
                    case "int":
                    case "integer":
                        fieldForUpdate.set(obj, Integer.parseInt(value));
                        break;
                    case "double":
                        fieldForUpdate.set(obj, Double.parseDouble(value));
                        break;
                    case "boolean":
                        fieldForUpdate.set(obj, Boolean.parseBoolean(value));
                        break;
                    case "long":
                        fieldForUpdate.set(obj, Long.parseLong(value));
                        break;
                    default:
                        fieldForUpdate.set(obj, value);
                        break;
                }
            } catch (IllegalArgumentException e) {
                System.err.println("Error: can't find such method");
                System.exit(1);
            }

            fieldForUpdate.setAccessible(false);
            System.out.println("Object updated: " + obj);
        } catch (NoSuchFieldException | IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    public static void callMethod(Object obj, Scanner sc) {
        String methodName = sc.next();
        Method[] oMethods = obj.getClass().getDeclaredMethods();

        for (Method oMethod : oMethods) {
            String returnType = oMethod.getReturnType().getSimpleName();
            String name = oMethod.getName();
            Class<?>[] parameters = oMethod.getParameterTypes();
            String res;

            if (parameters.length > 0) {
                StringBuilder stringBuilder = new StringBuilder(parameters[0].getSimpleName());

                for (int i = 1; i < parameters.length; i++) {
                    stringBuilder.append(", ");
                    stringBuilder.append(parameters[i].getSimpleName());
                }

                res = name + "(" + stringBuilder + ")";
            } else {
                res = name + "()";
            }

            if (res.equals(methodName)) {
                ArrayList<Object> objects = new ArrayList<>();

                for (Class<?> parameter : parameters) {
                    System.out.println("Enter " + parameter.getSimpleName() + " value:");

                    try {
                        switch (parameter.getSimpleName().toLowerCase()) {
                            case "int":
                            case "integer":
                                objects.add(Integer.parseInt(sc.next()));
                                break;
                            case "double":
                                objects.add(Double.parseDouble(sc.next()));
                                break;
                            case "boolean":
                                objects.add(Boolean.parseBoolean(sc.next()));
                                break;
                            case "long":
                                objects.add(Long.parseLong(sc.next()));
                                break;
                            default:
                                objects.add(sc.next());
                                break;
                        }
                    } catch (NumberFormatException e) {
                        System.err.println("Error: wrong input type!");
                        System.exit(1);
                    }
                }
                oMethod.setAccessible(true);
                try {
                    if (returnType.equals("void")) {
                        oMethod.invoke(obj, objects.toArray());
                    } else {
                        System.out.println("Method returned:");
                        System.out.println(oMethod.invoke(obj, objects.toArray()));
                    }
                } catch (IllegalAccessException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                oMethod.setAccessible(false);
                return;
            }
        }
        System.err.println("Error: can't find such method");
    }
}
