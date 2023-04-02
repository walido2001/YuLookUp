package persistence.layer;

import business.logic.layer.Constants;
import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import business.logic.layer.Course;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.sql.*;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;

import static java.lang.Character.compare;

public class mainScraper {
    private static ArrayList<Course> courseList = null;

    /**
     * A basic delay function
     */
    public static void momentaryPause(int val)
    {

        try {
            Thread.sleep(val);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

    /**
     * This method needs to be run only once in the beginning of the semester.
     * The method webscrapes courses from the YorkU website and stores them in a JSON file.
     */
    public static void navigateToCourseList() {
//        System.setProperty("webdriver.chrome.driver", "chromedriver_mac_arm64/chromedriver.exe");
        ArrayList<Course> courseList = new ArrayList<Course>();

        WebDriver driver;
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        WebDriver driver2 = new ChromeDriver();
        driver2.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));

//        String[] FacultyXpaths = {"/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[2]/select/option[7]",
//                "/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[2]/select/option[8]",
//                "/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[2]/select/option[9]",
//                "/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[2]/select/option[10]"};
        String[] FacultyXpaths = {"/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[2]/select/option[8]",
        "/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[2]/select/option[11]"};
        String courseCampusSelectOption = "/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/form/table/tbody/tr[4]/td[2]/select[2]/option[3]";
        String courseSearchButtonClick = "/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/form/input";


        for (String xpath: FacultyXpaths)
        {
            driver.get("https://w2prod.sis.yorku.ca/Apps/WebObjects/cdm");
            WebElement advancedSearchElement = driver.findElement(By.xpath("/html/body/p/table/tbody/tr[2]/td[1]/table/tbody/tr[13]/td/a/img"));
            advancedSearchElement.click();

//            momentaryPause(1000);
            //Current Page: Filter by Subject Page

            WebElement facultySelect = driver.findElement(By.xpath(xpath));
            facultySelect.click();
            System.out.println("Looking at Faculty: " + xpath);
//            momentaryPause(500);

            WebElement subjectForm = driver.findElement(By.id("subjectSelect"));
            int subjectFormOptionsCount = subjectForm.findElements(By.tagName("option")).size();
//            List<WebElement> subjectFormOptions = subjectForm.findElements(By.tagName("option"));
            System.out.println("Subject Form Options Count: " + subjectFormOptionsCount);
            for (int sI = 1; sI <= subjectFormOptionsCount; sI++)
            {
                driver.get("https://w2prod.sis.yorku.ca/Apps/WebObjects/cdm");
                advancedSearchElement = driver.findElement(By.xpath("/html/body/p/table/tbody/tr[2]/td[1]/table/tbody/tr[13]/td/a/img"));
                advancedSearchElement.click();

                facultySelect = driver.findElement(By.xpath(xpath));
                facultySelect.click();

                try
                {
                    WebElement subjectFormOption = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/form/table/tbody/tr[2]/td[4]/select/option["+sI+"]"));
                    subjectFormOption.click();

//                momentaryPause(1000);

                    WebElement courseCampusSelect = driver.findElement(By.xpath(courseCampusSelectOption));
                    courseCampusSelect.click();

//                    momentaryPause(1000);

                    WebElement searchCourseButton = driver.findElement(By.xpath(courseSearchButtonClick));
                    searchCourseButton.click();

                    String courseElementXPath = "/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/table[2]/tbody/tr[2]/td[3]/a";
                    String course2 = "/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/table[2]/tbody/tr[3]/td[3]/a";
                    //Results Page
                    int courseElementsSize = driver.findElements(By.xpath("/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/table[2]/tbody/tr")).size() -1;
                    ArrayList<String> courseLinks = new ArrayList<>();
                    for (int i = 2 ; i <= courseElementsSize+2; i++)
                    {

                        try{
                            WebElement courseLinkElement = driver.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/table[2]/tbody/tr[" + i + "]/td[3]/a"));
                            String link = courseLinkElement.getAttribute("href");
                            courseLinks.add(link);
                        }
                        catch (Exception e)
                        {
//                            System.out.println(e.getMessage());
                            System.out.println("Error encountered getting course link");

                        }

                    }

                    for (String courseLink : courseLinks)
                    {
                        driver2.get(courseLink);

                        WebElement courseCodeTitle = driver2.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/table[1]/tbody/tr/td[1]/h1"));
                        WebElement courseDescriptionElement = driver2.findElement(By.xpath("/html/body/table/tbody/tr[2]/td[2]/table/tbody/tr[2]/td/table/tbody/tr/td/p[3]"));

                        String combined = courseCodeTitle.getText();
                        String code = combined.split("  ")[0];
                        String description = courseDescriptionElement.getText();
                        String name = combined.split("  ")[1];

                        Course newCourse = new Course(code, name, description, setPrerequisites(description, name));
                        courseList.add(newCourse);
                    }
                }
                catch (Exception e)
                {
                    System.out.println("Error encountered while navigating a course");
                }
            }


            for (Course course : courseList)
            {
                System.out.println(course.getCode() + " | " + course.getName());
            }
        }

        exportArrayListToJSON(courseList, "courses.json");

        driver.close();
        driver2.close();
    }


