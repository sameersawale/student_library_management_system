package com.example.Student_Library_Management_System.service;


import com.example.Student_Library_Management_System.DTOs.AuthorEntryDto;
import com.example.Student_Library_Management_System.DTOs.AuthorResponseDto;
import com.example.Student_Library_Management_System.DTOs.BookResponseDto;
import com.example.Student_Library_Management_System.model.Author;
import com.example.Student_Library_Management_System.model.Book;
import com.example.Student_Library_Management_System.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AuthorService {

    @Autowired
    AuthorRepository authorRepository;

    public String createAuthor(AuthorEntryDto authorEntryDto){

        //Important: in the params the object is of type DTO
        //but the repository interact only with entity

        //convert authorEntryDto to Author

        Author author=new Author();

        author.setName(authorEntryDto.getName());
        author.setAge(authorEntryDto.getAge());
        author.setCountry(authorEntryDto.getCountry());
        author.setRating(authorEntryDto.getRating());

        authorRepository.save(author);
        return "Author added successfully....";
    }

    public AuthorResponseDto getAuthor(Integer authorId){
        Author author=authorRepository.findById(authorId).get();

        AuthorResponseDto authorResponseDto=new AuthorResponseDto();

        //Set its attributes.
        //List<Book>-->List<BookResponseDto>

        List<Book> bookList=author.getBooksWritten(); //taken from this list and put in below list

        List<BookResponseDto> bookWrittenDto=new ArrayList<>();

        for(Book b:bookList){
            BookResponseDto bookResponseDto=new BookResponseDto();
            bookResponseDto.setGenre(b.getGenre());
            bookResponseDto.setName(b.getName());

            bookWrittenDto.add(bookResponseDto);
        }

        authorResponseDto.setBooksWritten(bookWrittenDto);
        authorResponseDto.setName(author.getName());
        authorResponseDto.setAge(author.getAge());
        authorResponseDto.setRating(author.getRating());


        return authorResponseDto;

    }
}
