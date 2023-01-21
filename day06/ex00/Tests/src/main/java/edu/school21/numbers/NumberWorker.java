package edu.school21.numbers;


public class NumberWorker {

    public boolean isPrime(int number) {
        if (number < 2) {
            throw new IllegalNumberException("Wrong number");
        }
        int i = 2;
        while (i * i <= number) {
            if (number % i == 0 && i != number) {
                return false;
            }
            i++;
        }
        return true;
    }

    public int digitsSum(int number) {
        int sum = 0;
        if (number < 0) {
            number *= -1;
        }
        while (number > 0) {
            sum += number % 10;
            number /= 10;
        }
        return sum;
    }
}
