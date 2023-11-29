package com.ud.UrlShortenerSpringMVC.controllers;

import com.ud.UrlShortenerSpringMVC.Constants;
import com.ud.UrlShortenerSpringMVC.dao.UserDao;
import com.ud.UrlShortenerSpringMVC.model.ShortUrl;
import com.ud.UrlShortenerSpringMVC.model.User;
import com.ud.UrlShortenerSpringMVC.service.ShortUrlService;
import com.ud.UrlShortenerSpringMVC.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.view.RedirectView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
public class FormController {
    private UserService userService;
    private ShortUrlService shortUrlService;

    @Autowired
    public FormController(UserService userService, ShortUrlService shortUrlService) {
        this.userService = userService;
        this.shortUrlService = shortUrlService;
    }

    @PostMapping("/signup_handler")
    public RedirectView signupHandler(
            @ModelAttribute User user,
            HttpServletRequest httpServletRequest,
            RedirectView redirectView,
            HttpSession httpSession
    ){
        String setUrl = null;
        if (userService.userWithUsername(user)){
            httpSession.setAttribute("type","Error");
            httpSession.setAttribute("message","Username exist");
            setUrl =  "/error";
        }
        else if (userService.userWithEmail(user)){
            httpSession.setAttribute("type","Error");
            httpSession.setAttribute("message","Email exist");
            setUrl =  "/error";
        }
        else {
            userService.save(user);
            httpSession.setAttribute("type","Success");
            httpSession.setAttribute("message","Account created");
            setUrl =  "/success";
        }

        redirectView.setUrl(httpServletRequest.getContextPath()+setUrl);
        return redirectView;
    }

    @PostMapping("/login_handler")
    public RedirectView loginHandler(
            @ModelAttribute User user,
            HttpServletRequest httpServletRequest,
            HttpSession httpSession,
            RedirectView redirectView
    ){
        String setUrl = null;
        String type = null,message = null;

        if (!userService.userWithEmail(user)){
            type = "error";
            message = "User does not exist";
            setUrl = "/error";
        }
        else if (userService.authenticateUser(user)){
            User user1 = userService.getUserWithEmail(user.getEmail());
            httpSession.setAttribute("user",new UserDao(user1));

            type = "success";
            message = "Login success";
            setUrl = "/success";
        }
        else {
            type = "error";
            message = "Incorrect password";
            setUrl = "/error";
        }

        httpSession.setAttribute("type",type);
        httpSession.setAttribute("message",message);

        redirectView.setUrl(httpServletRequest.getContextPath()+setUrl);
        return redirectView;
    }

    @PostMapping("/url_short_handler")
    public RedirectView urlShortHandler(
            @ModelAttribute ShortUrl shortUrl,
            HttpSession httpSession,
            RedirectView redirectView,
            HttpServletRequest httpServletRequest
            ){
        UserDao sessionUser = (UserDao) httpSession.getAttribute("user");

        if (shortUrlService.shortUrlWithUserIdAndLongUrl(sessionUser.getId(),shortUrl.getLongUrl())){
            httpSession.setAttribute("type","Error");
            httpSession.setAttribute("message","You already shorted this url in past");
            redirectView.setUrl(httpServletRequest.getContextPath()+"/error");
        }else{
            String shortCode = Constants.shortCodeGenerator();
            while (shortUrlService.shortUrlWithShortCode(shortCode)){
                shortCode = Constants.shortCodeGenerator();
            }
            shortUrl.setShortCode(shortCode);

            shortUrl.setUserUserId(sessionUser.getId());
            shortUrlService.save(shortUrl);
            redirectView.setUrl(httpServletRequest.getContextPath()+"/dashboard");
        }

        return redirectView;
    }
}
