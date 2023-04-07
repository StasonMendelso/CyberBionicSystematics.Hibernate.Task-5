package org.stanislav.ex_002_insert_and_update;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.stanislav.ex_002_insert_and_update.entity.Author;

/**
 * Created by Asus on 04.11.2017.
 */
public class Main {

    private static final Logger LOG = Logger.getLogger(AuthorHelper.class.getName());

    public static void main(String[] args) {
        AuthorHelper authorHelper = new AuthorHelper();

        // Case 1. Add one more author
        // addOneMoreAuthor(authorHelper);

        // Case 2. Apply 1NF to 'name' db column data
/*
        List<Author> allAuthors = authorHelper.getAuthorList();
        for(Author a : allAuthors) {
            String[] namePieces = a.getName().split("\\s");
            if(namePieces.length == 2) {
                String firstName = namePieces[0];
                String lastName = namePieces[1];
                a.setName(firstName);
                a.setLastName(lastName);
                authorHelper.updateAuthor(a);
            }
        }
*/

        // Case 3. @DynamicUpdate annotation
        // dynamicUpdateExample();

        // Case 4. session.flush() example
        sessionFlushExample();

    }

    private static void addOneMoreAuthor(AuthorHelper authorHelper) {
        Author author = new Author("Clive", "Lewis");
        authorHelper.addAuthor(author);
    }

    private static void dynamicUpdateExample() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        Author author = session.get(Author.class, 1L);

        // Case 1. Update entity without @DynamicUpdate annotation
        // only 1 field is changed, but Hibernate tries to update all fields in DB:
        // Hibernate: update Author set last_name=?, name=? where id=?
        author.setName("Franz4");
        author.setLastName("Kafka4");
        session.update(author);

        // add @DynamicUpdate annotation and try again
        // RESULT: only the modified field is sent to the DB
        // Hibernate: update Author set name=? where id=?

        session.getTransaction().commit();
        session.close();
    }

    private static void sessionFlushExample() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        // 200 тыс. - 35 сек
        // 2 млн. - 5 мин 31 сек (331 сек)
        for (int i = 0; i < 2_000_000; i++) {
            session.save(new Author("name" + i));

            if (i % 50 == 0) {
                session.flush();
            }
        }

        session.getTransaction().commit();
        session.close();

        // alter table author auto_increment = 6;
    }


}
