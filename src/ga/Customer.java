package ga;

/**
 * 訪問先のクラス。 IDと位置を持ちます。 IDは生成した順に整数が付与されます。
 */
public class Customer implements Comparable<Customer> {

	// ID生成用のシーケンス番号
	private static int seq = 1;

	// ノードのID
	private int id;

	/**
	 * 座標[x,y]
	 */
	private double[] location;

	/**
	 * 位置を指定してインスタンスを生成します。
	 * 
	 * @param x
	 *            X座標[0, 1]
	 * @param y
	 *            Y座標[0, 1]
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
