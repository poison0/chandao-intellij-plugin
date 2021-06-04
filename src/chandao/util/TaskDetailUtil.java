package chandao.util;

import chandao.bean.*;
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
                if (taskDescribe.getText() != null && !"".equals(taskDescribe.getText())) {
                    if (taskDescribe.getText().equals("[步骤]") || taskDescribe.getText().equals("[结果]") || taskDescribe.getText().equals("[期望]")) {
                        html.append(" <div style='margin-left:10;font-weight:bold'>  ").append("   <span> ").append(taskDescribe.getText()).append(" </span>").append(" </div>");
                    } else {
                        html.append(" <div style='margin-left:20;margin-top:5'>  ").append("   <span> ").append(taskDescribe.getText()).append(" </span>").append(" </div>");
                    }
                }
            }else{
                html.append(" <div style='margin-left:20;margin-top:5'>  ").append("   <img style='max-width:500px' src='").append(taskDescribe.getUrl()).append("' />").append(" </div>");
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
            html.append("<div style='margin-left:10;color:#696969;margin-top:5'>").append(taskHistory.getTime())
                    .append("<span style='font-weight:bold'>").append(taskHistory.getFirstName()).append("</span>")
                    .append(taskHistory.getAction())
                    .append("<span style='font-weight:bold'>").append(taskHistory.getSecondName()).append("</span>");
            if (!"".equals(taskHistory.getSecondName())) {
                html.append("。</div>");
            }else {
                html.append("</div>");
            }
            for (TaskHistory.HistoryItem historyItem : taskHistory.getItemList()) {
                if (historyItem.getType() == 0) {
                    html.append("<div style='margin-left:20;margin-top:5'>").append(historyItem.getText()).append("</div>");
                } else {
                    html.append(" <div style='margin-left:20;margin-top:5'>  ").append("   <img style='max-width:500px' src='").append(historyItem.getUrl()).append("' />").append(" </div>");
                }
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
                taskHistories.add(getHistory(listItem));
            }
        } catch (Exception e) {
            e.printStackTrace();
            Notifier.notify("解析任务历史异常："+e.getMessage(), MessageType.ERROR);
        }
        return taskHistories;
    }

    /**
     * 解析一个历史节点描述
     * @author nss
     * @date 2021/5/29
     * @param item 当前节点
     */
    public static TaskHistory getHistory(HtmlListItem item) {
        TaskHistory taskHistory = new TaskHistory();
        List<TaskHistory.HistoryItem> historyItems = new ArrayList<>();
        taskHistory.setText(item.getVisibleText().replaceAll("src=\"/biz/","width=\"400\" src=\""+ URL +"/biz/"));
        DomNodeList<DomNode> childNodes = item.getChildNodes();
        int textPointer = 0;
        int namePointer = 0;
        for (DomNode childNode : childNodes) {
            if(childNode instanceof DomText){
                if (textPointer == 0) {
                    taskHistory.setTime(childNode.getVisibleText());
                }else if(textPointer == 1){
                    taskHistory.setAction(childNode.getVisibleText());
                }
                textPointer++;
            }
            if(childNode instanceof HtmlStrong){
                if (namePointer == 0) {
                    taskHistory.setFirstName(childNode.getVisibleText());
                }else if(namePointer == 1){
                    taskHistory.setSecondName(childNode.getVisibleText());
                }
                namePointer++;
            }
            if (childNode instanceof HtmlDivision) {
                HtmlDivision division = (HtmlDivision) childNode;
                if ("article-content comment".equals(division.getAttribute("class"))) {
                    DomNodeList<DomNode> nodes = division.getChildNodes();
                    for (DomNode node : nodes) {
                        if (node instanceof HtmlDivision) {
                            HtmlDivision div = (HtmlDivision) node;
                            DomNodeList<DomNode> childNodes1 = div.getChildNodes();
                            for (DomNode domNode : childNodes1) {
                                if (domNode instanceof HtmlParagraph) {
                                    HtmlParagraph paragraph = (HtmlParagraph) domNode;
                                    DomNodeList<DomNode> paragraphChildNodes = paragraph.getChildNodes();
                                    for (DomNode paragraphChildNode : paragraphChildNodes) {
                                        if (paragraphChildNode instanceof DomText) {
                                            if (!"".equals(paragraphChildNode.asText())) {
                                                TaskHistory.HistoryItem historyItem = new TaskHistory.HistoryItem();
                                                historyItem.setType(0);
                                                historyItem.setText(paragraphChildNode.asText());
                                                historyItems.add(historyItem);
                                            }
                                        }
                                        if (paragraphChildNode instanceof HtmlImage) {
                                            HtmlImage image = (HtmlImage) paragraphChildNode;
                                            TaskHistory.HistoryItem historyItem = new TaskHistory.HistoryItem();
                                            historyItem.setType(1);
                                            try {
                                                historyItem.setWidth(image.getWidth());
                                            } catch (IOException e) {
                                                historyItem.setWidth(500);
                                            }
                                            historyItem.setUrl(URL + image.getSrcAttribute());
                                            historyItems.add(historyItem);
                                        }
                                    }
                                } else if (domNode instanceof DomText) {
                                    if (!"".equals(domNode.asText())) {
                                        TaskHistory.HistoryItem historyItem = new TaskHistory.HistoryItem();
                                        historyItem.setType(0);
                                        historyItem.setText(domNode.asText());
                                        historyItems.add(historyItem);
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
        if (taskHistory.getSecondName() == null) {
            taskHistory.setSecondName("");
        }
        taskHistory.setItemList(historyItems);
        return taskHistory;
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
            if (!"".equals(text.asText())) {
                taskDescribe.setType(0);
                taskDescribe.setText(text.asText());
            }
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
        } else if (node instanceof HtmlStrong) {
            HtmlStrong strong = (HtmlStrong) node;
            taskDescribe.setType(0);
            taskDescribe.setText(strong.asText());
        }else {
            HtmlBold firstChild = (HtmlBold) node;
            DomNodeList<DomNode> childNodes = firstChild.getChildNodes();
            for (DomNode childNode : childNodes) {
                if (childNode instanceof HtmlSpan) {
                    HtmlSpan span = (HtmlSpan) childNode;
                    DomNodeList<DomNode> spanChildNodes = span.getChildNodes();
                    for (DomNode spanChildNode : spanChildNodes) {
                        taskDescribe = analyzeDom(spanChildNode);
                    }
                }else {
                    taskDescribe = analyzeDom(childNode);
                }
            }
        }
        return taskDescribe;
    }

}
