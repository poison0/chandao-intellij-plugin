package chandao.action;

import chandao.data.LogInData;
import chandao.message.Notifier;
import chandao.query.Login;
import com.intellij.ide.util.PropertiesComponent;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.DialogWrapper;
import com.intellij.openapi.ui.MessageType;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.awt.*;

public class LgDialog extends DialogWrapper {

    JTextField userField;
    JPasswordField passwordField;
    Project project;

    public LgDialog(Project project) {
        super(true);
        this.project = project;
        init();
        setTitle("登录");
    }

    public void showThisDialog() {
        show();
    }

    @Override
    protected @Nullable JComponent createCenterPanel() {
        JPanel jPanel = new JPanel(new BorderLayout());
        JPanel firstPanel = new JPanel(new BorderLayout());
        JPanel secondPanel = new JPanel(new BorderLayout());
        passwordField = new JPasswordField();
        userField = new JTextField();
        firstPanel.add(new JLabel("用户 "), BorderLayout.WEST);
        firstPanel.add(userField, BorderLayout.CENTER);
        secondPanel.add(new JLabel("密码 "), BorderLayout.WEST);
        secondPanel.add(passwordField, BorderLayout.CENTER);
        jPanel.add(firstPanel,BorderLayout.NORTH);
        jPanel.add(secondPanel,BorderLayout.CENTER);
        if (LogInData.USER_NAME != null) {
            userField.setText(LogInData.USER_NAME);
        }
        if (LogInData.PASS_WORD != null) {
            passwordField.setText(LogInData.PASS_WORD);
        }
        return jPanel;
    }

    @Override
    protected JComponent createSouthPanel(){
        JPanel jPanel = new JPanel(new BorderLayout());
        JButton button = new JButton("登录");
        button.addActionListener(e->{
            String password = String.valueOf(passwordField.getPassword());
            String user = userField.getText();
            LogInData.PASS_WORD = password;
            LogInData.USER_NAME = user;
            PropertiesComponent instance = PropertiesComponent.getInstance();
            instance.setValue("chandao_pass_word",password);
            instance.setValue("chandao_user_name",user);
            LgDialog.this.dispose();
            new Login().login(project);

            Notifier.notify("登录成功", MessageType.INFO);
        });
        jPanel.add(button, BorderLayout.EAST);
        return jPanel;
    }
}
