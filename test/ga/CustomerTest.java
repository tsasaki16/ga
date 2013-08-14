package ga;

import static org.junit.Assert.*;

import org.junit.Test;

public class CustomerTest {

	@Test
	public void testGetLocation() {
		Customer c1 = new Customer(0, 1);
		double[] location = c1.getLocation();
		assertEquals(0, location[0], 0.0);
		assertEquals(1, location[1], 0.0);
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

	@Test
	public void testToString() {
		Customer c1 = new Customer(0, 1);
		assertEquals(c1.getCustomerId(), Integer.valueOf(c1.toString())
				.intValue());
	}

	@Test
	public void testGetInformation() {
		Customer c1 = new Customer(2, 1);
		assertEquals(c1.getCustomerId() + ", 2.0, 1.0", c1.getInformation());

	}

}
