package chandao.bean;

import com.gargoylesoftware.htmlunit.html.HtmlAnchor;

public class TaskItem {
    //名字
    private String taskName;
    //点击地址
    private HtmlAnchor anchor;
    //id
    private String id;
    //优先级
    private String p;
    //是否是子任务
    private boolean isChild;
    //uid
    private String uid;
    /**
     * 类型 0任务 1bug 2任务标题 3bug标题
     */
    private int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public boolean isChild() {
        return isChild;
    }

    public void setChild(boolean child) {
        isChild = child;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

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
