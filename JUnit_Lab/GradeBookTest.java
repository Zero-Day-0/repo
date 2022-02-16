import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class GradeBookTest {
	GradeBook grade1;
	GradeBook grade2;
	@BeforeEach
	//TASK THREE
	void setUp() throws Exception {
		grade1 = new GradeBook(5);
		grade2 = new GradeBook(5);
		grade1.addScore(45.0);
		grade1.addScore(99.0);
		grade1.addScore(49.0);
		grade2.addScore(87.0);
		grade2.addScore(23.0);
	}

	@AfterEach
	//TASK THREE
	void tearDown() throws Exception {
		grade1 = null;
		grade2 = null;
	}

	@Test
	void testAddScore() {
		assertTrue(grade1.toString().equals("45.0 99.0 49.0 "));
		assertEquals(3, grade1.getScoreSize());
		assertTrue(grade2.toString().equals("87.0 23.0 "));
		assertEquals(2, grade2.getScoreSize());
	}

	@Test
	void testSum() {
		assertEquals(193.0, grade1.sum());
		assertEquals(110.0, grade2.sum());
	}

	@Test
	void testMinimum() {
		assertEquals(45.0, grade1.minimum());
		assertEquals(23.0, grade2.minimum());
	}

	@Test
	void testFinalScore() {
		assertEquals(148.0, grade1.finalScore());
		assertEquals(87.0, grade2.finalScore());
	}

	@Test
	void testGetScoreSize() {
		assertEquals(3, grade1.getScoreSize());
		assertEquals(2, grade2.getScoreSize());
	}

	@Test
	void testToString() {
		assertTrue(grade1.toString().equals("45.0 99.0 49.0 "));
		assertTrue(grade2.toString().equals("87.0 23.0 "));
	}

}