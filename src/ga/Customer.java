package ga;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * �K���̃N���X�B ID�ƈʒu�������܂��B ID�͐����������ɐ������t�^����܂��B
 */
public class Customer implements Comparable<Customer> {

	// ID�����p�̃V�[�P���X�ԍ�
	private static AtomicInteger seq = new AtomicInteger(1);

	// �m�[�h��ID
	private int id;

	/**
	 * ���W[x,y]
	 */
	private transient double[] location;

	/**
	 * �ʒu���w�肵�ăC���X�^���X�𐶐����܂��B
	 * 
	 * @param x
	 *            X���W[0, 1]
	 * @param y
	 *            Y���W[0, 1]
	 */
	public Customer(double x, double y) {
		id = seq.getAndIncrement();
		location = new double[] { x, y };
	}

	public int getCustomerId() {
		return id;
	}

	public double[] getLocation() {
		return location;
	}

	@Override
	public int compareTo(Customer customer) {
		return id - customer.getCustomerId();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Customer) {
			return ((Customer) obj).id == id;
		}
		return false;
	}
	
	

	public String toString() {
		return String.valueOf(id);
	}
	
	public String getInformation() {
		return id + ", " + location[0] + ", " + location[1];
	}
}
