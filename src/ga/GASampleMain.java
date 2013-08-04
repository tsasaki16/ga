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

	// 次世代に残す割合
	private static final double ELITISM = .3;

	// 交叉の発生率
	private static final double CROSS_OVER_RATE = 0.8;

	// 突然変異率
	private static final double MUTATIONM_RATE = 0.08;

	// 個体数
	private static final int POPULATIONS = 1000;

	// 世代数
	private static final int GENERATIONS = 1000;

	// 決定したいパラメータのサイズ
	private static final int SIZE = 100;

	// トーナメント選択に参加する個体数
	private static final int ARITY = 20;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GASampleMain main = new GASampleMain();
		// 地図を生成
		main.createMap();
		main.start();
	}

	public GASampleMain() {
		// 出発点
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
		// 乱数発生器はメルセンヌ・ツイスター
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

		// 停止条件を設定　指定した世代数で停止させる
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
	 * 出発点と訪問先を生成。 [(0,0), (1,1)]の範囲にSIZE箇所ランダムで生成する。
	 */
	private void createMap() {

		// 訪問先
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
	 * 第1世代を生成する。
	 * 
	 * @return 第1世代
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
