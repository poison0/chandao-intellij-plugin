package chandao.bean;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;

public class TaskItem {
    //名字
    private String taskName;
    //点击地址
    private HtmlAnchor anchor;
    private String id;
    //优先级
    private String p;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public HtmlAnchor getAnchor() {
        return anchor;
    }

    public void setAnchor(HtmlAnchor anchor) {
        this.anchor = anchor;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getP() {
        return p;
    }

    public void setP(String p) {
        this.p = p;
    }

    @Override
    public String toString() {
        return "TaskItem{" +
                "taskName='" + taskName + '\'' +
                ", anchor=" + anchor +
                ", id='" + id + '\'' +
                ", p='" + p + '\'' +
                '}';
    }
}
