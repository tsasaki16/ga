package ga;

/**
 * �K���̃N���X�B ID�ƈʒu�������܂��B ID�͐����������ɐ������t�^����܂��B
 */
public class Customer implements Comparable<Customer> {

	// ID�����p�̃V�[�P���X�ԍ�
	private static int seq = 1;

	// �m�[�h��ID
	private int id;

	/**
	 * ���W[x,y]
	 */
	private double[] location;

	/**
	 * �ʒu���w�肵�ăC���X�^���X�𐶐����܂��B
	 * 
	 * @param x
	 *            X���W[0, 1]
	 * @param y
	 *            Y���W[0, 1]
	 */
	public Customer(double x, double y) {
		id = seq++;
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
		return Integer.compare(id, customer.getCustomerId());
	}

	public String toString() {
		return String.valueOf(id);
	}
	
	public String getInformation() {
		return id + ", " + location[0] + ", " + location[1];
	}
}
