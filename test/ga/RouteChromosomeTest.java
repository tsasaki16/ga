package ga;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.util.Arrays;

import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.junit.Test;

public class RouteChromosomeTest {

	@Test
	public void testConstructor01() {
		try {
			RouteChromosome.setCustomers(Arrays.asList(new Customer(0, 1)));
			RouteChromosome r = new RouteChromosome(Arrays.asList(0.1));
			assertEquals(-2.0, r.fitness(), 0.01);
		} catch (InvalidRepresentationException e) {
			fail();
		}
	}

	@Test
	public void testConstructor02() {
		RouteChromosome.setCustomers(Arrays.asList(new Customer(0, 1), new Customer(1, 1)));
		RouteChromosome.setStartPoint(new double[] {0, 0});
		RouteChromosome r = new RouteChromosome(Arrays.asList(0.1, 0.2));
		assertEquals(-3.414, r.fitness(), 0.001);
	}

	@Test
	public void testConstructor03() {
		RouteChromosome.setCustomers(Arrays.asList(new Customer(0, 1), new Customer(0, 1)));
		RouteChromosome.setStartPoint(new double[] {0, 0});
		RouteChromosome r = new RouteChromosome(Arrays.asList(0.1, 0.2));
		assertEquals(-2.0, r.fitness(), 0.001);
	}

}
