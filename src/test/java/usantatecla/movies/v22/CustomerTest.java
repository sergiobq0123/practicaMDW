package usantatecla.movies.v22;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

public class CustomerTest {

    private Customer customer;
    private Movie newReleaseMovie;
    private Movie regularMovie;
    private Movie childrensMovie;
    
    @Before
    public void setUp() {
        customer = new Customer("Test Customer");
        newReleaseMovie = new NewReleaseMovie("New Release Movie");
        regularMovie = new RegularMovie("Regular Movie");
        childrensMovie = new ChildrensMovie("Children's Movie");
    }
        
    @Test
    public void testGetTotalChargeWithNoRentals() {
        assertEquals(0.0, getCustomerCharge(), 0.01);
    }
    
    @Test
    public void testGetTotalChargeWithOneRental() {
        customer.addRental(new Rental(regularMovie, 3));

        assertEquals(3.5, getCustomerCharge(), 0.01);
    }
    
    @Test
    public void testGetTotalChargeWithMultipleRentals() {
        customer.addRental(new Rental(newReleaseMovie, 2));
        customer.addRental(new Rental(regularMovie, 3));
        customer.addRental(new Rental(childrensMovie, 4));

        assertEquals(9.5, getCustomerCharge(), 0.01);
    }
        
    @Test
    public void testGetFrequentRenterPointsWithNoRentals() {
        assertEquals(0, getCustomerFrequentRenterPoints());
    }
    
    @Test
    public void testGetFrequentRenterPointsWithRegularMovie() {
        customer.addRental(new Rental(regularMovie, 3));

        assertEquals(1, getCustomerFrequentRenterPoints());
    }
    
    @Test
    public void testGetFrequentRenterPointsWithNewReleaseMovie() {
        customer.addRental(new Rental(newReleaseMovie, 2));

        assertEquals(2, getCustomerFrequentRenterPoints());
    }
    
    @Test
    public void testGetFrequentRenterPointsWithMultipleRentals() {
        customer.addRental(new Rental(newReleaseMovie, 2));
        customer.addRental(new Rental(regularMovie, 3));   
        customer.addRental(new Rental(childrensMovie, 4)); 
        
        assertEquals(4, getCustomerFrequentRenterPoints());
    }
    
    
    @Test
    public void testStatementFormatWithNoRentals() {
        String expectedStatement = "Rental Record for Test Customer\n" +
                                   "Amount owed is 0.0\n" +
                                   "You earned 0 frequent renter points";
        assertEquals(expectedStatement, customer.statement());
    }
    
    @Test
    public void testStatementFormatWithOneRental() {
        customer.addRental(new Rental(regularMovie, 3));
        
        String expectedStatement = "Rental Record for Test Customer\n" +
                                   "\tRegular Movie\t3.5\n" +
                                   "Amount owed is 3.5\n" +
                                   "You earned 1 frequent renter points";
        assertEquals(expectedStatement, customer.statement());
    }
    
    @Test
    public void testStatementFormatWithMultipleRentals() {
        customer.addRental(new Rental(newReleaseMovie, 2));
        customer.addRental(new Rental(regularMovie, 3));
        customer.addRental(new Rental(childrensMovie, 4));
        
        String expectedStatement = "Rental Record for Test Customer\n" +
                                   "\tNew Release Movie\t3.0\n" +
                                   "\tRegular Movie\t3.5\n" +
                                   "\tChildren's Movie\t3.0\n" +
                                   "Amount owed is 9.5\n" +
                                   "You earned 4 frequent renter points";
        assertEquals(expectedStatement, customer.statement());
    }
    
    
    private double getCustomerCharge() {
        try {
            java.lang.reflect.Method method = Customer.class.getDeclaredMethod("getTotalCharge");
            method.setAccessible(true);
            return (Double) method.invoke(customer);
        } catch (Exception e) {
            fail("Error accessing private method: " + e.getMessage());
            return 0.0;
        }
    }
    
    private int getCustomerFrequentRenterPoints() {
        try {
            java.lang.reflect.Method method = Customer.class.getDeclaredMethod("getTotalFrequentRenterPoints");
            method.setAccessible(true);
            return (Integer) method.invoke(customer);
        } catch (Exception e) {
            fail("Error accessing private method: " + e.getMessage());
            return 0;
        }
    }
}
