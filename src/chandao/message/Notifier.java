package chandao.message;

import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationGroup;
import com.intellij.notification.Notifications;
import com.intellij.openapi.ui.MessageType;

/**
 * 异常弹框
 */
public class Notifier {
    //通知弹框
    public static void notify(String content,MessageType type) {
        NotificationGroup error = new NotificationGroup("notify", NotificationDisplayType.BALLOON, true);
        Notification notification = error.createNotification(content, type);
        Notifications.Bus.notify(notification);
    }
}
