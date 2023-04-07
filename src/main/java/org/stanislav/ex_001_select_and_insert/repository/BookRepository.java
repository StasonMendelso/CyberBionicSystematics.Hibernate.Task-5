package org.stanislav.ex_001_select_and_insert.repository;

import org.stanislav.ex_001_select_and_insert.entity.Author;
import org.stanislav.ex_001_select_and_insert.entity.Book;

import java.util.List;
import java.util.Optional;

/**
 * @author Stanislav Hlova
 */
public class BookRepository extends Repository {

    public BookRepository() {
        super(HibernateUtil.getSessionFactory());
    }

    public List<Book> readAll() {
        return doInTransaction(session -> session.createQuery("FROM Book", Book.class).getResultList());
    }

    public void create(Book book) {
        doInTransaction(session -> session.save(book));
    }

    public Optional<Book> read(int id) {
        return doInTransaction(session -> Optional.ofNullable(session.get(Book.class, id)));
    }

    public void updateBookName(long id, String name) {
        doInTransaction(session -> {
            Book book = session.get(Book.class, id);
            if (book != null) {
                book.setName(name);
            }
            return null;
        });
    }

    public void create(List<String> bookNames, String authorName) {
        doInTransaction(session -> {
            Author author = new Author(authorName);
            author = session.merge(author);
            for (int i = 0; i < bookNames.size(); i++) {
                session.save(new Book(bookNames.get(i), author.getId()));
                if (i % 10 == 0) {
                    session.flush();
                }
            }
            return null;
        });
    }

}
