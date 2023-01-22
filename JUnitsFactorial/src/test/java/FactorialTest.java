import static org.junit.jupiter.api.Assertions.*;

class FactorialTest {

    @org.junit.jupiter.api.Test
    void factorialCountFirst() {
        Factorial factorial = new Factorial();
        int realResult = factorial.factorialCount(0);
        int expectedResult = 1;
        assertEquals(expectedResult, realResult);
    }
    @org.junit.jupiter.api.Test
    void factorialCountSecond() {
        Factorial factorial = new Factorial();
        int realResult = factorial.factorialCount(1);
        int expectedResult = 1;
        assertEquals(expectedResult, realResult);
    }
    @org.junit.jupiter.api.Test
    void factorialCountThird (){
        Factorial factorial = new Factorial();
        int realResult = factorial.factorialCount(2);
        int expectedResult = 2;
        assertEquals(expectedResult, realResult);
    }
    @org.junit.jupiter.api.Test
    void factorialCountFourth() {
        Factorial factorial = new Factorial();
        int realResult = factorial.factorialCount(4);
        int expectedResult = 24;
        assertEquals(expectedResult, realResult);
    }
    @org.junit.jupiter.api.Test
    void factorialCountFifth() {
        Factorial factorial = new Factorial();
        int realResult = factorial.factorialCount(5);
        int expectedResult = 120;
        assertEquals(expectedResult, realResult);
    }
}