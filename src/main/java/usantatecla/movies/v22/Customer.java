package usantatecla.movies.v22;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.BiFunction;
import java.util.function.Function;

public class Customer {

	private String name;
	
	private List<Rental> rentals;

	public Customer(String name) {
		this.name = name;
		rentals = new ArrayList<Rental>();
	}

	public void addRental(Rental rental) {
		rentals.add(rental);
	}

	public String getName() {
		return name;
	}

	public String statement() {
		String result = "Rental Record for " + this.getName() + "\n";
		
		result = processRentals(result, (accumulator, rental) -> 
			accumulator + "\t" + rental.getTitle() + "\t" + 
			String.valueOf(rental.getCharge()) + "\n");
		
		result += "Amount owed is " + String.valueOf(this.getTotalCharge()) + "\n";
		result += "You earned " + String.valueOf(this.getTotalFrequentRenterPoints()) + " frequent renter points";
		return result;
	}

	private double getTotalCharge() {
		return processRentals(0.0, (total, rental) -> total + rental.getCharge());
	}
	
	private int getTotalFrequentRenterPoints() {
		return processRentals(0, (total, rental) -> total + rental.getFrequentRenterPoints());
	}
	
	private <T> T processRentals(T initial, BiFunction<T, Rental, T> operation) {
		T result = initial;
		Iterator<Rental> rentals = this.rentals.iterator();
		while (rentals.hasNext()) {
			Rental each = rentals.next();
			result = operation.apply(result, each);
		}
		return result;
	}
}
