package com.polarbookshop.catalog_service.web;

import com.polarbookshop.catalog_service.domain.BookNotFoundException;
import com.polarbookshop.catalog_service.domain.BookService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(BookController.class)
public class BookControllerMvcTests {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private BookService bookService;

    @Test
    void whenGetBookNotExistingThenShouldReturn404() throws Exception {
        String isbn = "73737313940";
        when(bookService.viewBookDetails(isbn)).thenThrow(new BookNotFoundException(isbn));
        mockMvc.perform(get("/books/" + isbn))
                .andExpect(status().isNotFound());

//        given(bookService.viewBookDetails(isbn))
//                .willThrow(BookNotFoundException.class);
//        mockMvc.perform(get("/books/" + isbn))
//                .andExpect(status().isNotFound());
    }

}
