package by.itechartgroup.anastasiya.shirochina.dialogs;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Dialog extends BaseDialog {
    private final Locator dialogBody;
    private final Locator dialogTitle;

    public Dialog(Page page) {
        super(page);
        this.dialogBody = page.locator("//div[@class = 'modal-body']");
        this.dialogTitle = page.locator("//div[@id='example-modal-sizes-title-sm']");
    }

    public Locator getDialogBody() {
        return dialogBody;
    }

    public Locator getDialogTitle() {
        return dialogTitle;
    }
}

