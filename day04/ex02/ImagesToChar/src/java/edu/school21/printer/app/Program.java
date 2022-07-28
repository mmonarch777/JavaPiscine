package edu.school21.printer.app;

import edu.school21.printer.logic.Logic;

import com.beust.jcommander.JCommander;
import com.beust.jcommander.Parameter;
import com.beust.jcommander.ParameterException;
import com.beust.jcommander.Parameters;

@Parameters(separators = "=")
public class Program {
    @Parameter(names="--white")
    private static String white;
    @Parameter (names="--black")
    private static String black;

    public static void main(String[] args) throws ParameterException {
        System.out.println(black);
        System.out.println(white);
        Program program = new Program();
        JCommander.newBuilder()
                .addObject(program)
                .build()
                .parse(args);
        System.out.println(black);
        System.out.println(white);
        program.run();
    }

    private void run() {
        Logic logic = new Logic();
        if (white == null || black == null) {
            System.out.println("Error! Bad Arguments!");
            System.exit(-1);
        }
        logic.convert(white, black, Program.class.getResource("/resources/it.bmp"));
    }
}