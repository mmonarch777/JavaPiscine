package day00.ex00;

public class Program {
    public static void main(String[] args) {
        int numb = 479598;
        int sum = 0;
        sum += numb % 10;
        numb /= 10;
        sum += numb % 10;
        numb /= 10;
        sum += numb % 10;
        numb /= 10;
        sum += numb % 10;
        numb /= 10;
        sum += numb % 10;
        numb /= 10;
        sum += numb % 10;
        System.out.println(sum);
        System.out.println(1/10);
    }
}
