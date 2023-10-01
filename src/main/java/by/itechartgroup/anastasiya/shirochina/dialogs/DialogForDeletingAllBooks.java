package by.itechartgroup.anastasiya.shirochina.dialogs;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DialogForDeletingAllBooks extends BaseDialog {
    private Locator dialogBody;
    private Locator dialogTitle;

    public DialogForDeletingAllBooks(Page page) {
        super(page);
        this.dialogBody = page.locator("//div[text() = 'Do you want to delete all books?']");
        this.dialogTitle = page.locator("//div[@id='example-modal-sizes-title-sm']");
    }

    public Locator getDialogBody() {
        return dialogBody;
    }

    public Locator getDialogTitle() {
        return dialogTitle;
    }
}
