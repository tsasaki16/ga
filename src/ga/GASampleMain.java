package ga;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.commons.math3.genetics.Chromosome;
import org.apache.commons.math3.genetics.ElitisticListPopulation;
import org.apache.commons.math3.genetics.FixedGenerationCount;
import org.apache.commons.math3.genetics.GeneticAlgorithm;
import org.apache.commons.math3.genetics.Population;
import org.apache.commons.math3.genetics.RandomKeyMutation;
import org.apache.commons.math3.genetics.StoppingCondition;
import org.apache.commons.math3.genetics.TournamentSelection;
import org.apache.commons.math3.genetics.UniformCrossover;
import org.apache.commons.math3.random.MersenneTwister;

public class GASampleMain {

	// ������Ɏc������
	private static final double ELITISM = .3;

	// �����̔�����
	private static final double CROSS_OVER_RATE = 0.8;

	// �ˑR�ψٗ�
	private static final double MUTATIONM_RATE = 0.08;

	// �̐�
	private static final int POPULATIONS = 1000;

	// ���㐔
	private static final int GENERATIONS = 1000;

	// ���肵�����p�����[�^�̃T�C�Y
	private static final int SIZE = 100;

	// �g�[�i�����g�I���ɎQ������̐�
	private static final int ARITY = 20;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GASampleMain main = new GASampleMain();
		// �n�}�𐶐�
		main.createMap();
		main.start();
	}

	public GASampleMain() {
		// �o���_
		RouteChromosome.setStartPoint(new double[] { 0.5, 0.5 });
	}

	/**
	 * Add customers.
	 * 
	 * @param points
	 *            List of coordinates of customers (x, y).Each coordinate must
	 *            be length 2 array.
	 */
	public void addCustomers(List<double[]> points) {
		List<Customer> customers = new ArrayList<Customer>(SIZE);
		for (double[] point : points) {
			if (point.length != 2) {
				throw new IllegalArgumentException("length of point is "
						+ point.length + ", " + Arrays.toString(point));
			}
			customers.add(new Customer(point[0], point[1]));
		}
		RouteChromosome.setCustomers(customers);
	}

	public void start() {
		// ����������̓����Z���k�E�c�C�X�^�[
		GeneticAlgorithm.setRandomGenerator(new MersenneTwister());
		// initialize a new genetic algorithm
		GeneticAlgorithm ga = new GeneticAlgorithm(
				new UniformCrossover<Integer>(0.5), CROSS_OVER_RATE,
				new RandomKeyMutation(), MUTATIONM_RATE,
				new TournamentSelection(ARITY)) {

			@Override
			public Population nextGeneration(Population current) {
				System.out.println(current.getFittestChromosome());
				return super.nextGeneration(current);
			}

		};

		// initial population
		Population initial = new ElitisticListPopulation(getInit(),
				POPULATIONS, ELITISM);

		// ��~������ݒ�@�w�肵�����㐔�Œ�~������
		StoppingCondition stopCond = new FixedGenerationCount(GENERATIONS);

		System.out.println("Start Fitting.");
		// run the algorithm
		Population finalPopulation = ga.evolve(initial, stopCond);

		// best chromosome from the final population
		RouteChromosome bestFinal = (RouteChromosome) finalPopulation
				.getFittestChromosome();

		System.out.println(bestFinal);

	}

	/**
	 * �o���_�ƖK���𐶐��B [(0,0), (1,1)]�͈̔͂�SIZE�ӏ������_���Ő�������B
	 */
	private void createMap() {

		// �K���
		MersenneTwister twister = new MersenneTwister();
		List<Customer> customers = new ArrayList<Customer>(SIZE);
		System.out.println("Customers:");
		System.out.println("ID, x, y");
		for (int i = 0; i < SIZE; i++) {
			Customer c = new Customer(twister.nextDouble(),
					twister.nextDouble());
			customers.add(c);
			System.out.println(c.getInformation());
		}
		RouteChromosome.setCustomers(customers);
	}

	/**
	 * ��1����𐶐�����B
	 * 
	 * @return ��1����
	 */
	private List<Chromosome> getInit() {
		Double[] init = new Double[SIZE];
		for (int i = 0; i < SIZE; i++) {
			init[i] = Math.random();
		}
		Chromosome p = new RouteChromosome(Arrays.asList(init));
		return Collections.nCopies(POPULATIONS, p);
	}
}
