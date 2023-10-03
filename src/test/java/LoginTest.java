import by.itechartgroup.anastasiya.shirochina.DialogPageHelper;
import by.itechartgroup.anastasiya.shirochina.api.ApiBookStore;
import by.itechartgroup.anastasiya.shirochina.dialogs.Dialog;
import by.itechartgroup.anastasiya.shirochina.utils.Randomizer;
import by.itechartgroup.anastasiya.shirochina.utils.Reader;
import by.itechartgroup.anastasiya.shirochina.utils.Route;
import by.itechartgroup.anastasiya.shirochina.utils.Screenshot;
import com.microsoft.playwright.Locator;
import com.microsoft.playwright.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import by.itechartgroup.anastasiya.shirochina.pojos.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

import static com.microsoft.playwright.assertions.PlaywrightAssertions.assertThat;

public class LoginTest extends BaseTest {
    Semaphore semaphore = new Semaphore(0);

    @Test
    public void loginFormTest() throws IOException {
        page.navigate("https://demoqa.com/profile");
        profile.waitForProfileUrlAndCookie();
        Assertions.assertNotNull(profile.getCookieUserID());
        Assertions.assertNotNull(profile.getCookieToken());
        Assertions.assertNotNull(profile.getCookieUserName());
        Assertions.assertNotNull(profile.getCookieExpires());
        Route.abortAllPicture(page, "**/*.{png,jpg,jpeg}");
        Response getBooksResponse = page.waitForResponse(bookStore.booksUrl, () -> bookStore.getBookStoreButton().click());
        List<Book> books = ApiBookStore.getBooksArray(getBooksResponse);
        Screenshot.getScreenshot(page, "screenshots/screenshot.png");
        Assertions.assertEquals(200, getBooksResponse.status());
        assertThat(bookStore.getBooksArrayLocator()).hasCount(books.size());
        String quantityOfPageApi = String.valueOf(Randomizer.randomNumber(100, 1000));
        Route.modifiedResponseWithDifferentBody(page, "pages", quantityOfPageApi, "https://demoqa.com/BookStore/v1/Book?ISBN=*");
        bookStore.clickBookByNumber(Randomizer.randomNumber(0, bookStore.getBooksArrayLocator().all().size() - 1));
        assertThat(book.getQuantityOfPageUi()).containsText((quantityOfPageApi));
        assertThat(apiLogin.sendRequest()).isOK();
        Assertions.assertEquals(new ArrayList<>(), apiLogin.getTextFromResponse().getBooksFromResponse());
        Assertions.assertEquals(Reader.readPropertyUserName(), apiLogin.getTextFromResponse().getUserNameFromResponse());
    }

    @Test
    public void bookStoreTest() {
        page.navigate("https://demoqa.com/books");
        int randomNumberOfBooks = Randomizer.randomNumber(2, 5);

        page.onDialog(dialog -> {
            Assertions.assertEquals(dialogPageHelper.expectedDialogMessage, dialog.message());
            dialogPageHelper.expectedDialogMessage = null;
            dialog.accept();
            dialogPageHelper.release();
        });

        List<Book> books = new LinkedList<>();
        for (int i = 0; i < randomNumberOfBooks; i++) {
            Response response = page.waitForResponse("https://demoqa.com/BookStore/v1/Book?ISBN=*", () ->
                    bookStore.getBooksArrayLocator().nth(Randomizer.randomNumber(0, bookStore.getBooksArrayLocator().all().size()-1)).click());
            Book bookFromResponse = apiBook.getBook(response);
            if (!books.contains(bookFromResponse)) {
                books.add(bookFromResponse);
                dialogPageHelper.beforeDialog("Book added to your collection.");
                book.getAddToCollectionButton().click();
                dialogPageHelper.waitForDialog();
            } else if (books.contains(bookFromResponse)) {
                i--;
            }
            book.getBackToBookStoreButton().click();
        }
        profile.getProfileButton().click();
        assertThat(profile.getBooksTitle()).hasCount(randomNumberOfBooks);
        for (int i = 0; i < books.size(); i++) {
            assertThat(profile.getBooksTitle().nth(i)).containsText(books.get(i).getTitle());
            assertThat(profile.getBooksAuthor().nth(i)).containsText(books.get(i).getAuthor());
            assertThat(profile.getBooksPublisher().nth(i)).containsText(books.get(i).getPublisher());
        }
        Locator deletedBook = profile.getDeletedBook().nth(1);
        String deletedBookTitle = profile.getBooksTitle().nth(1).textContent();
        deletedBook.click();
        dialog = new Dialog(page);
        assertThat(dialog.getDialogBody()).containsText("Do you want to delete this book?");
        assertThat(dialog.getDialogTitle()).containsText("Delete Book");
        assertThat(dialog.getOkButton()).isVisible();
        assertThat(dialog.getCancelButton()).isVisible();

        dialogPageHelper.beforeDialog("Book deleted.");
        dialog.getOkButton().click();
        dialogPageHelper.waitForDialog();

        assertThat(profile.getDeletedBooksTitle(deletedBookTitle)).hasCount(0);
        profile.getDeleteAllBooksButton().click();
        assertThat(dialog.getDialogBody()).containsText("Do you want to delete all books?");
        assertThat(dialog.getDialogTitle()).containsText("Delete All Books");
        assertThat(dialog.getCancelButton()).isVisible();
        assertThat(dialog.getOkButton()).isVisible();

        dialogPageHelper.beforeDialog("All Books deleted.");
        dialog.getOkButton().click();
        dialogPageHelper.waitForDialog();

        assertThat(profile.getEmptyCollection()).containsText("No rows found");
        assertThat(profile.getBooksTitle()).hasCount(0);
    }
}
