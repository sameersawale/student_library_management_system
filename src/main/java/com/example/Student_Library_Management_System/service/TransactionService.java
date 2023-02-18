package com.example.Student_Library_Management_System.service;

import com.example.Student_Library_Management_System.DTOs.IssueBookRequestDto;
import com.example.Student_Library_Management_System.Enums.CardStatus;
import com.example.Student_Library_Management_System.Enums.TransactionStatus;
import com.example.Student_Library_Management_System.model.Book;
import com.example.Student_Library_Management_System.model.Card;
import com.example.Student_Library_Management_System.model.Transactions;
import com.example.Student_Library_Management_System.repository.BookRepository;
import com.example.Student_Library_Management_System.repository.CardRepository;
import com.example.Student_Library_Management_System.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class TransactionService {

    @Autowired
    TransactionRepository transactionRepository;

    @Autowired
    BookRepository bookRepository;

    @Autowired
    CardRepository cardRepository;

    public String issueBook(IssueBookRequestDto issueBookRequestDto) throws Exception{

        int bookId= issueBookRequestDto.getBookId();

        int cardId= issueBookRequestDto.getCardId();

        //get the book entity and card entity
        //for set the transactions attributes

        Book book=bookRepository.findById(bookId).get();

        Card card=cardRepository.findById(cardId).get();

        //final goal is to make a transaction Entity set its attributes and save it.

        Transactions transactions=new Transactions();

        //set the attributes
        transactions.setBook(book);
        transactions.setCard(card);
        transactions.setTransactionId(UUID.randomUUID().toString());
        transactions.setIssueOperation(true);
        transactions.setTransactionStatus(TransactionStatus.PENDING);

        //check for validations
        if(book==null || book.isIssued()==true){
            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("book is not available");
        }

        if(card==null || (card.getCardStatus()!=CardStatus.ACTIVATED)){

            transactions.setTransactionStatus(TransactionStatus.FAILED);
            transactionRepository.save(transactions);
            throw new Exception("Card is not valid");
        }

        //we reach success case

        transactions.setTransactionStatus(TransactionStatus.SUCCESS);

        //set the attributes of book
        book.setIssued(true);

        //between the book and transaction
        List<Transactions> listOfTransactionForBook=book.getListOfTransactions();
        listOfTransactionForBook.add(transactions);
        book.setListOfTransactions(listOfTransactionForBook);

        //I need to make changes in card
        List<Book>issuedBookForCard=card.getBookIssued();
        issuedBookForCard.add(book);
        card.setBookIssued(issuedBookForCard);

        //card and transaction bidirectional
        List<Transactions> listOfTransactionForCard=card.getTrasactionsLists();
        listOfTransactionForCard.add(transactions);
        card.setTrasactionsLists(listOfTransactionForCard);

        //save the parent
        cardRepository.save(card);

        //automatically by cascading book and transaction will be saved.
        //saving the parent

        return "Book issued successfully....";


    }
}
