package com.ud.UrlShortenerSpringMVC.service;

import com.ud.UrlShortenerSpringMVC.model.ShortUrl;
import com.ud.UrlShortenerSpringMVC.repository.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class ShortUrlService {
    private ShortUrlRepository shortUrlRepository;

    @Autowired
    public ShortUrlService(ShortUrlRepository shortUrlRepository) {
        this.shortUrlRepository = shortUrlRepository;
    }

    public void save(ShortUrl shortUrl) {
        shortUrlRepository.save(shortUrl);
    }

    public List<ShortUrl> getAllShortUrl(){
        return shortUrlRepository.getAllShortUrl();
    }


    public ShortUrl getShortUrlWithShortCode(String shortCode) {
        ShortUrl shortUrl = null;

        List<ShortUrl> shortUrlList = getAllShortUrl();

        for (ShortUrl item:shortUrlList){
            if (Objects.equals(item.getShortCode(), shortCode)){
                shortUrl=item;
                break;
            }
        }

        return shortUrl;
    }

    public List<ShortUrl> getShortUrlWithUserId(long userId) {
        List<ShortUrl> shortUrlList = getAllShortUrl();
        return shortUrlList.stream().filter(e->e.getUserUserId()==userId).toList();
    }

    public boolean shortUrlWithShortCode(String shortCode){
        return !getAllShortUrl().stream().filter(e-> Objects.equals(e.getShortCode(), shortCode)).toList().isEmpty();
    }

    public boolean shortUrlWithUserIdAndLongUrl(long userId, String longUrl){
        return !getAllShortUrl().stream().filter(e->e.getUserUserId()==userId).filter(e-> Objects.equals(e.getLongUrl(), longUrl)).toList().isEmpty();
    }
}
