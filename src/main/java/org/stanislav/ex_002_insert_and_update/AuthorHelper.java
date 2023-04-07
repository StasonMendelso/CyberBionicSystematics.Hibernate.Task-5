package org.stanislav.ex_002_insert_and_update;


import org.hibernate.Session;
import org.hibernate.SessionFactory;

import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.stanislav.ex_002_insert_and_update.entity.Author;

import java.util.List;

/**
 * Created by Asus on 01.11.2017.
 */
public class AuthorHelper {

    private final SessionFactory sessionFactory;

    public AuthorHelper() {
        sessionFactory = HibernateUtil.getSessionFactory();
    }


    public Author addAuthor(Author author) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(author); // сгенерит ID и вставит в объект
        session.getTransaction().commit();
        session.close();

        return author;
    }

    public List<Author> getAuthorList() {
        // открыть сессию - для манипуляции с персист. объектами
        Session session = sessionFactory.openSession();


        // этап подготовки запроса

        // объект-конструктор запросов для Criteria API
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated

        CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Author.class);

        Root<Author> root = criteriaQuery.from(Author.class);// первостепенный, корневой entity (в sql запросе - from)

        criteriaQuery.select(root);// необязательный оператор, если просто нужно получить все значения

        //этап выполнения запроса
        Query query = session.createQuery(criteriaQuery);

        List<Author> authorList = query.getResultList();

        session.close();

        return authorList;
    }

    public Author getAuthorById(long id) {
        Session session = sessionFactory.openSession();
        Author author = session.get(Author.class, id); // получение объекта по id
        session.close();
        return author;
    }

    public Author updateAuthor(Author author) {

        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.saveOrUpdate(author);
        session.getTransaction().commit();
        session.close();

        return author;
    }
}