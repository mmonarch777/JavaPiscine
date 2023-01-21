package day02.ex00;

import java.io.*;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Program {
    public final static String EXIT = "42";
    static void fillMap(StringBuilder sb, Map<String, String> map) {
        String[] tmp = sb.toString().split(", ");
        if (tmp.length != 2) {
            System.err.println("wrong signature");
            System.exit(-1);
        }
        map.put(tmp[0], tmp[1].toLowerCase().replaceAll(" ", ""));
        sb.setLength(0);
    }
    static void readFis(FileInputStream fis, Map<String, String> map) {
        try {
            int i = -1;
            StringBuilder buff = new StringBuilder();
            while ((i = fis.read()) != -1) {
                if (i == '\n') {
                    fillMap(buff, map);
                    continue;
                }
                buff.append((char)i);
            }
            fillMap(buff, map);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    static String getSignature(FileInputStream file) {
        StringBuilder buffer = new StringBuilder();
        try {
            int nb;
            for (int i = 0; i < 8; i++) {
                nb = file.read();
                String hex = Integer.toHexString(nb);
                if (hex.length() == 1) {
                    hex = "0" + hex;
                }
                buffer.append(hex);
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
        return buffer.toString();
    }

    static void checkSignature(Map<String, String> map, String sign, FileOutputStream output) {
        try {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (sign.startsWith(entry.getValue())) {
                    System.out.println("PROCESSED");
                    output.write(entry.getKey().getBytes());
                    output.write('\n');
                    return;
                }
            }
            System.out.println("UNDEFINED");
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        String path = System.getenv("PWD") + "/signatures.txt";
        Map<String, String> map = new LinkedHashMap<>();
        Scanner sc = new Scanner(System.in);

        try (FileOutputStream output = new FileOutputStream("result.txt", true)){
            FileInputStream fis = new FileInputStream(path);
            readFis(fis, map);
            FileInputStream file;
            String pathFile = sc.nextLine();
            while (!pathFile.equals(EXIT)) {
                file = new FileInputStream(pathFile);
                String signature = getSignature(file);
                checkSignature(map, signature, output);
                file.close();
                pathFile = sc.nextLine();
            }
        } catch (IOException e) {
            System.err.println(e.getMessage());
        }
    }
}
