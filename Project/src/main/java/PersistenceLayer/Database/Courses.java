package PersistenceLayer.Database;

import java.io.Serializable;

public class Courses {
    private String code;
    private String name;
    private String description;
    private String prerequisites;

    public Courses() {
        code = null;
        name = null;
        description = null;
        prerequisites = null;
    }

    public String getPrerequisites() {
        return prerequisites;
    }
    public void setPrerequistes(String prerequistes) {
        this.prerequisites = prerequistes;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
