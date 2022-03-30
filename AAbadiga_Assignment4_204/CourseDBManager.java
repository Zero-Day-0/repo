import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * @author Adam Abadiga
 */

public class CourseDBManager implements CourseDBManagerInterface {
	private CourseDBStructure cds;
	
	public CourseDBManager() {
		cds = new CourseDBStructure(100);
	}
	
	@Override
	public void add(String course, int crn, int credit, String room, String instructor) {
		CourseDBElement cde = new CourseDBElement(course, crn, credit, room, instructor);
		cds.add(cde);
	}
	
	@Override
	public CourseDBElement get(int crn) {
		try {
			return cds.get(crn);
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
		return null;
	}
	
	@Override
	public void readFile(File input) throws FileNotFoundException {
		Scanner sc = new Scanner(input);
		int credit, crn;
		CourseDBElement cde;
		String strings;
		String[] course;
		
		while (sc.hasNextLine()) {
			strings = sc.nextLine();
			course = strings.split(" ",5);
			crn = Integer.parseInt(course[1]);
			credit = Integer.parseInt(course[2]);
			cde = new CourseDBElement(course[0], crn, credit, course[3], course[4]);
			cds.add(cde);
		}
	}
	
	@Override
	public ArrayList<String> showAll() {
		return cds.showAll();
	}

}