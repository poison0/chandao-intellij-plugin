package chandao.util;

import chandao.bean.TaskDescribe;
import chandao.bean.TaskDetail;
import chandao.bean.TaskHistory;
import chandao.bean.TaskItem;
import chandao.message.Notifier;
import com.gargoylesoftware.htmlunit.Page;
import com.gargoylesoftware.htmlunit.html.*;
import com.intellij.openapi.progress.ProgressIndicator;
import com.intellij.openapi.progress.ProgressManager;
import com.intellij.openapi.progress.Task;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.MessageType;
import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static chandao.data.LogInData.URL;

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
                html.append(" <div>  ").append("   <span> ").append(taskDescribe.getText()).append(" </span>").append(" </div>");
            }else{
                html.append(" <div>  ").append("   <img style='max-width:500px' src='").append(taskDescribe.getUrl()).append("' />").append(" </div>");
            }
        }
        html.append("</html>");
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
                html.append(" <div>  ").append("   <span> ").append(taskHistory.getText()).append(" </span>").append(" </div>");
            }else{
                html.append(" <div>  ").append("   <img style='max-width:500px' src='").append(taskHistory.getUrl()).append("' />").append(" </div>");
            }
        }
        html.append("</html>");
        return html.toString();
    }

    public static TaskDetail generateDetails( TaskItem taskItem) {
        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setTaskItem(taskItem);
        HtmlAnchor anchor = taskItem.getAnchor();
        try {
            HtmlPage click = anchor.click();
            taskDetail.setTaskDescribes(getTaskDescribe(click));
            taskDetail.setTaskHistories(new ArrayList<>());
        } catch (IOException e) {
            e.printStackTrace();
            Notifier.notify(e.getMessage(), MessageType.ERROR);
        }
        // TODO 需要解析
        return taskDetail;
    }

    public static List<TaskDescribe> getTaskDescribe(HtmlPage page) {
        List<TaskDescribe> describes = new ArrayList<>();
        List<HtmlParagraph> paragraphList = page.getByXPath("//div[@class='detail-content article-content']/p");
        for (HtmlParagraph paragraph : paragraphList) {
            HtmlBold firstChild = (HtmlBold)paragraph.getFirstChild();
            DomNodeList<DomNode> childNodes = firstChild.getChildNodes();
            for (DomNode childNode : childNodes) {
                HtmlSpan span = (HtmlSpan) childNode;
                DomNodeList<DomNode> spanChildNodes = span.getChildNodes();
                for (DomNode spanChildNode : spanChildNodes) {
                    if(spanChildNode instanceof DomText){
                        DomText text = (DomText) spanChildNode;
                        TaskDescribe taskDescribe = new TaskDescribe();
                        taskDescribe.setType(0);
                        taskDescribe.setText(text.asText());
                        describes.add(taskDescribe);
                    }else if(spanChildNode instanceof HtmlImage){
                        HtmlImage image = (HtmlImage) spanChildNode;
                        TaskDescribe taskDescribe = new TaskDescribe();
                        taskDescribe.setType(1);
                        try {
                            taskDescribe.setWidth(image.getWidth());
                        } catch (IOException e) {
                            taskDescribe.setWidth(500);
                        }
                        taskDescribe.setUrl(URL+image.getSrcAttribute());
                        describes.add(taskDescribe);
                    }
                }
            }
        }
        return describes;
    }

}
