package org.stanislav.ex_001_select_and_insert.repository;

import org.hibernate.Session;

/**
 * @author Stanislav Hlova
 */
public interface SessionOperation<T> {
    T execute(Session session);
}
