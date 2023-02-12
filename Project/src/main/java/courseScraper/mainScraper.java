package courseScraper;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import courseStructures.Course;
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
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

public class mainScraper {



    public static void main(String[] args) {
//        System.out.println("Hello");
        navigateToCourseList();
//        ArrayList<Course> courses = getCourseList();
//
//        for (Course course : courses)
//        {
//            System.out.println(course.getCode() + " | " + course.getName());
////            System.out.println(course.getDescription());
//        }


    }

    public static void momentaryPause(int val)
    {

        try {
            Thread.sleep(val);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }

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

                        Course newCourse = new Course(code, name, description);
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
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();
        try
        {
            FileWriter writer = new FileWriter("courses.json");
            writer.write(gson.toJson(courseList));
            writer.close();
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        driver.close();
        driver2.close();
    }

    public static ArrayList<Course> getCourseList()
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


}
