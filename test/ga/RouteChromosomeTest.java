package ga;

import static org.junit.Assert.*;

import java.util.Collections;

import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.junit.Test;

public class RouteChromosomeTest {

	@Test
	public void testConstructor() {
		try {
			new RouteChromosome(Collections.EMPTY_LIST);
			fail();
		} catch (InvalidRepresentationException e) {
			
		}
	}
}
