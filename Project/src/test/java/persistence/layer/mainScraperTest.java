package persistence.layer;

import business.logic.layer.Course;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.testng.annotations.BeforeTest;

import java.util.ArrayList;

import static database.Database.formDatabase;
import static org.junit.jupiter.api.Assertions.*;
import static persistence.layer.mainScraper.getCourseList;
import static persistence.layer.mainScraper.getCourseListFromJSON;

class mainScraperTest {

    @BeforeAll
    static void setupDB() throws Exception {
        formDatabase();
    }

    @Test
    void getCourseList_Test01() {
        assertNotNull(getCourseList());
    }

    @Test
    void getCourseListFromJSON_Test02() {
        assertNotNull(getCourseListFromJSON());
    }

    @Test
    void databaseIntegrationTest_01() {
        ArrayList<Course> fromDB = getCourseList();
        ArrayList<Course> fromJSON = getCourseListFromJSON();

        for (Course c1 : fromJSON)
        {
            Boolean exists = false;
            for (Course c2: fromDB)
            {
                if (c1.equals(c2))
                {
                    System.out.println(c2.getCode() + " exists in DB");
                    exists = true;
                }
            }
            assertTrue(exists);
        }

    }

}