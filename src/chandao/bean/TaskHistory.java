package chandao.bean;

public class TaskHistory {
    //标签类型 0 文字 1 图片
    private int type;
    //文字内容
    private String text;
    //图片地址
    private String url;

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
