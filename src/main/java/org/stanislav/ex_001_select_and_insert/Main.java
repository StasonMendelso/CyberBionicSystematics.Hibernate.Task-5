package org.stanislav.ex_001_select_and_insert;



import org.apache.log4j.Logger;
import org.stanislav.ex_001_select_and_insert.entity.Author;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(AuthorHelper.class.getName());

    public static void main(String[] args) {

        AuthorHelper authorHelper = new AuthorHelper();

/*
        Author[] authors = {
                new Author("Franz Kafka"),
                new Author("Charles Dickens"),
                new Author("Herman Melville")
        };

        // Case 1. Insert authors into DB
        for (Author author : authors) {
            authorHelper.addAuthor(author);
        }



        // Case 2.1 Get all authors
        List<Author> authorList = authorHelper.getAuthorList();

        for (Author author : authorList) {
            // LOG.debug(author.getId() + " " + author.getName());
            System.out.println(author);
            LOG.debug(author);
        }
        */


        // Case 2.2 Get a specific author by id
        Author author = authorHelper.getAuthorById(2);
        System.out.println(author);
        LOG.debug(author.getName());

    }

}
