package com.alternate.sample.repositories;

import com.alternate.sample.entities.UserAccount;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import java.util.Collection;
import java.util.List;

@Repository
@Transactional
public class UserRepositoryImpl implements UserRepository {

    private SessionFactory sessionFactory;

    @Autowired
    public UserRepositoryImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public UserAccount findById(Long id) {
        return sessionFactory.getCurrentSession().get(UserAccount.class, id);
    }

    @Override
    public void save(UserAccount userAccount) {
        sessionFactory.getCurrentSession().save(userAccount);
    }

    @Override
    public Collection<UserAccount> findAll() {
        Session session = sessionFactory.getCurrentSession();
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserAccount> criteria = builder.createQuery(UserAccount.class);
        criteria.from(UserAccount.class);
        return session.createQuery(criteria).getResultList();
    }
}
