package by.itechartgroup.anastasiya.shirochina.pages;

import by.itechartgroup.anastasiya.shirochina.utils.Cookies;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;
import com.microsoft.playwright.options.Cookie;

import java.util.List;

public class ProfilePage extends BasePage {
    private List<Cookie> cookies;
    private String cookieNameUserId = "userID";
    private String cookieNameToken = "token";
    private String cookieNameUserName = "userName";
    private String cookieNameExpires = "expires";
    private Locator profileButton;
    private Locator booksTitle;
    private Locator booksAuthor;
    private Locator booksPublisher;
    private Locator deletedBook;
    private Locator deleteAllBooksButton;
    private Locator emptyCollection;

    public ProfilePage(Page page) {
        super(page);
        this.profileButton = page.locator("//span[text()='Profile']");
        this.booksTitle = page.locator("//a[(contains(@href, '/profile?book='))]");
        this.booksAuthor = page.locator("//a[(contains(@href, '/profile?book='))]/../../../..//div[3]");
        this.booksPublisher = page.locator("//a[(contains(@href, '/profile?book='))]/../../../..//div[4]");
        this.deletedBook = page.locator("//span[@id='delete-record-undefined']");
        this.deleteAllBooksButton = page.locator("//div[@class='text-right button di']/button[@id='submit' and text()='Delete All Books']");
        this.emptyCollection = page.locator("//div[@class='rt-noData']");
    }

    public ProfilePage waitForProfileUrlAndCookie() {
        page.waitForURL("**/profile");
        cookies = page.context().cookies();
        return this;
    }

    public String getCookieUserID() {
        return Cookies.getCookieByName(cookieNameUserId, cookies);
    }

    public String getCookieToken() {
        return Cookies.getCookieByName(cookieNameToken, cookies);
    }

    public String getCookieUserName() {
        return Cookies.getCookieByName(cookieNameUserName, cookies);
    }

    public String getCookieExpires() {
        return Cookies.getCookieByName(cookieNameExpires, cookies);
    }

    public Locator getProfileButton() {
        return profileButton;
    }
    public Locator getBooksTitle() {
        return booksTitle;
    }

    public Locator getBooksAuthor() {
        return booksAuthor;
    }

    public Locator getBooksPublisher() {
        return booksPublisher;
    }

    public Locator getDeletedBook() {
        return deletedBook;
    }
    public Locator getDeletedBooksTitle(String title) {
        return page.locator("//a[text() = " + "\"" + title +"\"" +"]");
    }

    public Locator getDeleteAllBooksButton() {
        return deleteAllBooksButton;
    }

    public Locator getEmptyCollection() {
        return emptyCollection;
    }
}
