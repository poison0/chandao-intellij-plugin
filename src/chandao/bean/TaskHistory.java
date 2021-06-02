package chandao.bean;

import java.util.List;

public class TaskHistory {
    //标签类型 0 文字 1 图片 2 事件
    private int type;
    //文字内容
    private String text;
    //图片地址
    private String url;
    //第一个名字
    private String firstName;
    //第二个名字
    private String secondName;
    //事件时间
    private String time;
    //动作
    private String action;

    private List<HistoryItem> itemList;

    public List<HistoryItem> getItemList() {
        return itemList;
    }

    public void setItemList(List<HistoryItem> itemList) {
        this.itemList = itemList;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public static class HistoryItem{
        //标签类型 0 文字 1 图片
        private int type;
        //内容
        private String text;
        //图片地址
        private String url;
        //宽度
        private int width;

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }

        public int getType() {
            return type;
        }

        public void setType(int type) {
            this.type = type;
        }

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
