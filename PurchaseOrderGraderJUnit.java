import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class PurchaseOrderGraderJUnit {

	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	//
	// DATE
	//
	@Test
	public void DateConstructor() {
		Date d = new Date(3,4, 2018);
		int year = d.getYear();
		int day = d.getDay();
		int month = d.getMonth();
		
		assertEquals(4, day);
		assertEquals(2018, year);
		assertEquals(3, month);
		
	}
	
	@Test 
	public void DateCopyConstructor() {
		Date origDate = new Date(3,4, 2018);
		Date d = new Date(origDate);
		
		int year = d.getYear();
		int day = d.getDay();
		int month = d.getMonth();
		
		assertEquals(4, day);
		assertEquals(2018, year);
		assertEquals(3, month);
		assertNotSame(origDate, d);
		
	}
	
	@Test
	public void DateToString() {
		Date d = new Date(3,4, 2018);
		String s = d.toString();
		assertEquals("03/04/2018",s);
	}
	
	@Test
	public void DateEquals()
	{
		Date d1 = new Date(3,4, 2018);
		Date d2 = new Date(3,4, 2018);
		
		assertEquals(d1.getDay(), d2.getDay());
		assertEquals(d1.getMonth(), d2.getMonth());
		assertEquals(d1.getYear(), d2.getYear());
	}
	
	@Test
	public void dateIsAfter()
	{
		Date d1 = new Date(3,4, 2018);
		Date d2 = new Date(3,5, 2018);
		
		assertTrue(d1.isAfter(d2));
		assertFalse(d2.isAfter(d1));
	}
	
	//
	// MONEY
	//
	@Test
	public void MoneyConstructor()
	{
		Money m = new Money(3);
		
		assertEquals(3, m.getDollars());
		assertEquals(0, m.getCents());
	}
	
	@Test 
	public void MoneyOverflow()
	{
		Money m = new Money(3, 110);
		assertEquals(4, m.getDollars());
		assertEquals(10, m.getCents());
	}
	
	@Test
	public void MoneyGet()
	{
		Money  m = new Money(4, 2);
		assertEquals(4.02, m.getMoney(), .000000001);
	}
	
	@Test
	public void MoneyAdd()
	{
		Money  m = new Money(4, 77);
		m.add(1, 50);
		assertEquals(6.27, m.getMoney(), .0000001);
		
		Money m2 = new Money(2, 80);
		m.add(m2);
		assertEquals(9.07, m.getMoney(), .0000001);	
	}
	
	@Test
	public void MoneyEquals()
	{
		Money  m = new Money(4, 77);
		Money m2 = new Money(4, 77);
		
		assertEquals(m, m2);
		
		m.add(1);
		assertNotEquals(m,  m2);
	}

	@Test
	public void MoneyToString()
	{
		Money  m = new Money(4, 10);
		assertEquals("$4.10", m.toString());
	}
	
	//
	// ORDER
	//
	
	@Test
	public void OrderConstructor()
	{
		Order b = new Order(new Money(2,33), new Date(6,12,2017), "ACME Company", "widget");
		
		assertEquals(12, b.getOrderDate().getDay());
		assertEquals("ACME Company", b.getCustomer());	
		assertEquals("widget", b.getItem());
	}
	
	@Test
	public void OrderResetDateNotFulfilled()
	{
		Order b = new Order(new Money(2,33), new Date(6,12,2017), "ACME Company", "widget");
		boolean ret = b.setOrderDate(new Date(5,5,2017));
		assertEquals(5, b.getOrderDate().getDay());
		assertTrue(ret);
	}
	
	@Test
	public void OrderResetDateFulfilled()
	{
		Order b = new Order(new Money(2,33), new Date(6,12,2017), "ACME Company", "widget");
		b.setFulfilled(new Date(7,1,2019));
		boolean ret = b.setOrderDate(new Date(5,5,2018));
		assertEquals(12, b.getOrderDate().getDay());
		assertFalse(ret);
	}
	
	@Test
	public void OrderFulfilledTest()
	{
		Order b = new Order(new Money(2,33), new Date(6,12,2017), "ACME Company", "widget");
		b.setFulfilled(new Date(6,30,2016));
		assertFalse(b.isFulfilled());
		
		b.setFulfilled(new Date(6,30,2018));
		assertTrue(b.isFulfilled());
	}
	
	@Test 
	public void OrderDatePrivacyLeaks()
	{
		// make sure date was copied when set
		Date d = new Date(6,12,2017);
		Order b = new Order(new Money(2,33), d, "ACME Company", "widget");
		
		d.setDay(10);
		Date billDate = b.getOrderDate();
		assertEquals(12, billDate.getDay());
		
		// check new get value each time
		Date d1 = b.getOrderDate();
		Date d2 = b.getOrderDate();
		assertNotSame(d1, d2);
	}
	
	@Test
	public void OrderMoneyPrivacyLeaks()
	{
		Money m = new Money(20, 10);
		Order order = new Order(m, new Date(1, 2, 2021), "ACME", "widget");
		
		Money m2 = order.getAmount();
		assertNotSame(m, m2);
		Money m3 = order.getAmount();
		assertNotSame(m2, m3);
	}
	
	@Test
	public void OrderEquals()
	{
		Order b1 = new Order(new Money(2,33), new Date(6,12,2017), "ACME Company", "widget");
		Order b2 = new Order(new Money(2,33), new Date(6,12,2017), "ACME Company", "widget");
		
		assertEquals(b1, b2);
		
		// REmoved in later version
//		b1.setOrderDate(new Date(6, 15, 2018));
//		assertNotEquals(b1,b2);
	}
	
	@Test
	public void toStringTest()
	{
		Order b1 = new Order(new Money(2,33), new Date(6,12,2017), "ACME Company", "widget");
		String s = b1.toString();
		assertTrue(s.length() > 10);
	}


}