package com.vorozco.books.repository;

import com.vorozco.books.model.Book;
import jakarta.enterprise.context.Dependent;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;

import java.util.List;

@Transactional
@Dependent
public class BookRepository {

    @PersistenceContext
    EntityManager em;

    //CRUD
    public void create(Book book) {
        em.persist(book);
    }

    public Book read(Long bookId) {
        return em.find(Book.class, bookId);
    }

    public void update(Book book) {
        em.merge(book);
    }

    public void delete(Long bookId) {
        Book book = em.find(Book.class, bookId);
        em.remove(book);
    }

    //List all
    public List<Book> listAll() {
        return em.createQuery("SELECT b FROM Book b", Book.class).getResultList();
    }
}
