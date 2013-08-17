package ga;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Collections;

import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.junit.Test;

public class RouteChromosomeTest {

	@Test
	public void testConstructor() {
		try {
			RouteChromosome.setCustomers(Arrays.asList(new Customer(0, 1)));
			RouteChromosome r = new RouteChromosome(Arrays.asList(0.1));
			assertEquals(-2.0, r.fitness(), 0.01);
		} catch (InvalidRepresentationException e) {
			fail();
		}
	}
}
