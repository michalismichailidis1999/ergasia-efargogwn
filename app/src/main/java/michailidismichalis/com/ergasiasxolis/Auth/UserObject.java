package michailidismichalis.com.ergasiasxolis.Auth;

public class UserObject {
    private String email, sex;
    private int weight, height, age;

    public UserObject(String email, String sex, int weight, int height, int age) {
        this.sex = sex;
        this.email = email;
        this.weight = weight;
        this.height = height;
        this.age = age;
    }

    public String getEmail() {
        return email;
    }

    public String getSex() {
        return sex;
    }

    public int getWeight() {
        return weight;
    }

    public int getHeight() {
        return height;
    }

    public int getAge() {
        return age;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
