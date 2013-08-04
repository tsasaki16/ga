package ga;

import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {

	@Test
	public void testGetLocation() {
		Customer c1 = new Customer(0, 1);
		assertArrayEquals(new double[]{0, 1}, c1.getLocation(), 0);
	}

	@Test
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
