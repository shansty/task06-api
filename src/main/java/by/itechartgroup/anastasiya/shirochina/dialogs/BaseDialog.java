package by.itechartgroup.anastasiya.shirochina.dialogs;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BaseDialog {
    Page page;
    private final Locator okButton;
    private final Locator cancelButton;
    public BaseDialog(Page page) {
        this.page = page;
        this.okButton = page.locator("//button[@id='closeSmallModal-ok']");
        this.cancelButton = page.locator("//button[@id='closeSmallModal-cancel']");
    }

    public Locator getOkButton() {
        return okButton;
    }

    public Locator getCancelButton() {
        return cancelButton;
    }
}
