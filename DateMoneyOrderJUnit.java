/*
 * JUnit tests for Date, Money, and Bill classes
 * @author Lesley Kalmin
 *  CSS143
 */

import static org.junit.Assert.*;

import org.junit.Test;

public class DateMoneyOrderJUnit {

	@Test
	public void ConstructMoneyTest() {
		Money money1 = new Money(10);
		
		assertEquals(10, money1.getDollars());		
	}
	
	@Test
	public void SetMoneyTest()
	{
		Money money1 = new Money();
		
		money1.setMoney(30,50);
        assertEquals(30, money1.getDollars());      
        assertEquals(50, money1.getCents());
	}
	
	@Test
	public void ConstructMoneyCopyTest() {
		Money money1 = new Money();
		money1.setMoney(10,40);		
		
        Money money2 = money1.clone();
        
        assertEquals(10, money1.getDollars());       
        assertEquals(40, money2.getCents());
	}
	
	@Test
	public void PrintMoneyTest()
	{
		Money money1 = new Money(10);

        money1.setMoney(30,50);
        assertEquals("$30.50", money1.toString());
		
	}

	@Test
	public void moneyComparableTestTrueCase(){
		Money money1 = new Money(10);
		Money money2 = new Money(10);

		assertEquals(0,money1.compareTo(money2));
	}

	@Test
	public void moneyComparableTestFalseCase(){
		Money money1 = new Money(10);
		Money money2 = new Money(7);

		assertEquals(1,money1.compareTo(money2));
	}


	@Test
	public void dateComparableTestTrueCase(){
		Date date=new Date(1,2,2022);
		Date date2=new Date(1,2,2022);

		assertEquals(0,date.compareTo(date2));
	}

	@Test
	public void dateComparableTestFalseCase(){
		Date date=new Date(1,2,2022);
		Date date2=new Date(1,1,2022);

		assertEquals(1,date.compareTo(date2));
	}

	@Test
	public void orderComparableTestTrueCase(){
		Date date=new Date(1,2,2022);
		Money money=new Money(10);
		Order order =new Order(money,date,"Brady","Basket");

		Order order1=order.clone();

		assertEquals(0,order.compareTo(order1));
	}


	@Test
	public void orderComparableTestFalseCase(){
		Date date=new Date(1,2,2022);
		Money money=new Money(10);
		Order order =new Order(money,date,"Brady","Basket");

		Money money2=new Money(11);
		Order order1=new Order(money2,date,"Brady","Basket");

		assertEquals(1,order.compareTo(order1));
	}

}
