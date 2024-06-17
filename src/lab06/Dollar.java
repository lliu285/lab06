package lab06;

/*
 * Lab 2/3
 * Names: Lucia Liu, Nithya Ramasubramonian
 * Due date: 5/8/24
 * Purpose: The purpose of this lab is to practice Inheritance and Polymorphism through a Currency modeling scenario.
 */

public class Dollar extends Currency
{
	private String name = "Dollar";

	public Dollar() {
        super();
    }

    public Dollar(double value) {
        super(value);
    }

    /*
     * Post:
     * Returns name of Currency ("Dollar").
     */
    @Override
    public String getName() {
        return name;
    }

    /*
     * Pre:
     * whole - New whole value of Currency, cannot be negative
     * fraction - New fraction value of Currency, cannot be negative
     * 
     * Post:
     * Returns a new Pound object with new whole and fraction values.
     * Throw exception if whole or fraction is negative.
     * 
     */
	@Override
    protected Dollar getNewCurrency(int whole, int fraction) {
		if (whole < 0 || fraction < 0) {
            throw new IllegalArgumentException("Currency values cannot be negative");
        }		
		
		double temp = (whole * 100.0 + fraction)/100.0;
        return new Dollar(temp);
    }
}
