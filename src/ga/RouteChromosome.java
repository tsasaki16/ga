package ga;

import java.util.List;

import org.apache.commons.math3.genetics.AbstractListChromosome;
import org.apache.commons.math3.genetics.InvalidRepresentationException;
import org.apache.commons.math3.genetics.RandomKey;
import org.apache.commons.math3.util.MathArrays;

/**
 * �o�H�I���̐��F��
 */
public class RouteChromosome extends RandomKey<Customer> {

	// �K���
	private static List<Customer> customers;

	// �o���_
	private static double[] startPoint = new double[] { 0.0, 0.0 };

	// �K��惊�X�g��ݒ�
	protected static void setCustomers(List<Customer> customers) {
		RouteChromosome.customers = customers;
	}

	// �o���_��ݒ�
	protected static void setStartPoint(double[] startPoint) {
		RouteChromosome.startPoint = startPoint;
	}

	public RouteChromosome(List<Double> representation)
			throws InvalidRepresentationException {
		super(representation);
	}

	@Override
	public double fitness() {
		// �ړ��̑�����
		double totalDistance = 0;

		// ���݂̃p�����[�^�ɂ��K�⏇���擾
		// �p�����[�^��getRepresentaion()�Ŏ擾�ł���double�z��
		// �����List<Customer>�ɕϊ�����
		List<Customer> orderOfVisit = decode(customers);

		double[] currentPoint = startPoint; // �o���_
		for (Customer customer : orderOfVisit) {
			double[] nextPoint = customer.getLocation();
			totalDistance += MathArrays.distance(currentPoint, nextPoint);
			currentPoint = nextPoint;
		}
		// �Ō�͏o���_�܂Ŗ߂�
		totalDistance += MathArrays.distance(currentPoint, startPoint);
		// �ŏ��l�����߂�ꍇ�͕����𔽓]���ĕԂ�
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
