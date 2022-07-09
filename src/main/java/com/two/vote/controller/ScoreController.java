package com.two.vote.controller;

import com.two.vote.entity.Score;
import com.two.vote.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Controller
public class ScoreController {

    @Autowired
    private ScoreService scoreService;

    @RequestMapping("setScore")
    public String setScore(String fraction,String articleid){
        System.out.println(fraction+"==="+articleid);
//        BigDecimal bigDecimal = BigDecimal.valueOf(score);
        BigDecimal fraction1 = new BigDecimal(fraction);
        long articleid1 = Long.parseLong(articleid);
        scoreService.setScore(fraction1,articleid1);
//        String route ="redirect:/managerVoteList/"+userid;
        return "redirect:/managerVoteList";
    }

    @RequestMapping("getScore")
    public String getScore(Integer scoreid, Model model, HttpServletRequest servletRequest){
        Score score = scoreService.getScore(scoreid);
        List list =new ArrayList();
        Cookie[] cookies = servletRequest.getCookies();
        for (Cookie cookie:cookies){
            list.add(cookie.getValue());
        }
        System.out.println(list.get(0));
        Object userid = list.get(0).toString();
        model.addAttribute("score",score);
        String route ="redirect:/managerVoteList/"+userid;
        return route;
    }
}
