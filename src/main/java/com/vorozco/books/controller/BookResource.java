package com.vorozco.books.controller;

import com.vorozco.books.model.Book;
import com.vorozco.books.repository.BookRepository;
import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;

import java.util.List;

@Path("/books")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class BookResource {

    BookRepository bookRepository;

    @Inject
    public BookResource(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    //List all
    @GET
    public List<Book> listAll() {
        return bookRepository.listAll();
    }

    //Get by id
    @GET
    @Path("{id}")
    public Book getBook(@PathParam("id") Long id) {
        return bookRepository.read(id);
    }

    //Create
    @POST
    public Book createBook(Book book) {
        bookRepository.create(book);
        return book;
    }

    //Update
    @PUT
    @Path("{id}")
    public Book updateBook(@PathParam("id") Long id, Book book) {
        bookRepository.update(book);
        return book;
    }

    //Delete
    @DELETE
    @Path("{id}")
    public void deleteBook(@PathParam("id") Long id) {
        bookRepository.delete(id);
    }

}
