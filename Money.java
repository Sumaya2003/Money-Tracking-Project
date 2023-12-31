import java.io.Serializable;

/**
 * This is Money class that can hold amount of money in the form of dollars and cents
 */
public class Money implements Comparable<Object>, Cloneable, Serializable {

    //class variables
    private int dollars;//used to hold dollars
    private int cents;//used to hold cents

    /**
     * default constructor
     */
    public Money() {

        dollars =0;
        cents =0;
    }

    /**
     * overloaded constructor that created object with given amount of dollars and set cents t o zero
     * @param dol
     */
      public Money (int dol )
    {
        this.dollars =dol;
        this.cents=0;
    }

    /**
     * overloaded constructor that will set dollars and cents to specified values (it also re-calculate dollars and cents  if cents are greater than 99 )
     * @param dol
     * @param cent
     */
      public Money( int dol, int cent)
    {
        this.dollars =dol;
        this.cents =cent;
        if(this.cents>99)
        {

            int d=(this.cents/100);
            int c=this.cents%100;

            this.dollars+=d;
            this.cents=c;
        }
    }


//    /**
//     * copy constructor
//     * @param other
//     */
//    public Money( Money other)
//    {
//        this.dollars =other.dollars;
//        this.cents=other.cents;
//    }

    /**
     * getter of dollars
     * @return
     */
    int getDollars()
    {
        return this.dollars;
    }

    /**
     * getter for cents
     * @return
     */
    int getCents()
    {
        return this.cents;
    }



    /**
     * This function will resets dollars and cents to specified values
     * @param dollars
     * @param cents
     */
    public  void setMoney( int dollars, int cents)
    {
        this.dollars =dollars;
        this.cents=cents;
    }



    /**
     * getter for money (by calculating total money in double)
     * @return
     */
    public double getMoney( )
    {
        double money=dollars+(cents/100);
        money+=((double) (cents%100))/100;

        return money;
    }

    /**
     * This function will add dollars to existing dollars
     * @param dollars
     */
    void add( int dollars)
    {
        this.dollars+=dollars;
    }

    /**
     * This function will add dollars and cents to existing dollars and cents respectively
     * @param dollars
     * @param cents
     */
    void add ( int dollars, int cents)
    {
        this.dollars+=dollars;
        this.cents+=cents;
        //if cents are greater than 99 then recalculate dollars and cents
        if(this.cents>99)
        {

            int d=(this.cents/100);
            int c=this.cents%100;

            this.dollars+=d;
            this.cents=c;
        }
    }



    /**
     * this function will add dollars and cents from another Money object
     * @param other
     */
    void add( Money other)

    {
        this.dollars+=other.dollars;

        this.cents+=other.cents;
        //if cents greater than 99 then recalculate dollars and cents
        if(this.cents>99)
        {
            int d=cents/100;
            int c=cents%100;
            this.dollars+=d;
            this.cents=c;
        }
    }


    /**
     * Compare two Money objects
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Money)) return false;
        Money money = (Money) o;
        return dollars == money.dollars && cents == money.cents;
    }

    /**
     * String representation of Money object
     * @return String
     */
    public String toString()
    {

        double d=(double) this.dollars+(((double)this.cents)/100.00);
       String ans= String.format("%.2f", d);
        return "$"+ans;


    }

//    /**
//     * compare two Money objects
//     * @param Money
//     * @return int
//     */

    /*
    @Override
    public int compareTo(Money o) {
        if(dollars==o.getDollars()&&cents==o.getCents()){
            return 0;
        }
        return 1;
    }*/

    @Override
    public int compareTo(Object o) {
        //if o is not a valid money object then throw an exception
        if (!(o instanceof Money)) throw  new IllegalArgumentException("The provided Object must be of same class");

        Money m=(Money) o;
        //otherwise compare money value of this object with money value of 'o' object .if this value is less then return -1 and if this value is greater then return 1 otherwise return zero(values are equal)
        return this.getMoney()<m.getMoney()? -1: this.getMoney()>m.getMoney()? 1: 0;

    }



    //    /**
//     * clone Money object
//     * @return Money
//     */
    @Override
    public Money clone() {
        try {
            //as there are no mutable(objects of other classes) fields in Money class therefore we have no need to do deep copy,we can simply call super.clone()
            return (Money) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
