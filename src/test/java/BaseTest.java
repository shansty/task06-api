import by.itechartgroup.anastasiya.shirochina.api.ApiBook;
import by.itechartgroup.anastasiya.shirochina.api.ApiLogin;
import by.itechartgroup.anastasiya.shirochina.dialogs.Dialog;
import by.itechartgroup.anastasiya.shirochina.pages.*;
import com.microsoft.playwright.*;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.List;

public class BaseTest {
    static Playwright playwright;
    static Browser browser;
    static Page page;
    static BrowserContext context;
    static LoginPage login;
    static ProfilePage profile;
    BookStorePage bookStore;
    BookPage book;
    ApiLogin apiLogin;
    static File file;
    ApiBook apiBook;
    static Dialog dialog;


    @BeforeAll
    public static void launchBrowser() throws IOException {
        playwright = Playwright.create();
        browser = playwright.chromium().launch(new BrowserType.LaunchOptions().setHeadless(false));
        context = browser.newContext();
        page = context.newPage();
        login = new LoginPage(page);
        login.navigate().inputUserName().inputUserPassword().submitButton();
        file = new File("playwright/.auth/authentication.json");
        page.waitForURL("https://demoqa.com/profile");
        context.storageState(new BrowserContext.StorageStateOptions().setPath(Paths.get(file.getPath())));
        profile = new ProfilePage(page);
        closeAllBook();
        page.close();
        context.close();
    }

    @AfterAll
    public static void closeBrowser() {
        playwright.close();
        if (file.exists()) {
            file.delete();
        }
    }

    @BeforeEach
    void createContextAndPage() throws Exception {
        if (file.exists()) {
            context = browser.newContext(new Browser.NewContextOptions().setStorageStatePath(Paths.get(file.getPath())));
            page = context.newPage();
        } else {
            throw new Exception("Файл с данныйми для аутентификации не найден " + file.getName());
        }
        profile = new ProfilePage(page);
        bookStore = new BookStorePage(page);
        book = new BookPage(page);
        apiLogin = new ApiLogin(playwright, profile);
        apiBook = new ApiBook();
    }

    @AfterEach
    void closeContext() {
        context.close();
    }
    private static void closeAllBook() {
        dialog = new Dialog(page);
        List<Locator> booksForDelete = profile.getDeletedBook().all();
        for (int i = 0; i <booksForDelete.size(); i++) {
            profile.getDeletedBook().nth(0).click();
            dialog.getOkButton().click();
        }
    }
}
