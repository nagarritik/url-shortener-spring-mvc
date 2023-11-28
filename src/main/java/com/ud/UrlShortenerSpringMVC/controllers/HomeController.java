package com.ud.UrlShortenerSpringMVC.controllers;

import com.ud.UrlShortenerSpringMVC.dao.UserDao;
import com.ud.UrlShortenerSpringMVC.model.ShortUrl;
import com.ud.UrlShortenerSpringMVC.service.ShortUrlService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@Controller
public class HomeController {
    private ShortUrlService shortUrlService;

    @Autowired
    public HomeController(ShortUrlService shortUrlService) {
        this.shortUrlService = shortUrlService;
    }

    @GetMapping("/")
    public String home(){
        return login();
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("/dashboard")
    public String dashboard(
            Model model,
            HttpSession httpSession
    ){
        UserDao userDao = (UserDao) httpSession.getAttribute("user");
        if (userDao!=null){
            List<ShortUrl> shortUrlList = shortUrlService.getShortUrlWithUserId(userDao.getId());
            model.addAttribute("shortUrlList",shortUrlList);
        }
        return "dashboard";
    }

    @GetMapping("/signup")
    public String signup(){
        return "signup";
    }

    @GetMapping("/logout")
    public RedirectView logout(
            HttpSession httpSession,
            RedirectView redirectView,
            HttpServletRequest httpServletRequest
    ){
        httpSession.invalidate();
        redirectView.setUrl(httpServletRequest.getContextPath()+"/");
        return redirectView;
    }

    @GetMapping("{shortCode}")
    public String demo(
            @PathVariable String shortCode,
            Model model
    ){
        ShortUrl shortUrl = shortUrlService.getShortUrlWithShortCode(shortCode);
        model.addAttribute("shortUrl",shortUrl);
        if (shortUrl!=null) {
            return "redirect";
        }
        return "error";
    }

    @GetMapping("/error")
    public String error(){
        return "error";
    }

    @GetMapping("/success")
    public String success(){
        return "success";
    }
}
