import java.io.Serializable;

/**
 * This class represents and order,An order object can be used to hold different information regarding  an order
 */
public class Order implements Comparable<Object>, Cloneable, Serializable {
   private Money amount; //  Money object
    private Date orderDate; // a Date Object
    private Date sentDate; // a Date object – null if not yet sent
    private String customer; // a string containing the name of the purchaser
    private String item;// name of item

    /**
     * Parametrized constructor
     * @param amount
     * @param orderDate
     * @param customer
     * @param item
     */
   public  Order ( Money amount, Date orderDate, String customer, String item) {

       this.amount=new Money(amount.getDollars(),amount.getCents());
       this.orderDate=new Date(orderDate.getMonth(),orderDate.getDay(),orderDate.getYear());
       this.customer=customer;
       this.item=item;
   }

//    /**
//     * copy constructor
//     * @param toCopy
//     */
//  public Order ( Order toCopy)
//    {
//        this.amount=toCopy.amount;
//        this.orderDate=toCopy.orderDate;
//        this.sentDate=toCopy.sentDate;
//        this.customer=toCopy.customer;
//        this.item=toCopy.item;
//
//    }

    /**
     * getter for amount
     * @return amount
     */
    public Money getAmount() {
        return new Money(amount.getDollars(),amount.getCents());
    }

    /**
     * getter for order date
     * @return orderDate
     */
    public Date getOrderDate() {
        return new Date(orderDate.getMonth(),orderDate.getDay(),orderDate.getYear());

    }

    /**
     * getter for customer name
     * @return customer
     */
    public String getCustomer() {
        return customer;
    }

    /**
     * getter for item
     * @return item
     */
    public String getItem() {
        return item;
    }

    /**
     * This function will check either an order is fulfilled(it has a valid sent date).
     * @return
     */
    public boolean isFulfilled()
    {
        if (sentDate!=null)
            return true;
        else return false;
    }

    /**
     * This function will set  send date to order if send date is after the order date
     * @param dateSent
     * @return
     */
    boolean setFulfilled( Date dateSent)
    {
        if(orderDate.isAfter(dateSent))
        {
            this.sentDate=dateSent;
            return true;
        }
        else return false;
    }

    /**
     * setter for orderDate
     * @param nextDate
     * @return
     */
    public boolean setOrderDate( Date nextDate)
    {
        if( nextDate.isAfter(orderDate))
        {
            this.orderDate=nextDate;
            return true;
        }
        else
        {
            return false;
        }
    }

    /**
     * setter for amount
     * @param amount
     * @return
     */
    public  boolean setAmount( Money amount)
    {
        if(this.amount==null || this.amount.getMoney()==0.0)
        {
            this.amount=amount;
            return true;
        }
        else return false;
    }

    /**
     * setter for customer
     * @param customer
     */
    public void setCustomer(String customer)
    {
        this.customer=customer;
    }


    /**
     * return a string representation of order reports the item, amount, when ordered, if sent and the sent date if it has been sent.
     * @return String
     */
    public String toString()
    {
        String str=" Item Name:"+item+" Amount:"+amount+" Order Date:"+orderDate;
        if(sentDate!=null)
            str+=" Sent Date:"+sentDate;
    return str;
    }

//    /**
//     * comapre two order objects
//     * @param o
//     * @return boolean
//     */
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return customer.equals(order.customer) && item.equals( order.item);
    }

//    /**
//     * compare two Order objects
//     * @param Order
//     * @return int
//     */
    @Override
    public int compareTo(Object o) {
        //if provided object o is not of Order type throw an exception
        if(! (o instanceof Order)) throw new IllegalArgumentException("The object must be of order type");

        Order order=(Order) o;
        //otherwise, simply call compareTo() method of Money class and return it
      return order.amount.compareTo(this.amount);
    }

//    /**
//     * clone Order object
//     * @return Order
//     */
    @Override
    public Order clone(){
        try {
            //Create a copy of object by calling  super.clone()
            Order order=(Order) super.clone();
            //We have to call clone() for fields of order class as there are some mutable fields inside Order class (Money,Date)
            order.setAmount(order.getAmount().clone());
            order.setOrderDate(order.getOrderDate().clone());
            //to avoid null comparison
            if(order.sentDate!=null)
            order.setFulfilled(order.sentDate.clone());

            //return the clone object
            return order;
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }
}
