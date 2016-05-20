package Model;

public class Person {
    private int userId;
    private String login;
    private Role role;
    private Student student;

    public int getUserId() {
        return userId;
    }
    public String getLogin() {
        return login;
    }
    public Role getRole() {
        return role;
    }
    public Student getStudent() {
        return student;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public void setRole(Role role) {
        this.role = role;
    }
    public void setStudent(Student student) {
        this.student = student;
    }

}
