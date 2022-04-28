/**
 * @author Adam Abadiga
 *
 */

public class Road implements Comparable<Road> {

	private Town s;
	private Town d;
	private String n;
	private int w;

	/**
	 * @param source !
	 * @param destination !
	 * @param name !
	 */
	public Road(Town source, Town destination, String name) {
		
		this(source, destination, 0, name);
	}

	/**
	 * @param source !
	 * @param destination !
	 * @param weight !
	 * @param name !
	 */
	public Road(Town source, Town destination, int weight, String name) {
		this.s = source;
		this.d = destination;
		this.w = weight;
		this.n = name;
	}
	
	@Override
	public boolean equals(Object i) {
		if (s.equals(((Road) i).getSource()) && d.equals(((Road) i).getDestination())) {
			return true;
		}
		return false;
	}
	
	/**
	 * @param town !
	 * @return !
	 */
	public boolean contains(Town town) {
		return s.getName().equals(town.getName()) || d.getName().equals(town.getName());
	}

	@Override
	public int compareTo(Road j) {
		if (this.n == j.n)
			return 0;
		else
			return 1;
	}
	
	/**
	 * @return !
	 */
	public Town getSource() {
		return s;
	}

	/**
	 * @return !
	 */
	public Town getDestination() {
		return d;
	}

	/**
	 * @return !
	 */
	public String getName() {
		return n;
	}

	/**
	 * @return !
	 */
	public int getWeight() {
		return w;
	}

	@Override
	public String toString() {
		return "Road [source=" + s + ", destination=" + d + ", name=" + n + ", weight=" + w
				+ "]";
	}
}