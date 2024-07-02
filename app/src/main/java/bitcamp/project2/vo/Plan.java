package bitcamp.project2.vo;

import java.util.Date;

public class Plan {
    private int no;
    private String title;
    private Date startDate;
    private Date endDate;
    private String repeatedDays;

    public Plan() {

    }

    public Plan(int no, String title, Date startDate, Date endDate, String repeatedDays) {
        this.no = no;
        this.title = title;
        this.startDate = startDate;
        this.endDate = endDate;
        this.repeatedDays = repeatedDays;
    }

    public int getNo() {
        return no;
    }

    public void setNo(int no) {
        this.no = no;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date dueDate) {
        this.endDate = dueDate;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getRepeatedDays() {
        return repeatedDays;
    }

    public void setRepeatedDays(String repeatedDays) {
        this.repeatedDays = repeatedDays;
    }
}
