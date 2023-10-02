package by.itechartgroup.anastasiya.shirochina.dialogs;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class Dialog extends BaseDialog {
    private final Locator dialogBody;
    private final Locator dialogTitle;
    private final Locator dialogBodyForAllBooks;

    public Dialog(Page page) {
        super(page);
        this.dialogBody = page.locator("//div[@class = 'modal-body' and contains(text(), 'Do you want to delete this book?')]");
        this.dialogTitle = page.locator("//div[@id='example-modal-sizes-title-sm']");
        this.dialogBodyForAllBooks = page.locator("//div[text() = 'Do you want to delete all books?']");
    }

    public Locator getDialogBody() {
        return dialogBody;
    }

    public Locator getDialogTitle() {
        return dialogTitle;
    }

    public Locator getDialogBodyForAllBooks() {
        return dialogBodyForAllBooks;
    }
}
