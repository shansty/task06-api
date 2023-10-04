package by.itechartgroup.anastasiya.shirochina.dialogs;

import com.microsoft.playwright.Page;

public class DialogPageHelper {
    private boolean flag;
    public String expectedDialogMessage;
    Page page;

    public DialogPageHelper(Page page) {
        this.page = page;
        this.flag = false;
        this.expectedDialogMessage = null;
    }

    public void waitForDialog() {
        while (flag) {
            page.waitForTimeout(1);
        }
    }

    public void release() {
        flag = false;
    }

    public void beforeDialog(String message) {
        flag = true;
        expectedDialogMessage = message;
    }
}
