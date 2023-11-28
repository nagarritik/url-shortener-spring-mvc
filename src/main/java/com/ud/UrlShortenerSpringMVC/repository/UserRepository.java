package com.ud.UrlShortenerSpringMVC.repository;

import com.ud.UrlShortenerSpringMVC.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserRepository {
    private HibernateTemplate hibernateTemplate;

    @Autowired
    public UserRepository(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    public List<User> getAllUser(){
        return hibernateTemplate.loadAll(User.class);
    }

    @Transactional
    public void save(User user) {
        hibernateTemplate.save(user);
    }
}
