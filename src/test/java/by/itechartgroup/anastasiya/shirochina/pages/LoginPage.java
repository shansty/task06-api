package by.itechartgroup.anastasiya.shirochina.pages;

import by.itechartgroup.anastasiya.shirochina.utils.Reader;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Page;

import java.io.IOException;

public class LoginPage extends BasePage{
    private final Locator userNameInput;
    private final Locator passwordInput;
    private final Locator submitButton;

    public LoginPage(Page page) {
        super(page);
        this.userNameInput = page.getByPlaceholder("UserName");
        this.passwordInput = page.getByPlaceholder("Password");
        this.submitButton = page.locator("//button[@id='login']");
    }
    public LoginPage navigate() {
        page.navigate("https://demoqa.com/login");
        return this;
    }
    public LoginPage inputUserName() throws IOException {
        userNameInput.fill(Reader.readPropertyUserName());
        return this;
    }
    public LoginPage inputUserPassword() throws IOException {
        passwordInput.fill(Reader.readPropertyPassword());
        return this;
    }
    public LoginPage submitButton() {
        submitButton.click();
        return this;
    }
}