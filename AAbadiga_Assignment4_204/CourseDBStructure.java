import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

/**
 * @author Adam Abadiga
 */

public class CourseDBStructure implements CourseDBStructureInterface {

	protected int hashSize;
	protected ArrayList<LinkedList<CourseDBElement>> hashTable;
	private final double loadFactor = 1.5;

	public CourseDBStructure(int cds) {
		int i = (int) (cds / loadFactor);
		
		for (int j = 0; j < i; j++) {
			if ((4 * j + 3) > i) {
				if (isPrime(4 * j + 3)) {
					int size = 4 * j + 3;
					hashSize = size;
					break;
				}
			}
		}

		hashTable = new ArrayList<LinkedList<CourseDBElement>>(hashSize);
		
		for (int k = 0; k < hashSize; k++) {
			hashTable.add(new LinkedList<CourseDBElement>());
		}
	}

	public CourseDBStructure(String string, int cds) {
		hashSize = cds;
		hashTable = new ArrayList<LinkedList<CourseDBElement>>(hashSize);
		
		for (int i = 0; i < hashSize; i++) {
			hashTable.add(new LinkedList<CourseDBElement>());
		}
	}
	
	@Override
	public void add(CourseDBElement element) {
		int cds = Integer.parseInt(element.getHash()) % hashSize;

		if (!(hashTable.get(cds).contains(element))) {
			hashTable.get(cds).add(element);
		}

		for (int i = 0; i < hashTable.get(cds).size(); i++) {
			if (!((CourseDBElement) hashTable.get(cds).get(i)).getID().equals(element.getID())) {
				if (((CourseDBElement) hashTable.get(cds).get(i)).getCRN() == element.getCRN()) {
					hashTable.get(cds).remove(i);
					hashTable.get(cds).add(element);
				}
			}
		}

	}
	
	@Override
	public CourseDBElement get(int crn) throws IOException {
		int index = crn % hashSize;
		
		if (!(hashTable.get(index).isEmpty())) {
			for (int i = 0; i < hashTable.get(index).size(); i++) {
				if (((CourseDBElement) hashTable.get(index).get(i)).getCRN() == crn) {
					return ((CourseDBElement) hashTable.get(index).get(i));
				}
			}
		}
		throw new IOException();
	}
	
	private static boolean isPrime(int num) {
		boolean prime = false;
		if (num <= 1) {
			return prime;
		}
		for (int i = 2; i <= Math.sqrt(num); i++) {
			if (num % i == 0) {
				return prime;
			}
		}
		prime = true;
		return prime;
	}
	
	@Override
	public ArrayList<String> showAll() {
		ArrayList<String> cds = new ArrayList<String>();
		
		for (int i = 0; i < hashSize; i++) {
			if (!(hashTable.get(i).isEmpty())) {
				cds.add(hashTable.get(i).toString().replace("[", "").replace("]", ""));
			}
		}
		return cds;
	}
	
	@Override
	public int getTableSize() {
		return hashSize;
	}

}