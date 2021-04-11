package chandao.bean;

import java.util.List;

public class TaskDetail {
    private TaskItem taskItem;
    private List<TaskDescribe> taskDescribes;
    private List<TaskHistory> taskHistories;

    public TaskItem getTaskItem() {
        return taskItem;
    }

    public void setTaskItem(TaskItem taskItem) {
        this.taskItem = taskItem;
    }

    public List<TaskDescribe> getTaskDescribes() {
        return taskDescribes;
    }

    public void setTaskDescribes(List<TaskDescribe> taskDescribes) {
        this.taskDescribes = taskDescribes;
    }

    public List<TaskHistory> getTaskHistories() {
        return taskHistories;
    }

    public void setTaskHistories(List<TaskHistory> taskHistories) {
        this.taskHistories = taskHistories;
    }
}
