package BusinessLogicLayer;

import PersistenceLayer.mainScraper;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class Major {
    private LinkedHashMap<String, Course> courses;
    private String name;
    private String[] civilCourses;
    private String[] computerCourses;
    private String[] electricalCourses;
    private String[] mechanicalCourses;
    private String[] softwareCourses;
    private String[] spaceCourses;
    private LinkedHashMap<Integer, ArrayList<Course>> major;
//    private LinkedHashMap<Integer, ArrayList<Course>> civilMajor;
//    private LinkedHashMap<Integer, ArrayList<Course>> computerMajor;
//    private LinkedHashMap<Integer, ArrayList<Course>> electricalMajor;
//    private LinkedHashMap<Integer, ArrayList<Course>> mechanicalMajor;
//    private LinkedHashMap<Integer, ArrayList<Course>> softwareMajor;
//    private LinkedHashMap<Integer, ArrayList<Course>> spaceMajor;

    public Major(String name) {
        setAllCourses(mainScraper.getCourseList());
        this.name = name;
        this.major = new LinkedHashMap<>();
        //this.civilMajor = new LinkedHashMap<>();
        this.civilCourses = new String[]{"CHEM 1100", "EECS 1011", "EECS 1021", "ENG 1101", "ENG 1102", "ESSE 1012", "MATH 1013", "MATH 1014", "MATH 1025", "PHYS 1800", "PHYS 1801", "CIVL 2000", "CIVL 2120", "CIVL 2150", "CIVL 2160", "CIVL 2210", "CIVL 2220", "CIVL 2240", "ENG 2001", "ENG 2003", "MATH 2015", "MATH 2271", "MATH 2930", "CIVL 3110", "CIVL 3120", "CIVL 3130", "CIVL 3140", "CIVL 3160", "CIVL 3210", "CIVL 3220", "CIVL 3230", "CIVL 3240", "CIVL 3260", "ENG 3000", "ESSE 2210", "CIVL 4110", "CIVL 4210", "CIVL 4000", "CIVL 4001", "CIVL 4002", "CIVL 4003", "CIVL 4011", "CIVL 4013", "CIVL 4021", "CIVL 4022", "CIVL 4031", "CIVL 4033", "CIVL 4034", "CIVL 4041", "CIVL 4042", "CIVL 4043"};
        this.computerCourses =new String[]{"CHEM 1100", "EECS 1011", "EECS 1021", "EECS 1028", "ENG 1101", "ENG 1102", "MATH 1013", "MATH 1014", "MATH 1025", "PHYS 1800", "PHYS 1801", "MATH 1090", "EECS 2011", "EECS 2021", "EECS 2030", "EECS 2032", "EECS 2200", "EECS 2210", "ENG 2001", "ENG 2003", "MATH 2015", "MATH 2930", "PHYS 2020", "PHYS 2211", "ENG 3000", "EECS 3101", "EECS 3201", "EECS 3213", "EECS 3216", "EECS 3221", "EECS 3311", "EECS 3451", "ESSE 2210", "ENG 4000", "EECS 4201", "EECS 4214", "EECS 4312"};
        this.electricalCourses = new String[]{"CHEM 1100", "EECS 1011", "EECS 1021", "EECS 1028", "ENG 1101", "ENG 1102", "MATH 1013", "MATH 1014", "MATH 1025", "PHYS 1800", "PHYS 1801", "EECS 2021", "EECS 2032", "EECS 2200", "EECS 2210", "EECS 3451", "ENG 2001", "ENG 2003", "MATH 2015", "MATH 2930", "PHYS 2020", "PHYS 2211", "EECS 3201", "EECS 3604", "EECS 3622", "ENG 3000", "ESSE 2210", "ENG 4550", "ENG 4000", "EECS 3603", "EECS 3611", "EECS 3641", "EECS 3216", "EECS 4214", "EECS 4612", "EECS 4614", "EECS 4623", "EECS 4640", "EECS 4643", "EECS 3213", "EECS 3214", "EECS 3221", "EECS 4201", "EECS 4215", "EECS 4404", "EECS 4413", "EECS 4421", "EECS 4422", "EECS 4471", "ENG 4650"};
        this.mechanicalCourses = new String[]{"CHEM 1100", "EECS 1011", "EECS 1021", "ENG 1101", "ENG 1102", "ESSE 1012", "MATH 1013", "MATH 1014", "MATH 1025", "PHYS 1800", "PHYS 1801", "ENG 2001", "ENG 2003", "MATH 2015", "MATH 2271", "MATH 2930", "MECH 2201", "MECH 2202", "MECH 2301", "MECH 2302", "MECH 2401", "MECH 2412", "MECH 2502", "ESSE 2210", "MECH 2112", "EECS 3505", "MECH 3201", "MECH 3202", "MECH 3203", "MECH 3302", "MECH 3401", "MECH 3409", "MECH 3502", "MECH 3503", "MECH 3504", "ENG 3000", "ENG 4000", "ENG 4550", "MECH 4401", "MECH 4402", "MECH 4502", "MECH 4504", "MECH 4201", "MECH 4202", "MECH 4203", "MECH 4301", "MECH 4510", "ENG 4650"};
        this.softwareCourses = new String[]{"CHEM 1100", "EECS 1011", "EECS 1021", "EECS 1028", "ENG 1101", "ENG 1102", "MATH 1013", "MATH 1014", "MATH 1025", "PHYS 1800", "PHYS 1801", "MATH 1090", "EECS 2011", "EECS 2021 ", "EECS 2030", "EECS 2032", "EECS 2200", "EECS 2311", "ENG 2001", "ENG 2003", "MATH 2015", "MATH 2930", "PHYS 2020", "PHYS 2211", "ENG 3000", "EECS 3101", "EECS 3201", "EECS 3213", "EECS 3216", "EECS 3221", "EECS 3311", "EECS 3451", "EECS 3342", "ESSE 2210", "EECS 3216", "EECS 3214", "EECS 3421", "EECS 3461", "EECS 3481", "EECS 3482", "EECS 4214", "EECS 4215", "EECS 4411", "EECS 4412", "EECS 4441", "EECS 4481", "EECS 4482", "EECS 4404", "EECS 4312", "EECS 4313", "EECS 4314", "EECS 4315", "EECS 4413", "ENG 4000", "ENG 4550"};
        this.spaceCourses = new String[]{"CHEM 1100", "EECS 1011", "EECS 1021", "ENG 1101", "ENG 1102", "ESSE 1012", "MATH 1013", "MATH 1014", "MATH 1025", "PHYS 1800", "PHYS 1801", "ENG 2001", "ENG 2003", "ESSE 2030", "ESSE 2220", "ESSE 2361", "ESSE 2470", "MECH 2302", "MECH 2401", "MATH 2015", "MATH 2271", "MATH 2930", "PHYS 2020", "ENG 3000", "ESSE 3330", "ESSE 2210", "ESSE 3280", "ESSE 3360", "ESSE 4110", "MECH 3302", "PHYS 2030", "PHYS 3050", "PHYS 3150", "PHYS 3250", "ENG 4000", "ESSE 4020", "ESSE 4350", "ESSE 4360", "ESSE 4361", "ESSE 4370", "ENG 4550", "EECS 4421", "ENG 3320", "ESSE 3320", "ENG 4330", "ENG 4650", "ESSE 3020", "ESSE 3670", "ESSE 4220", "ESSE 4230", "PHYS 3070", "PHYS 4120"};
        this.major = setMajor();
    }

    public LinkedHashMap<Integer, ArrayList<Course>> setMajor(){
        String[] majorCourses = null;
        if(name.equals("Civil Engineering")) majorCourses = civilCourses;
        else if(name.equals("Computer Engineering")) majorCourses = computerCourses;
        else if(name.equals("Electrical Engineering")) majorCourses = electricalCourses;
        else if(name.equals("Mechanical Engineering")) majorCourses = mechanicalCourses;
        else if(name.equals("Software Engineering")) majorCourses = softwareCourses;
        else if(name.equals("Space Engineering")) majorCourses = spaceCourses;

        LinkedHashMap<Integer, ArrayList<Course>> major = new LinkedHashMap<>();
        ArrayList<Course> level1 = new ArrayList<>();
        ArrayList<Course> level2 = new ArrayList<>();
        ArrayList<Course> level3 = new ArrayList<>();
        ArrayList<Course> level4 = new ArrayList<>();

        for(int i = 0; i<majorCourses.length; i++){
            Course course = courses.get(majorCourses[i]);
            if(course!=null) {
                if (getCourseLevel(course) == 1) level1.add(course);
                else if (getCourseLevel(course) == 2) level2.add(course);
                else if (getCourseLevel(course) == 3) level3.add(course);
                else if (getCourseLevel(course) == 4) level4.add(course);
            }
        }
        major.put(1, level1);
        major.put(2, level2);
        major.put(3, level3);
        major.put(4, level4);
        return major;
    }

    public LinkedHashMap<Integer, ArrayList<Course>> getMajor(){
        return major;
    }

    public void setAllCourses(ArrayList<Course> list){
        LinkedHashMap<String, Course> hashMap = new LinkedHashMap<>();
        for (int i= 0; i<list.size(); i++)
            hashMap.put(getShortCode(list.get(i)), list.get(i));
        this.courses = hashMap;
    }

    public String getName() {
        return name;
    }

    public String getShortCode(Course course) {
        return course.getCode().substring(3, course.getCode().length()-5);
    }

    public int getCourseLevel(Course course){
        String code = getShortCode(course);
        return Character.getNumericValue(code.charAt(code.length()-4));
    }
}
