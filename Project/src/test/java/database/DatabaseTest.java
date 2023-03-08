package database;

import business_logic_layer.Course;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static database.Database.formDatabase;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static persistence_layer.mainScraper.getCourseList;
import static persistence_layer.mainScraper.getCourseListFromJSON;

class DatabaseTest {

    @BeforeAll
    static void initialize() throws Exception {
        formDatabase();
    }

    @Test
    static void databaseContentTest_01() {
        assertNotNull(getCourseList());
    }

    //Database is populated from JSON. Therefore this test checks whether the DB contains all of the JSON values.
    @Test
    void databaseContentTest_02() {
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