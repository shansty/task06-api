package by.itechartgroup.anastasiya.shirochina.pages;

import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

public class BookPage extends BasePage {
    private Locator uiPageNumberLocator;
    private Locator addToCollectionButton;
    private Locator backToBookStoreButton;
    public BookPage(Page page) {
        super(page);
        this.uiPageNumberLocator = page.locator("//div[@id = 'pages-wrapper']//label[@id = 'userName-value']");
        this.addToCollectionButton = page.locator("//button[text() = 'Add To Your Collection']");
        this.backToBookStoreButton = page.locator("//button[text() = 'Back To Book Store']");
    }
    public Locator getQuantityOfPageUi() {
        return uiPageNumberLocator;
    }

    public Locator getAddToCollectionButton() {
        return addToCollectionButton;
    }

    public Locator getBackToBookStoreButton() {
        return backToBookStoreButton;
    }
}
