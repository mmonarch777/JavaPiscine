package edu.school21.numbers;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

class NumberWorkerTest {
    @ParameterizedTest
    @ValueSource(ints = {3, 31, 151,})
    public void  isPrimeForPrimes(int number) {
        assertTrue(new NumberWorker().isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {122, 66, 256})
    public void isPrimeForNotPrimes(int number) {
        assertFalse(new NumberWorker().isPrime(number));
    }

    @ParameterizedTest
    @ValueSource(ints = {-1, 0, 1})
    public void isPrimeForIncorrectNumbers(int number) {
        assertThrows(IllegalNumberException.class, () -> new NumberWorker().isPrime(number), "Exception not thrown");
    }

    @ParameterizedTest
    @CsvFileSource(resources = "/data.csv")
    public void isCheckDigitsSum(int number, int sum) {
        assertEquals(new NumberWorker().digitsSum(number), sum);
    }
}