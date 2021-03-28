package chandao.data;

import chandao.bean.TaskItem;

public class DataConvert {
    public static String[] covert(TaskItem data) {
        String[] raw = new String[4];
        raw[0] = data.getId();
        raw[1] = data.getP();
        raw[2] = data.getTaskName();
        return raw;
    }
}
