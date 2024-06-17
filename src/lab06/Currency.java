package lab06;

/*
 * Lab 2/3
 * Names: Lucia Liu, Nithya Ramasubramonian
 * Due date: 5/8/24
 * Purpose: The purpose of this lab is to practice Inheritance and Polymorphism through a Currency modeling scenario.
 */

public abstract class Currency 
{
    private int wholeValue;
    private int fractionValue;
    
    public Currency()
    {
        this.wholeValue = 0;
        this.fractionValue = 0;
    }
    
    /*
     * This is a non-default constructor to create a Currency object.
     * 
     * Pre:
     * value - double, value of the currency 
     * 
     * Post:
     * Values with more than two decimal points will be rounded to two decimal points.
     * Throw exception if value is negative.
     * 
     */
    public Currency(double value)
    {
        if (value < 0) {
            throw new IllegalArgumentException("negative double value");
        }

        wholeValue = (int) value;
        
        if (value - wholeValue > 0.005) // to round up
            fractionValue = (int) (100 * (value - wholeValue + 0.005));
        else
            fractionValue = (int) (100 * (value - wholeValue));
    }

    /*
     * Pre:
     * whole - New whole value of Currency, cannot be negative
     * fraction - New fraction value of Currency, cannot be negative
     * 
     * Post:
     * Returns a new Currency (either Pound or Dollar) object with new whole and fraction values.
     * Throw exception if whole or fraction is negative.
     * 
     */
    protected abstract Currency getNewCurrency(int newWhole, int newFraction);

    /*
     * Post:
     * Return a String of the Currency type (either Pound or Dollar)
     * 
     */
    protected abstract String getName();

    /*
     * Post:
     * Return the whole part of the Currency value as an integer.
     * 
     */
    public int getWholeValue()
    {
        return wholeValue;
    }
    
    /*
     * Pre:
     * wholeValue - New whole value, cannot be negative
     * 
     * Post:
     * Throw exception if wholeValue is negative.
     * 
     */
    public void setWholeValue(int wholeValue)
    {
        if (fractionValue < 0) {
            throw new IllegalArgumentException("Whole value cannot be negative");
        }
        
        this.wholeValue = wholeValue;
    }
    
    /*
     * Post:
     * Return the decimal part of the Currency value as an integer.
     * 
     */
    public int getFractionValue()
    {
        return fractionValue;
    }
    
    /*
     * Pre:
     * fractionValue - New fraction value, cannot be negative
     * 
     * Post:
     * Throw exception if fractionValue is negative.
     * 
     */
    public void setFractionValue(int fractionValue)
    {
        // throw exception if input is negative
        if (fractionValue < 0) {
            throw new IllegalArgumentException("Fraction value cannot be negative");
        }
        
        this.fractionValue = fractionValue;
    }

    /*
     * This function adds together two Currency objects.
     * 
     * Pre:
     * otherCurrency - Currency object that is added to this Currency, both objects should be of same subtype.
     * 
     * Post:
     * Return a Currency object with the sum of the two Currency objects.
     * Throw exception if otherCurrency is not the same subtype.
     * 
     */
    public Currency add(Currency otherCurrency) 
    {
        if (!this.getName().equals(otherCurrency.getName())) {
            throw new IllegalArgumentException("Cannot mix currencies.");
        }
        int totalFraction = this.fractionValue + otherCurrency.fractionValue;
        int newWhole = (this.wholeValue + otherCurrency.wholeValue + totalFraction / 100);
        int newFraction = totalFraction % 100;

        return this.getNewCurrency(newWhole, newFraction);
    }
    
    /*
     * This function subtracts another Currency object from this Currency object.
     * 
     * Pre:
     * otherCurrency - Currency object that is subtracted from this Currency, both objects should be of same subtype.
     * 
     * Post:
     * Return a Currency object with the difference of the two Currency objects (this currency - other currency)
     * Throw exception if difference between the two Currency objects is negative.
     * Throw exception if otherCurrency is not the same subtype.
     * 
     */
    public Currency subtract(Currency otherCurrency)
    {
        if (!this.getName().equals(otherCurrency.getName())) {
            throw new IllegalArgumentException("Needs to be same currency type");
        }
        
        int totalFraction = this.fractionValue - otherCurrency.fractionValue;
        int extra = 0;

        if (totalFraction < 0) {
            totalFraction += 100;
            extra = 1;
        }

        int newWhole = this.wholeValue - otherCurrency.wholeValue - extra;
        if (newWhole < 0) {
            throw new IllegalArgumentException("Invalid subtraction");
        }
        int newFraction = totalFraction;
        return getNewCurrency(newWhole, newFraction);
    }

    /*
     * This function checks if the value of this Currency object is equivalent to the other Currency object.
     * 
     * Pre:
     * otherCurrency - Currency object that is being compared to this Currency, both objects should be of same subtype.
     * 
     * Post:
     * Return true if values of both Currency objects are equal. Return false otherwise.
     * Throw exception if otherCurrency is not the same subtype.
     * 
     */
    public boolean isEqual(Currency otherCurrency)
    {
        if (!getName().equals(otherCurrency.getName())) {
            throw new IllegalArgumentException("Cannot occur");
        }
        
        if (this.wholeValue == otherCurrency.wholeValue && 
            this.fractionValue == otherCurrency.fractionValue)
            return true;
        else
            return false;
    }
    
    /*
     * This function checks if the value of this Currency object is greater than the other Currency object.
     * 
     * Pre:
     * otherCurrency - Currency object that is being compared to this Currency, both objects should be of same subtype.
     * 
     * Post:
     * Return true if the value of this Currency object is greater than the value of otherCurrency.
     * Return false otherwise. 
     * Throw exception if otherCurrency is not the same subtype.
     * 
     */
    public boolean isGreater(Currency otherCurrency)
    {
        if (!getName().equals(otherCurrency.getName())) {
            throw new IllegalArgumentException("Cannot use different types of currencies");
        }
        
        if (this.wholeValue > otherCurrency.wholeValue) {
            return true;
        } else if (this.wholeValue < otherCurrency.wholeValue) {
            return false;
        } else {
            if (this.fractionValue > otherCurrency.fractionValue) 
                return true;
            else
                return false;
        }
    }
    
    /*
     * This function prints out the value of the Currency in the format: "xx.yy" + Currency name
     * The Currency value is rounded to two decimal points.
     * 
     */
    public void print() {
        System.out.printf("%d.%02d %s%n", wholeValue, fractionValue, getName());
    }

    /*
     * Post:
     * Returns a String value of the Currency in the format: "xx.yy" + Currency name
     * The Currency value is rounded to two decimal points.
     * 
     */
    public String toString() {
        return String.format("%d.%02d %s", getWholeValue(), getFractionValue(), getName());
    }
    
	/*
	 * Post: Returns double value of Dollar object.
	 */
	public double getValue() {
		return wholeValue + (fractionValue / 100.0);
	}
}
