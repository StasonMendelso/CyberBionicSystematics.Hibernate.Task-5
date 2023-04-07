package org.stanislav.ex_001_select_and_insert;


import org.apache.log4j.Logger;
import org.stanislav.ex_001_select_and_insert.entity.Book;
import org.stanislav.ex_001_select_and_insert.repository.AuthorRepository;
import org.stanislav.ex_001_select_and_insert.repository.BookRepository;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(AuthorRepository.class.getName());

    public static void main(String[] args) {

        AuthorRepository authorRepository = new AuthorRepository();
        System.out.printf("Автор з id=2 до оновлення імені: %s\n", authorRepository.getAuthorById(2));
        authorRepository.updateAuthorName(2, "Rick");
        LOG.info("Оновлюємо ім'я автора з id=2 на ім'я Rick.");
        System.out.printf("Автор з id=2 після оновлення імені: %s\n", authorRepository.getAuthorById(2));

        BookRepository bookRepository = new BookRepository();
        bookRepository.create(new Book("Old book"));
        System.out.printf("Книга з id=1 до оновлення імені: %s\n", bookRepository.read(1).get());
        bookRepository.updateBookName(1, "Strange diary");
        LOG.info("Оновлюємо ім'я книги з id=1 на ім'я Strange diary.");
        System.out.printf("Книга з id=1 після оновлення імені: %s\n", bookRepository.read(1).get());

        LOG.info("Додаємо список книг з автором до БД.");
        bookRepository.create(List.of("Book1", "Book2", "Book3", "Book4", "Book5"), "Test Author Name");
        System.out.println("Книги з БД наступні:");
        System.out.println(bookRepository.readAll());
        System.out.println("Автори з БД наступні:");
        System.out.println(authorRepository.getAuthorList());
    }

}
