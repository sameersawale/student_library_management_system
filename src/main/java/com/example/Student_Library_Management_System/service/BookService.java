package com.example.Student_Library_Management_System.service;

import com.example.Student_Library_Management_System.DTOs.BookRequestDto;
import com.example.Student_Library_Management_System.model.Author;
import com.example.Student_Library_Management_System.model.Book;
import com.example.Student_Library_Management_System.repository.AuthorRepository;
import com.example.Student_Library_Management_System.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {


    @Autowired
    AuthorRepository authorRepository;

    public String addBook(BookRequestDto bookRequestDto){
        //I want to get the Author Entity


        //I will be fetching author entity
        try {
            //i want to get authorEntity
            int authorId= bookRequestDto.getAuthorId();

            //new I will be fetching the authorEntity
            Author author=authorRepository.findById(authorId).get();

            //we have created this entity so that we can save it into db
            Book book=new Book();

            //basic attributes are being from Dto to entity layer
            book.setName(bookRequestDto.getName());
            book.setIssued(false);
            book.setAuthor(author);
            book.setGenre(bookRequestDto.getGenre());

            List<Book>currentWrittenBook=author.getBooksWritten();
            currentWrittenBook.add(book);

            authorRepository.save(author);

            return "book added successfully....";

        }
        catch (Exception e){
            return "Author not found...";
        }

    }
}
