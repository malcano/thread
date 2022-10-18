package test;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import java.util.Random;
import src.SupermarketSol;
import src.Supermarket;

public class SupermarketTest {

    @Test
    public void testSimple1() {
        System.out.println("*****testSimple1()*****");
        assertEquals(12, Supermarket.serveCustomer(new int[] { 5, 3, 4 }, 1));
    }

    @Test
    public void testSimple2() {
        System.out.println("*****testSimple2()*****");
        assertEquals(10, Supermarket.serveCustomer(new int[] { 10, 2, 3, 3 }, 2));
    }

    @Test
    public void testSimple3() {
        System.out.println("*****testSimple3()*****");
        assertEquals(12, Supermarket.serveCustomer(new int[] { 2, 3, 10 }, 2));
    }

    @Test
    public void testSimple4() {
        System.out.println("*****testSimple4()*****");
        assertEquals(9, Supermarket.serveCustomer(new int[] { 2, 2, 3, 3, 4, 4 }, 2));
    }

    @Test
    public void testEmptyCustomer() {
        System.out.println("*****testEmptyCustomer()*****");
        assertEquals(0, Supermarket.serveCustomer(new int[] {}, 1));
    }

    @Test
    public void testManyCashiers() {
        System.out.println("*****testManyCashiers()*****");
        assertEquals(5, Supermarket.serveCustomer(new int[] { 1, 2, 3, 4, 5 }, 100));
    }

    @Test
    public void testSingleCustomer() {
        System.out.println("*****testSingleCustomer()*****");
        assertEquals(2, Supermarket.serveCustomer(new int[] { 2 }, 5));
    }

    @Test
    public void testSingleCustomerSingleCashier() {
        System.out.println("*****testSingleCustomerSingleCashier()*****");
        assertEquals(5, Supermarket.serveCustomer(new int[] { 5 }, 1));
    }

    @Test
    public void testSingleCashierManyCustomers() {
        System.out.println("*****testSingleCashierManyCustomers()*****");
        assertEquals(15, Supermarket.serveCustomer(new int[] { 1, 2, 3, 4, 5 }, 1));
    }

    @Test
    public void testLongCustomerArray() {
        System.out.println("*****testLongCustomerArray()*****");
        assertEquals(113,
                Supermarket.serveCustomer(
                        new int[] { 29, 18, 6, 23, 25, 29, 24, 17, 10, 8, 8, 22, 20, 16, 13, 17, 7, 21, 7, 11, 18, 26,
                                25, 1, 18,
                                29, 16, 26, 7, 11, 13, 20, 12, 6, 23, 3, 10, 9, 8, 5, 6, 18, 19, 26, 5, 15, 4, 15, 1,
                                4 },
                        7));
    }

    @Test
    public void testRandomCustomers() {
        System.out.println("*****testRandomCustomers()*****");
        final int max_tills = 6;
        Random rand = new Random();
        for (int l = 0; l < 2; l++) {
            int n = rand.nextInt(max_tills) + 1;
            int[] list = SupermarketSol.generateRandomArray();
            for (int j = 0; j < 3; j++) {
                n = ((n + j) % max_tills) + 1;
                System.out.println("Testing n: " + n + ", array: " + SupermarketSol.stringifiedArray(list));
                assertEquals(SupermarketSol.serveCustomer(list, n),
                        Supermarket.serveCustomer(list, n));
            }
        }
    }
}