package business_logic_layer;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class CourseTest {

    @Test
    void CourseTest_01() {
        //Testing Using First Constructor
        String code = "LE/CIVL 2000 3.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";
        Course newCourse = new Course(code, name, description);

        assertEquals(newCourse.getCode(), code);
        assertEquals(newCourse.getName(), name);
        assertEquals(newCourse.getDescription(), description);

        String descriptionWithoutPrereq = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. ";
        assertEquals(newCourse.getDescriptionWithoutPrereq(), descriptionWithoutPrereq);

        assertTrue(newCourse.getPrerequisites().size() == 0);
        assertTrue(newCourse.getCoursePrerequisites().size() == 0);
    }

    @Test
    void CourseTest_02() {
        String code = "LE/CIVL 2000 3.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";
        ArrayList<String> prerequisites = new ArrayList<>();
        prerequisites.add("LE/ENG 2001 3.00");
        prerequisites.add("LE/CIVL 2150 3.00");
        prerequisites.add("LE/ENG 2003 3.00");

        Course newCourse = new Course(code, name, description, prerequisites);

        assertEquals(newCourse.getCode(), code);
        assertEquals(newCourse.getName(), name);
        assertEquals(newCourse.getDescription(), description);

        String descriptionWithoutPrereq = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. ";
        assertEquals(newCourse.getDescriptionWithoutPrereq(), descriptionWithoutPrereq);

        assertTrue(newCourse.getPrerequisites().size() == 3);
        assertTrue(newCourse.getCoursePrerequisites().size() == 3);
        assertEquals(newCourse.getCoursePrerequisites().get(0).getCode(), "LE/CIVL 2150 3.00");
        assertEquals(newCourse.getCoursePrerequisites().get(1).getCode(), "LE/ENG 2001 3.00");
        assertEquals(newCourse.getCoursePrerequisites().get(2).getCode(), "LE/ENG 2003 3.00");
    }

    @Test
    void CourseTest_03() {
        String code = "LE/CIVL 2000 3.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";
        ArrayList<String> prerequisites = new ArrayList<>();

        Course newCourse = new Course(code, name, description, prerequisites);

        assertEquals(newCourse.getCode(), code);
        assertEquals(newCourse.getName(), name);
        assertEquals(newCourse.getDescription(), description);

        String descriptionWithoutPrereq = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. ";
        assertEquals(newCourse.getDescriptionWithoutPrereq(), descriptionWithoutPrereq);

        assertTrue(newCourse.getPrerequisites().size() == 0);
        assertTrue(newCourse.getCoursePrerequisites().size() == 0);
    }

    @Test
    void CourseMethod_getSubjectType_test01()
    {
        String code = "LE/CIVL 2000 3.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals(curr.getSubjectType(), "CIVL");
    }

    @Test
    void CourseMethod_getSubjectType_test02()
    {
        String code = " ";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals(curr.getSubjectType(), "");
    }

    @Test
    void CourseMethod_getFacultyType_test01()
    {
        String code = "LE/CIVL 2000 3.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals("LE", curr.getFacultyType());
    }

    @Test
    void CourseMethod_getFacultyType_test02()
    {
        String code = " ";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals("", curr.getFacultyType());
    }

    @Test
    void CourseMethod_getCourseLevel_test01()
    {
        String code = "LE/CIVL 2000 3.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals(2, curr.getLevel());
    }

    @Test
    void CourseMethod_getCourseLevel_test02()
    {
        String code = "LE/CIVL 1000 3.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals(1, curr.getLevel());
    }

    @Test
    void CourseMethod_getCourseLevel_test03()
    {
        String code = "LE/CIVL 0000 3.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals(0, curr.getLevel());
    }

    @Test
    void CourseMethod_getCourseLevel_test04()
    {
        String code = "LE/CIVL 4000 3.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals(4, curr.getLevel());
    }

    @Test
    void CourseMethod_getCourseLevel_test05()
    {
        String code = "1000 3.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals(-1, curr.getLevel());
    }

    @Test
    void CourseMethod_getCourseLevel_test06()
    {
        String code = " ";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals(-1, curr.getLevel());
    }

    @Test
    void CourseMethod_getCreditAmount_test01()
    {
        String code = "LE/CIVL 2000 3.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals(3, curr.getCreditAmount());
    }

    @Test
    void CourseMethod_getCreditAmount_test02()
    {
        String code = "LE/CIVL 2000 2.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals(2, curr.getCreditAmount());
    }

    @Test
    void CourseMethod_getCreditAmount_test03()
    {
        String code = "LE/CIVL 2000 0.00";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals(0, curr.getCreditAmount());
    }

    @Test
    void CourseMethod_getCreditAmount_test04()
    {
        String code = "CIVL 2000";
        String name = "Civil Engineering Design Project";
        String description = "Introduction to principles of engineering design via application to a suitable civil engineering project. Students work in groups of three or four, with periodic monitoring of group interaction and performance. Deliverables include a formal design report and a formal oral presentation in front of peers and invited guests. Students learning experience is enhanced through guest lectures from prominent member of civil engineering industry and academia. Prerequisites: LE/ENG 2001 3.00, LE/CIVL 2150 3.00 Corequisite: LE/ENG 2003 3.00. Date of submission: 2017-12-05";

        Course curr = new Course(code, name, description);
        assertEquals(-1, curr.getCreditAmount());
    }
}