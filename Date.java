import java.io.Serializable;

/**
 * This class represent a Date.A date consists od day,month and year
 */
public class Date implements Comparable<Object>, Cloneable, Serializable {
    //class variables
    private int month;
    private int day;
    private int year;

    /**
     * parametrized constructor
     * @param month
     * @param day
     * @param year
     */
    Date ( int month, int day, int year )
    {
        this.month=month;
        this.day=day;
        this.year=year;
    }



//    /**
//     * copy constructor
//     * @param other
//     */
  public Date ( Date other)
   {
       this.day=other.getDay();
       this.month= other.getMonth();
       this.year= other.getYear();
   }

    /**
     * getter for month
     * @return month
     */
    public int getMonth() {
        return month;
    }

    /**
     * getter for day
     * @return day
     */
    public int getDay() {
        return day;
    }

    /**
     * getter for year
     * @return year
     */
    public int getYear() {
        return year;
    }

    /**
     * setter for month
     * @param month
     */
    public void setMonth(int month) {
        this.month = month;
    }

    /**
     * setter for day
     * @param day
     */
    public void setDay(int day) {
        this.day = day;
    }

    /**
     * setter for year
     * @param year
     */
    public void setYear(int year) {
        this.year = year;
    }

    /**
     * set all variables to specified values
     * @param month
     * @param day
     * @param year
     */
    public void setAll(int month, int day, int year)
    {
        this.month=month;
        this.day=day;
        this.year=year;
    }

    /**
     * This function will check if  this date is after the provided date
     * @param compareTo
     * @return boolean
     */
    public boolean isAfter( Date compareTo)
    {
        if(this.year<compareTo.year )
        {
           return true;
        }
        else if(this.year==compareTo.year)
        {
            if(this.month<compareTo.month)
            {
                return true;
            }
            else if(this.month==compareTo.month)
            {
                if (this.day<compareTo.day)
                    return true;
                else return false;

            }
            else return false;
        }
        else return false;
    }

//    /**
//     * compare two date objects
//     * @param o
//     * @return boolean
//     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Date)) return false;
        Date date = (Date) o;
        return month == date.month && day == date.day && year == date.year;
    }

    /**
     * String representation of Date object
     * @return
     */
    public String toString()
    {
    return String.format("0%d",month)+"/"+String.format("0%d",day)+"/"+year;
    }

//    /**
//     * compare two date objects
//     * @param Date
//     * @return int
//     */
    @Override
    public int compareTo(Object obj) {
        //throw an exception if provided object 'o' is not of Type Date class
        if(!(obj instanceof Date))  new IllegalArgumentException("Object must be of Date Type");

        //we can divide the comparison of date objects into four(4) parts

        Date o=(Date) obj;
        //1.if day,month and year values of this object are equal to object o then simply return zero
        if(day==o.getDay()&&month==o.getMonth()&&year==o.getYear())
        {
            return 0;
        }
        //2.if year value of this object is less than object 'o' return -1
        else if(year<o.getYear())
        {
         return -1;
        }
        //3.This is a little bit complicated.if year value of this object is equal to the year value of 'o'. then we will comapre month value of both if that's also equal then we will compare day values.
        else if (year==o.getYear())
        {
            if(month==o.getMonth())
            {
                if(day<o.getDay())
                    return -1;
                else return 1;
            }
            else if(month<o.getMonth()) {
                return -1;
            }
            else
            {
                return 1;
            }




        }
        else
            return 1;


    }


//    /**
//     * clone date object
//     * @return Date
//     */
    @Override
    public Date clone() {
        try {
            //as there are no mutable(objects of other classes) fields in Date class therefore we have no need to do deep copy,we can simply call super.clone()

            return (Date) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
