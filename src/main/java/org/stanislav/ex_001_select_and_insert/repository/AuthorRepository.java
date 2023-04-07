package org.stanislav.ex_001_select_and_insert.repository;


import jakarta.persistence.Query;
import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Root;
import org.stanislav.ex_001_select_and_insert.entity.Author;

import java.util.List;


/**
 * Created by Asus on 01.11.2017.
 */
public class AuthorRepository extends Repository {
    public AuthorRepository() {
        super(HibernateUtil.getSessionFactory());
    }

    public Author addAuthor(Author author) {
        doInTransaction(session -> session.save(author));
        return author;
    }

    public List<Author> getAuthorList() {
        return doInTransaction(session -> {
            // объект-конструктор запросов для Criteria API
            CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();// не использовать session.createCriteria, т.к. deprecated
            CriteriaQuery criteriaQuery = criteriaBuilder.createQuery(Author.class);
            Root<Author> root = criteriaQuery.from(Author.class);// первостепенный, корневой entity (в sql запросе - from)
            criteriaQuery.select(root);// необязательный оператор, если просто нужно получить все значения
            //этап выполнения запроса
            Query query = session.createQuery(criteriaQuery);

            return (List<Author>) query.getResultList();
        });
    }

    public Author getAuthorById(long id) {
        return doInTransaction(session -> session.get(Author.class, id));
    }

    public void updateAuthorName(long id, String name) {
        doInTransaction(session -> {
            Author author = session.get(Author.class, id);
            author.setName(name);
            return null;
        });
    }

}
