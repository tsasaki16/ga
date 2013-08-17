package ga;

import java.util.List;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.apache.commons.math3.genetics.RandomKey;
import org.apache.commons.math3.util.MathArrays;

/**
 * 経路選択の染色体
 */
public class RouteChromosome extends RandomKey<Customer> {

	// 訪問先
	private static List<Customer> customers;

	// 出発点
	private static double[] startPoint = new double[] { 0.0, 0.0 };

	// 訪問先リストを設定
	protected static void setCustomers(List<Customer> customers) {
		RouteChromosome.customers = customers;
	}

	// 出発点を設定
	protected static void setStartPoint(double[] startPoint) {
		RouteChromosome.startPoint = startPoint;
	}

	public RouteChromosome(List<Double> representation)
			throws InvalidRepresentationException {
		super(representation);
	}

	@Override
	public double fitness() {
		// 移動の総距離
		double totalDistance = 0;

		// 現在のパラメータによる訪問順を取得
		// パラメータはgetRepresentaion()で取得できるdouble配列
		// これをList<Customer>に変換する
		List<Customer> orderOfVisit = decode(customers);

		double[] currentPoint = startPoint; // 出発点
		for (Customer customer : orderOfVisit) {
			double[] nextPoint = customer.getLocation();
			totalDistance += MathArrays.distance(currentPoint, nextPoint);
			currentPoint = nextPoint;
		}
		// 最後は出発点まで戻る
		totalDistance += MathArrays.distance(currentPoint, startPoint);
		// 最小値を求める場合は符号を反転して返す
		return -totalDistance;
	}

	@Override
	public AbstractListChromosome<Double> newFixedLengthChromosome(
			List<Double> chromosomeRepresentation) {
		return new RouteChromosome(chromosomeRepresentation);
	}

	@Override
	public String toString() {
		return "Distance = " + Math.abs(fitness()) + "\n" + "Path = "
				+ decode(customers);

	}

}
