package chandao.util;

import chandao.bean.TaskDescribe;
import chandao.bean.TaskDetail;
import chandao.bean.TaskHistory;
import chandao.bean.TaskItem;
import chandao.message.Notifier;
import com.gargoylesoftware.htmlunit.html.*;
import com.intellij.openapi.ui.MessageType;

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
            String[] split = taskHistory.getText().split("\n");
            for (String s : split) {
                html.append(s).append("<br/>");
            }
        }
        html.append("</html>");
        return html.toString();
    }
    /**
     * 解析页面
     * @param taskItem 当前页面信息
     * @return java.lang.String
     */
    public static TaskDetail generateDetails(TaskItem taskItem) {
        TaskDetail taskDetail = new TaskDetail();
        taskDetail.setTaskItem(taskItem);
        HtmlAnchor anchor = taskItem.getAnchor();
        try {
            HtmlPage click = anchor.click();
            taskDetail.setTaskDescribes(getTaskDescribe(click));
            taskDetail.setTaskHistories(getTaskHistory(click));
        } catch (IOException e) {
            e.printStackTrace();
            Notifier.notify(e.getMessage(), MessageType.ERROR);
        }
        return taskDetail;
    }
    /**
     * 解析历史记录
     * @author nss
     * @date 2021/5/29
     * @param page 当前页面
     */
    public static List<TaskHistory> getTaskHistory(HtmlPage page) {
        List<TaskHistory> taskHistories = new ArrayList<>();
        try {
            List<HtmlListItem> listItems = page.getByXPath("//ol[@class='histories-list']/li");
            for (HtmlListItem listItem : listItems) {
                System.out.println(listItem.getVisibleText());
                TaskHistory taskHistory = new TaskHistory();
                taskHistory.setText(listItem.getVisibleText().replaceAll("src=\"/biz/","width=\"400\" src=\""+ URL +"/biz/"));
                taskHistories.add(taskHistory);
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifier.notify("解析任务历史异常："+e.getMessage(), MessageType.ERROR);
        }
        return taskHistories;
    }
    /**
     * 解析任务描述
     * @author nss
     * @date 2021/5/29
     * @param page 当前页面
     */
    public static List<TaskDescribe> getTaskDescribe(HtmlPage page) {
        List<TaskDescribe> describes = new ArrayList<>();
        try {
            List<HtmlParagraph> paragraphList = page.getByXPath("//div[@class='detail-content article-content']/p");
            for (HtmlParagraph paragraph : paragraphList) {
                DomNodeList<DomNode> childElements = paragraph.getChildNodes();
                for (DomNode next : childElements) {
                    describes.add(analyzeDom(next));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifier.notify("解析任务描述异常："+e.getMessage(), MessageType.ERROR);
        }
        return describes;
    }

    /**
     * 解析任务描述的一个node节点
     * @author nss
     * @date 2021/5/29
     * @param node 任务描述的node节点
     */
    static TaskDescribe analyzeDom(DomNode node) {
        TaskDescribe taskDescribe = new TaskDescribe();
        if (node instanceof DomText) {
            DomText text = (DomText) node;
            taskDescribe.setType(0);
            taskDescribe.setText(text.asText());
        } else if (node instanceof HtmlBreak) {
            taskDescribe.setType(0);
            taskDescribe.setText("");
        } else if (node instanceof HtmlImage) {
            HtmlImage image = (HtmlImage) node;
            taskDescribe.setType(1);
            try {
                taskDescribe.setWidth(image.getWidth());
            } catch (IOException e) {
                taskDescribe.setWidth(500);
            }
            taskDescribe.setUrl(URL + image.getSrcAttribute());
        } else if (node instanceof HtmlAnchor) {
            HtmlAnchor anchor = (HtmlAnchor) node;
            taskDescribe.setType(0);
            taskDescribe.setText(anchor.asText());
        } else if (node instanceof HtmlSpan) {
            HtmlSpan anchor = (HtmlSpan) node;
            taskDescribe.setType(0);
            taskDescribe.setText(anchor.asText());
        } else {
            HtmlBold firstChild = (HtmlBold) node;
            DomNodeList<DomNode> childNodes = firstChild.getChildNodes();
            for (DomNode childNode : childNodes) {
                HtmlSpan span = (HtmlSpan) childNode;
                DomNodeList<DomNode> spanChildNodes = span.getChildNodes();
                for (DomNode spanChildNode : spanChildNodes) {
                    taskDescribe = analyzeDom(spanChildNode);
                }
            }
        }
        return taskDescribe;
    }

}
