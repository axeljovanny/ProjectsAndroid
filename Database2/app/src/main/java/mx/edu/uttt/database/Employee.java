package mx.edu.uttt.database;

/**
 * Created by qas on 23/08/16.
 */
public class Employee {
    private int id;
    private String name;
    private String rfc;
    private String phone;

    public Employee() {
        this.name = "";
        this.rfc = "";
        this.phone = "";
    }

    public Employee(String name, String rfc, String phone) {
        this.id = 0;
        this.name = name;
        this.rfc = rfc;
        this.phone = phone;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRfc() {
        return rfc;
    }

    public void setRfc(String rfc) {
        this.rfc = rfc;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", rfc='" + rfc + '\'' +
                ", phone='" + phone + '\'' +
                '}';
    }
}
