import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Iterator;

/**
 HW3.java: Simple driver to test Order, Date, and Order classes
 @author Rob Nash, borrowed from cfolsen, revised by Lesley Kalmin
 */
public class PurchaseOrderDriver
{

    /**
     main driver function
     pre:  none
     post: exercises the methods in Order, Money, and Date (not done)
     */
    public static void main(String[] args)
    {
        //Construct some money
        Money money1 = new Money(10);
        Money money2 = money1.clone();
        money1.setMoney(30,50);


        System.out.println("money1: " + money1);

        Money money3=new Money();
        money3.setMoney(20,80);
        money3.add(10);
        money3.add(20,51);
        money1.add(money3);
        System.out.println("money1: " + money1);
        money1.add(money2.getDollars());
        System.out.println("money1: " + money1);
        money1.add(money3.getDollars(),money3.getCents());
        System.out.println("money1: " + money1);


        System.out.println("Money objects output:");
        System.out.println(money1);
        System.out.println(money2);
        System.out.println("$"+money3.getMoney());

        Money money4=money1.clone();
        System.out.println("Equal Money check: "+money4.equals(money1));


        Money m = null;
        System.out.println("null check " + ((m instanceof Money)? true : false));
       


        //Construct some orders
        Money amount = new Money(20);

        Date orderDate = new Date(4, 30, 2017);
        Order order1 = new Order(amount, orderDate, "SysAdmin", "USB cable");
       
        Order order2 =order1.clone();
        order2.setCustomer("Customer1");
        order2.setFulfilled(new Date(5, 30, 2017));
        System.out.println("Setting amount after order2 fulfilled: "+order2.setAmount(new Money(40)));
        amount.setMoney(31, 99);
        orderDate.setDay(29);
        Order order3 = new Order(amount, orderDate, "Home Baker", "KitchenAid Mixer");
        order3.setFulfilled(new Date(4, 5, 21));
        
        // Try bad data with fulfilled date before order date
        Order order4 = new Order(amount, new Date(1,1,2021), "Fitness Buff", "Exercise Bike");
        boolean result = order4.setFulfilled(new Date(1, 1, 2020 ));
        System.out.println("result of bad fulfilled date is " + result);


        Order order5=new Order(new Money(),new Date(3,3,2021),"XYZ","Charger");
        System.out.println("Order5: "+order5);
        System.out.println("Changing the order Date of Order5(unpaid): "+order5.setOrderDate(new Date(5,4,2021)));
        System.out.println("Setting the amount of order5(unpaid): "+order5.setAmount(new Money(30,120)));
        System.out.println("Order5: "+order5);
        Date date=new Date(8,2,2021);
        date.setAll(4,4,2021);
        System.out.println("Fulfilling Order5(bad date): "+order5.setFulfilled(date)) ;
        System.out.println("Fulfilling Order5(correct date): "+order5.setFulfilled(new Date(7,4,2021)));
        System.out.println("Order objects output:");
        System.out.println(order1);
        System.out.println(order2);
        System.out.println(order3);
        System.out.println(order4);
        System.out.println(order5);

        Order order6=new Order(null,null,order1.getCustomer(),order1.getItem());
        System.out.println("Testing equals() Order1=order6 ? "+order1.equals(order6));


        //Testing comparable methods (Order class)
        System.out.println("Testing CompareTo()  order1.compareTo(order2)?");
        System.out.println("Order 1-> "+order1);
        System.out.println("Order 2-> "+order2);
        int ans=order1.compareTo(order2);
        System.out.println(ans<0?"Order1 is less the Order2":ans>0?"Order1 is Greater then order2":"Orders are equal");

        System.out.println();
        System.out.println("Testing CompareTo()  order3.compareTo(order4)?");
        order3=new Order(new Money(20),order1.getOrderDate(),order1.getCustomer(),order1.getItem());
        System.out.println("Order 3-> "+order3);
        System.out.println("Order 4-> "+order4);
         ans=order3.compareTo(order4);
        System.out.println(ans<0?"Order3 is less the Order4":ans>0?"Order3 is Greater then order4":"Orders are equal");

        System.out.println();
        System.out.println("Testing CompareTo()  order5.compareTo(order6)?");
        order5=new Order(new Money(80),order1.getOrderDate(),order1.getCustomer(),order1.getItem());
        order6=new Order(new Money(40),order1.getOrderDate(),order1.getCustomer(),order1.getItem());
        System.out.println("Order 3-> "+order5);
        System.out.println("Order 4-> "+order6);
        ans=order5.compareTo(order6);
        System.out.println(ans<0?"Order5 is less the Order6":ans>0?"Order5 is Greater then order6":"Orders are equal");





        //Testing clone method (Money class)
        System.out.println();
        System.out.println("Testing Clone() in Money class");
        money1=new Money(100);
        money1.add(30,4);
        System.out.println("Money 1-> "+money1);
        System.out.println("Money 2-> "+money2);
        System.out.println("After money2=money1.clone()");
        money2=money1.clone();
        System.out.println("Money 1-> "+money1);
        System.out.println("Money 2-> "+money2);


        //Testing clone method (Date class)
        System.out.println();
        System.out.println("Testing Clone() in Date class");
        Date date1=new Date(4,3,2020);
        Date date2=new Date(9,8,2021);
        System.out.println("Date1-> "+date1);
        System.out.println("Date2-> "+date2);
        System.out.println("After date2=date1.clone()");
        date2=date1.clone();
        System.out.println("Date1-> "+date1);
        System.out.println("Date2-> "+date2);



        //Testing clone method (Order class)
        System.out.println();
        System.out.println("Testing Clone() in Order class");
        System.out.println("Order1 -> "+order1);
        order2=order5.clone();
        System.out.println("Order2 -> "+order2);
        System.out.println("After order2=order1.clone()");
        order2=order1.clone();
        System.out.println("Order1 -> "+order1);
        System.out.println("Order2 -> "+order2);
        //testing memory leaks by changing order1 and checking if it has effect on order2 (if it has then there will be a memory leak)
        System.out.println("Testing Memory leaks (calling  order1.setOrderDate(new Date(1,3,2022))) ");
        order1.setOrderDate(new Date(1,3,2022));
        System.out.println("Order1 -> "+order1);
        System.out.println("Order2 -> "+order2);


    }
}
