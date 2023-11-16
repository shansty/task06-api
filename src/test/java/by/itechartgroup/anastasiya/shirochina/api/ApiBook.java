package by.itechartgroup.anastasiya.shirochina.api;

import by.itechartgroup.anastasiya.shirochina.pojos.Book;
import com.google.gson.Gson;
import com.microsoft.playwright.Response;


public class ApiBook {
    public static Book getBook(Response response) {
        String jsonResponse = response.text();
        Gson gson = new Gson();
        Book bookResponse = gson.fromJson(jsonResponse, Book.class);
        return bookResponse;
    }
}
