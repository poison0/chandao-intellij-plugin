package chandao.util;

import chandao.bean.TaskDescribe;
import chandao.bean.TaskDetail;
import chandao.bean.TaskHistory;
import chandao.bean.TaskItem;

import java.util.List;

public class TaskDetailUtil {
    /**
     * 生成任务描述html
     * @param taskDescribes 任务描述列表信息
     * @return java.lang.String
     */
    public static String createdDescribeHtml(List<TaskDescribe> taskDescribes) {
        StringBuilder html = new StringBuilder("<html>");
        for (TaskDescribe taskDescribe : taskDescribes) {
            if (taskDescribe.getType() == 0) {
                html.append(html).append(" <div>  ").append("   <span> ").append(taskDescribe.getText()).append(" </span>").append(" </div>");
            }else{
                html.append(html).append(" <div>  ").append("   <img style='max-width:900px' src='").append(taskDescribe.getUrl()).append("' />").append(" </div>");
            }
        }
        html.append(html).append("</html>");
        return html.toString();
    }
    /**
     * 生成任务历史
     * @param taskHistories 任务历史列表信息
     * @return java.lang.String
     */
    public static String createdHistory(List<TaskHistory> taskHistories) {
        StringBuilder html = new StringBuilder("<html>");
        for (TaskHistory taskHistory : taskHistories) {
            if (taskHistory.getType() == 0) {
                html.append(html).append(" <div>  ").append("   <span> ").append(taskHistory.getText()).append(" </span>").append(" </div>");
            }else{
                html.append(html).append(" <div>  ").append("   <img style='max-width:900px' src='").append(taskHistory.getUrl()).append("' />").append(" </div>");
            }
        }
        html.append(html).append("</html>");
        return html.toString();
    }

    public static TaskDetail generateDetails(TaskItem taskItem) {
        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setTaskItem(taskItem);
        // TODO 需要解析
        return taskDetail;
    }
}
