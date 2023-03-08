package business_logic_layer;

import persistence_layer.mainScraper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TakenCourseTest {
    Course course1, course2, course3, course4, course5, course6, course7, course8, course9, course10;
    TakenCourse taken1, taken2, taken3, taken4, taken5, taken6, taken7, taken8, taken9, taken10;


    @BeforeEach
    void setUp() {
        course1 = mainScraper.getCourseList().get(2);
        course2 = mainScraper.getCourseList().get(13);
        course3 = mainScraper.getCourseList().get(32);
        course4 = mainScraper.getCourseList().get(37);
        course5 = mainScraper.getCourseList().get(53);
        course6 = mainScraper.getCourseList().get(86);
        course7 = mainScraper.getCourseList().get(109);
        course8 = mainScraper.getCourseList().get(154);
        course9 = mainScraper.getCourseList().get(177);
        course10 = mainScraper.getCourseList().get(220);

        taken1 = new TakenCourse(course1, "A+");
        taken2 = new TakenCourse(course2, "C");
        taken3 = new TakenCourse(course3, "D");
        taken4 = new TakenCourse(course4, "C+");
        taken5 = new TakenCourse(course5, "B");
        taken6 = new TakenCourse(course6, "A");
        taken7 = new TakenCourse(course7, "D+");
        taken8 = new TakenCourse(course8, "B+");
        taken9 = new TakenCourse(course9, "B+");
        taken10 = new TakenCourse(course10, "A");
    }

    @Test
    void getCourseTest() {
        assertEquals(course1, taken1.getCourse());
        assertEquals(course2, taken2.getCourse());
        assertEquals(course3, taken3.getCourse());
        assertEquals(course4, taken4.getCourse());
        assertEquals(course5, taken5.getCourse());
        assertEquals(course6, taken6.getCourse());
        assertEquals(course7, taken7.getCourse());
        assertEquals(course8, taken8.getCourse());
        assertEquals(course9, taken9.getCourse());
        assertEquals(course10, taken10.getCourse());
    }

    @Test
    void getCourseCodeTest() {
        assertEquals(course1.getCode(), taken1.getCourseCode());
        assertEquals(course2.getCode(), taken2.getCourseCode());
        assertEquals(course3.getCode(), taken3.getCourseCode());
        assertEquals(course4.getCode(), taken4.getCourseCode());
        assertEquals(course5.getCode(), taken5.getCourseCode());
        assertEquals(course6.getCode(), taken6.getCourseCode());
        assertEquals(course7.getCode(), taken7.getCourseCode());
        assertEquals(course8.getCode(), taken8.getCourseCode());
        assertEquals(course9.getCode(), taken9.getCourseCode());
        assertEquals(course10.getCode(), taken10.getCourseCode());
    }

    @Test
    void getGradeTest() {
        assertEquals("A+", taken1.getGrade());
        assertEquals("C", taken2.getGrade());
        assertEquals("D", taken3.getGrade());
        assertEquals("C+", taken4.getGrade());
        assertEquals("B", taken5.getGrade());
        assertEquals("A", taken6.getGrade());
        assertEquals("D+", taken7.getGrade());
        assertEquals("B+", taken8.getGrade());
        assertEquals("B+", taken9.getGrade());
        assertEquals("A", taken10.getGrade());
    }

    @Test
    void getGradeValTest() {
        assertEquals(9, taken1.getGradeVal());
        assertEquals(4, taken2.getGradeVal());
        assertEquals(2, taken3.getGradeVal());
        assertEquals(5, taken4.getGradeVal());
        assertEquals(6, taken5.getGradeVal());
        assertEquals(8, taken6.getGradeVal());
        assertEquals(3, taken7.getGradeVal());
        assertEquals(7, taken8.getGradeVal());
        assertEquals(7, taken9.getGradeVal());
        assertEquals(8, taken10.getGradeVal());
    }

    @Test
    void getCreditTest() {
        assertEquals(3.00, taken1.getCredit());
        assertEquals(3.00, taken2.getCredit());
        assertEquals(3.00, taken3.getCredit());
        assertEquals(3.00, taken4.getCredit());
        assertEquals(3.00, taken5.getCredit());
        assertEquals(4.00, taken6.getCredit());
        assertEquals(3.00, taken7.getCredit());
        assertEquals(3.00, taken8.getCredit());
        assertEquals(3.00, taken9.getCredit());
        assertEquals(3.00, taken10.getCredit());
    }
}