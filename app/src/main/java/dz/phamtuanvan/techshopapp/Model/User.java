package dz.phamtuanvan.techshopapp.Model;

public class User {
    private String fullName;
    private String age;
    private String sex;
    private String phone;

    public User() {
    }

    public User(String fullName, String age, String sex) {
        this.fullName = fullName;
        this.age = age;
        this.sex = sex;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAge() {
        return age;
    }

    public void setAge(String age) {
        this.age = age;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
