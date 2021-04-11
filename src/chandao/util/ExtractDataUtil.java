package chandao.util;

import chandao.bean.TaskItem;
import chandao.message.Notifier;
import com.gargoylesoftware.htmlunit.html.*;
import com.intellij.openapi.ui.MessageType;

import java.util.ArrayList;
import java.util.List;

public class ExtractDataUtil {
    public static List<TaskItem> getTaskList(HtmlPage task) {
        ArrayList<TaskItem> taskItems = new ArrayList<>();
        try {
            List<HtmlTableRow> tr = task.getByXPath("//table[@id='tasktable']/tbody/tr");
            for (HtmlTableRow htmlTableRow : tr) {
                String id = htmlTableRow.getCell(0).asText().split("\r\n")[1];

                HtmlTableCell P = htmlTableRow.getCell(1);
                HtmlSpan sP  = (HtmlSpan)P.getFirstChild();

                HtmlTableCell title = htmlTableRow.getCell(3);
                DomNode firstChild = title.getFirstChild();
                DomNode domNode = firstChild.getNextSibling();
                HtmlAnchor titleA ;
                if (domNode instanceof HtmlAnchor) {
                    titleA = (HtmlAnchor) domNode;
                }else{
                    titleA = (HtmlAnchor)domNode.getNextSibling().getNextSibling();
                }
                String titleStr = title.getAttribute("title");
                TaskItem taskItem = new TaskItem();
                taskItem.setTaskName(titleStr);
                taskItem.setAnchor(titleA);
                taskItem.setP(sP.asText());
                taskItem.setId(id);
                taskItems.add(taskItem);
            }

        } catch (Exception e) {
            e.printStackTrace();
            Notifier.notify("加载任务出错"+e.getMessage(), MessageType.ERROR);
        }
        return taskItems;
    }

    public static String getNum() {
        return "";
    }
}
