package by.itechartgroup.anastasiya.shirochina.api;

import by.itechartgroup.anastasiya.shirochina.pojos.Book;
import by.itechartgroup.anastasiya.shirochina.pojos.Root;
import com.google.gson.Gson;
import com.microsoft.playwright.Response;

import java.util.List;

public class ApiBookStore {

    public static List<Book> getBooksArray(Response response) {
        String jsonResponse = response.text();
        Gson gson = new Gson();
        Root booksResponse = gson.fromJson(jsonResponse, Root.class);
       return booksResponse.getBooks();
    }

    //JSONArray jsonObject = new JSONObject(getBooksResponse.text()).getJSONArray("books");
}
