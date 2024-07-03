package bitcamp.project2.vo;

import java.sql.Date;
import java.util.LinkedList;

public class User {
    private String name;
    private String password;
    private Date joinDate;
    private static LinkedList<Plan> planList;

    public User() {

    }

    public User(String name, String password, Date joinDate, LinkedList<Plan> planList) {
        this.name = name;
        this.password = password;
        this.joinDate = joinDate;
        this.planList = planList;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getJoinDate() {
        return joinDate;
    }

    public void setJoinDate(Date joinDate) {
        this.joinDate = joinDate;
    }

    public LinkedList getPlanList() {
        return planList;
    }

    public void setPlanList(LinkedList planList) {
        this.planList = planList;
    }


}
