package postPayload2;


public class RequestPOJO {
    private String name;
    private String location;
    private String phone;
    private String[] coursesArr;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String[] getCoursesArr() {
        return coursesArr;
    }

    public void setCoursesArr(String[] coursesArr) {
        this.coursesArr = coursesArr;
    }



}
