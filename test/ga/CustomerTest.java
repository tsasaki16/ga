package ga;

import junit.framework.TestCase;

public class CustomerTest extends TestCase {

	public void testGetLocation() {
		Customer c1 = new Customer(0, 1);
		double[] location = c1.getLocation();
		assertEquals(0, location[0], 0.0);
		assertEquals(1, location[1], 0.0);
	}

	public void testCompareTo() {
		Customer c1 = new Customer(0, 1);
		Customer c2 = new Customer(0, 0);
		Customer c3 = new Customer(0, 0.5);
		assertTrue(c1.compareTo(c1) == 0);
		assertTrue(c2.compareTo(c2) == 0);
		assertTrue(c3.compareTo(c3) == 0);
		assertTrue(c1.compareTo(c2) < 0);
		assertTrue(c2.compareTo(c3) < 0);
		assertTrue(c1.compareTo(c3) < 0);
		assertTrue(c3.compareTo(c2) > 0);
		assertTrue(c3.compareTo(c1) > 0);
		assertTrue(c2.compareTo(c1) > 0);
	}

}
