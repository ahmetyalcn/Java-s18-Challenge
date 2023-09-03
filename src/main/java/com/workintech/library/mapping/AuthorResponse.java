package com.workintech.library.mapping;

import com.workintech.library.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AuthorResponse {
    private int authorId;
    private String authorFirstName;
    private String authorLastName;
    private List<BookResponse> books;
}
