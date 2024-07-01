package bitcamp.project2.vo;

import java.util.Date;

public class Plan {
    private static int seqNo;
    private int no;
    private Date startDate;
    private Date endDate;
    private String title;

    public int getNextSeqNo() {
        return seqNo++;
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
}
