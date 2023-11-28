package com.ud.UrlShortenerSpringMVC.repository;

import com.ud.UrlShortenerSpringMVC.model.ShortUrl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate5.HibernateTemplate;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public class ShortUrlRepository {
    private HibernateTemplate hibernateTemplate;

    @Autowired
    public ShortUrlRepository(HibernateTemplate hibernateTemplate) {
        this.hibernateTemplate = hibernateTemplate;
    }

    @Transactional
    public void save(ShortUrl shortUrl){
        hibernateTemplate.save(shortUrl);
    }

    public List<ShortUrl> getAllShortUrl(){
        return hibernateTemplate.loadAll(ShortUrl.class);
    }
}
