package by.itechartgroup.anastasiya.shirochina.dialogs;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class DialogForDeletingOneBook extends BaseDialog {
    private Locator dialogBody;
    private Locator dialogTitle;

    public DialogForDeletingOneBook(Page page) {
        super(page);
        this.dialogBody = page.locator("//div[@class = 'modal-body' and contains(text(), 'Do you want to delete this book?')]");
        this.dialogTitle = page.locator("//div[@id='example-modal-sizes-title-sm']");
    }

    public Locator getDialogBody() {
        return dialogBody;
    }

    public Locator getDialogTitle() {
        return dialogTitle;
    }
}
