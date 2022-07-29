package app;

import printer.Printer;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("/context.xml");
        Printer printer = context.getBean("printerWithPrefixSt", Printer.class);
        Printer printer2 = context.getBean("printerWithPrefixStDate", Printer.class);
        printer.print("Hello!");
        printer2.print("TIME");
    }
}