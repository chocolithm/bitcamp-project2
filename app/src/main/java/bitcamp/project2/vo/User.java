package bitcamp.project2.vo;

import java.sql.Date;
import java.util.LinkedList;

public class User {
    private static int seqNo;
    private String id;
    private String password;
    private String name;
    private Date joinDate;
    private LinkedList planList;

    public int getNextSeqNo() {
        return seqNo++;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
