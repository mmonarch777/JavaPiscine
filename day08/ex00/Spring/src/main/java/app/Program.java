package app;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import printer.Printer;
import printer.PrinterWithPrefixImpl;

public class Program {
    public static void main(String[] args) {
        try {
            ApplicationContext context = new ClassPathXmlApplicationContext("context.xml");

            Printer print = context.getBean("printerWithPrefix4", PrinterWithPrefixImpl.class);
            print.print("First!");


            PrinterWithPrefixImpl prefix= context.getBean("printerWithPrefix2", PrinterWithPrefixImpl.class);
            prefix.setPrefix("Hello");
            prefix.print("Second!");

            Printer printer = context.getBean("printerWithDate3", Printer.class);
            printer.print("Third!");

        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }
}
