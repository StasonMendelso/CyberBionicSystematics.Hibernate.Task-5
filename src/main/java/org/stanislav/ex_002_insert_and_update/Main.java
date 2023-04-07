package org.stanislav.ex_002_insert_and_update;

import org.stanislav.ex_002_insert_and_update.entity.Author;
import org.stanislav.ex_002_insert_and_update.repository.AuthorRepository;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Asus on 04.11.2017.
 */
public class Main {

    public static void main(String[] args) {
        AuthorRepository authorRepository = new AuthorRepository();
        System.out.println("Автори з БД перед вставкою 200 нових авторів: ");
        System.out.println(authorRepository.getAuthorList());
        List<Author> authorList = new ArrayList<>();
        for (int i = 1; i <= 200; i++) {
            authorList.add(new Author("author " + i));
        }
        authorRepository.saveAuthors(authorList);

        System.out.println("Автори з БД після вставки 200 нових авторів: ");
        System.out.println(authorRepository.getAuthorList());
    }

}
