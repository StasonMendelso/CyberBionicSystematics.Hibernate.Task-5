package org.stanislav.ex_002_insert_and_update.repository;

import org.hibernate.Session;

/**
 * @author Stanislav Hlova
 */
public interface SessionOperation<T> {
    T execute(Session session);
}
