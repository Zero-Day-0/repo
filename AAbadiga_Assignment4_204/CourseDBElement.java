/**
 * @author Adam Abadiga
 */

public class CourseDBElement implements Comparable <CourseDBElement> {
	
	protected String course;
	protected int crn;
	protected int credit;
	protected String room;
	protected String instructor;
	
	public CourseDBElement() {
		this (null, 00000, 0, null, null);
	}
	
	public CourseDBElement(int CRN) {
		this.crn = CRN;
	}
	
	public CourseDBElement(String course, int crn, int credit, String room, String instructor) {
		this.course = course;
		this.crn = crn;
		this.credit = credit;
		this.room = room;
		this.instructor = instructor;
	}
	
	
	public String getHash() {
		return "" + crn;
	}
	
	public String getID() {
		return course;
	}
	
	public int getCRN() {
		return crn;
	}
	
	public int getCredit() {
		return credit;
	}
	
	public String getRoomNum() {
		return room;
	}
	
	public String getInstructor() {
		return instructor;
	}
	
	public void setCourse(String course) {
		this.course = course;
	}
	
	public void setCRN(int crn) {
		this.crn = crn;
	}
	
	public void setCredits(int credit) {
		this.credit = credit;
	}
	
	public void setRoomNum(String room) {
		this.room = room;
	}
	
	public void setinstructorName(String instructor) {
		this.instructor = instructor;
	}
	
	@Override
	public int compareTo(CourseDBElement e) {
		if (e.crn == crn) {
			return 0;
		} else if (e.crn < crn) {
			return -1;
		} else {
			return 1;
		}
	}
	
	@Override
	public String toString() {
		String str = "\nCourse:" + course + " CRN:" + crn + " Credits:" + credit + " Instructor:" + instructor + " Room:" + room;
		return str;
	}
	
}