    /**
     * Given an arraylist of Course objects, export them into a JSON file
     */
    private static void exportArrayListToJSON(ArrayList<Course> courses, String filename)
    {
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try
        {
            FileWriter writer = new FileWriter(filename);
            writer.write(gson.toJson(courses));
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Request from a local mySQL server all the stored courses and store them into an ArrayList of Course objects
     */
    public static ArrayList<Course> getCourseList()
    {
        if (courseList != null)
        {
            return courseList;
        }

        ArrayList<Course> returnable = new ArrayList<>();

        try {
            Connection conn = DriverManager.getConnection(Constants.CONNECTION, Constants.USERNAME, Constants.PASSWORD);
            Statement statement = conn.createStatement();
            String query = "SELECT * FROM courses";
            ResultSet results = statement.executeQuery(query);
            while (results.next())
            {
                String code = results.getString("code");
                String name = results.getString("name");
                String description = results.getString("description");
                String prerequisites = results.getString("prerequisites");
                ArrayList<String> prerequisitesList = new ArrayList<>();
                prerequisites = prerequisites.substring(1, prerequisites.length()-1);
                prerequisitesList = new ArrayList<>(Arrays.asList(prerequisites.split(",")));

                for (int i = 0 ; i < prerequisitesList.size(); i++)
                {
                    prerequisitesList.set(i, prerequisitesList.get(i).trim());
                }

                Course newCourse = new Course(code, name, description, prerequisitesList);
                returnable.add(newCourse);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

//        for (Course course: returnable)
//        {
//            System.out.println(course.toString());
//        }

        return returnable;
//        Gson gson = new Gson();
//        String jsonString="";
//        try {
//            jsonString = FileUtils.readFileToString(new File("courses.json"), StandardCharsets.UTF_8);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        Type listType = new TypeToken<ArrayList<Course>>(){}.getType();
//        ArrayList<Course> returnable = gson.fromJson(jsonString, listType);
//        return returnable;
    }

    /**
     * Import all stored courses from the stub database (JSON)
     */
    public static ArrayList<Course> getCourseListFromJSON()
    {
        Gson gson = new Gson();
        String jsonString="";
        try {
            jsonString = FileUtils.readFileToString(new File("courses.json"), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Type listType = new TypeToken<ArrayList<Course>>(){}.getType();
        ArrayList<Course> returnable = gson.fromJson(jsonString, listType);
        return returnable;
    }

    /**
     * Find all occurences of a char within a string, and return a list of their indices
     */
    private static ArrayList<Integer> findInstancesOf(char target, String sample)
    {
        ArrayList<Integer> returnable = new ArrayList<>();
        for (int i = 0 ; i < sample.length(); i++)
        {
            char curr = sample.charAt(i);
            if (compare(curr, target) == 0)
            {
                returnable.add(i);
            }
        }
        return returnable;
    }


    /**
     * Extract the prerequisite course names from a course description
     */
    public static ArrayList<String> setPrerequisites(String desc, String name)
    {
        int PrereqIndex = desc.toLowerCase(Locale.ROOT).lastIndexOf("prerequisite");
        int CourseExclIndex = desc.toLowerCase(Locale.ROOT).lastIndexOf("course credit exclusion");
        System.out.println("Course: "+name+" | PrereqIndex: " + PrereqIndex + " | CourseExclIndex: " + CourseExclIndex);
        ArrayList<String> returnable = new ArrayList<>();

        boolean go1 = true;
        if (CourseExclIndex != -1 && PrereqIndex >= CourseExclIndex)
        {
            System.out.println("Course: " + name + " | Has exclusions before Prereqs");
            go1 = false;
        }

        if (PrereqIndex != -1)
        {
            if (go1)
            {
                String prereqDesc = (CourseExclIndex == -1) ? desc.substring(PrereqIndex) : desc.substring(PrereqIndex, CourseExclIndex);

                ArrayList<Integer> slashCourseLocations = findInstancesOf('/', prereqDesc);

                for (int i : slashCourseLocations)
                {
                    if (i >= 2 && i <= prereqDesc.length()-14)
                    {
                        String courseName = prereqDesc.substring(i-2, i) + prereqDesc.substring(i, i+14);
                        returnable.add(courseName);
                    }
                    else {
                        System.out.println("Index out of bounds for course " + name);
                    }
                }
            }
        }
        else
        {
            return new ArrayList<String>();
        }

        return returnable;
    }

}
