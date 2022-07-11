package com.two.vote.controller;

import com.two.vote.entity.Article;
import com.two.vote.entity.Optionss;
import com.two.vote.entity.Score;
import com.two.vote.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
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

//    @Autowired
//    private CommonService commonService;

    @RequestMapping("setScore")
    public String setScore(String fraction,String articleid){
        System.out.println(fraction+"==="+articleid);
//        BigDecimal bigDecimal = BigDecimal.valueOf(score);
        BigDecimal fraction1 = new BigDecimal(fraction);
        long articleid1 = Long.parseLong(articleid);
        scoreService.setScore(fraction1,articleid1);
//        Article article = commonService.getArticle(Long.parseLong(articleid));
//        model.addAttribute("fraction",fraction);
//        String ip = "106.64.246.55";
//        String route ="redirect:/voteList/"+ip;
        return "redirect:hasUser";
    }

    @RequestMapping("getScore")
    public String getScore(Long articleid, Model model, HttpServletRequest servletRequest){
        int score = scoreService.getScore(articleid);
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

    /**
     *评分管理列表
     */
    @GetMapping("scoreList/{userId}")
    public String getManagerVoteList(@PathVariable("userId") long userId, Model model, HttpServletRequest request){
        int i = Integer.parseInt(String.valueOf(userId));
        List<Article> articlesNo = scoreService.queryNo(i);
        model.addAttribute("articlesNo",articlesNo);
        List<Article> articlesYes = scoreService.queryYes(i);
        model.addAttribute("articlesYes",articlesYes);
        return "scoremanager";
    }

    //评分准备
    @GetMapping("/setScore/{articleid}/{userid}")
    public String setScore(@PathVariable("articleid") Long articleid,@PathVariable("userid") Integer userid,Model model,HttpServletRequest request){
        List<Optionss> optionsses = scoreService.queryOptionssByArticleid(articleid);
        String optionvalue = optionsses.get(0).getOptionvalue();
        String optionvalue1 = optionsses.get(1).getOptionvalue();
        Long optionid1 = optionsses.get(0).getId();
        Long optionid2 = optionsses.get(1).getId();
        model.addAttribute("optionvalue",optionvalue);
        model.addAttribute("optionvalue1",optionvalue1);
        model.addAttribute("optionid1",optionid1);
        model.addAttribute("optionid2",optionid2);
        model.addAttribute("userid",userid);
        model.addAttribute("articleid",articleid);
        return "JudgesScore";
    }

    //评分
    @PostMapping("/insetScore")
    public String insetScore(HttpServletRequest request,Model model){

        //获取选手分数
        String fraction1 = request.getParameter("fraction1");
        String fraction2 = request.getParameter("fraction2");
        //获取optionid
        String optionid1 = request.getParameter("optionid1");
        String optionid2 = request.getParameter("optionid2");
        //获取articleid
        String articleid = request.getParameter("articleid");
        //获取userid
        String userid = request.getParameter("userid");

        //转换格式

        //articleid
        long articleid1 = Long.parseLong(articleid);
        //optionssid
        long optionid11 = Long.parseLong(optionid1);
        long optionid12 = Long.parseLong(optionid2);
        //userid
        int userid1 = Integer.parseInt(userid);

        //不能为空
        if ("".equals(fraction1) && "".equals(fraction2)){
            request.getSession().setAttribute("sc","请打分");
            request.getSession().setAttribute("ss","请打分");
            request.getSession().setAttribute("fraction1",fraction1);
            request.getSession().setAttribute("fraction2",fraction2);
            return "redirect:/setScore/"+articleid1+"/"+userid1;
        }
        if ("".equals(fraction1)){
            request.getSession().setAttribute("sc","请打分");
            request.getSession().setAttribute("ss","");
            request.getSession().setAttribute("fraction1",fraction1);
            request.getSession().setAttribute("fraction2",fraction2);
            return "redirect:/setScore/"+articleid1+"/"+userid1;
        }
        if ("".equals(fraction2)){
            request.getSession().setAttribute("sc","");
            request.getSession().setAttribute("ss","请打分");
            request.getSession().setAttribute("fraction1",fraction1);
            request.getSession().setAttribute("fraction2",fraction2);
            return "redirect:/setScore/"+articleid1+"/"+userid1;
        }
        //fraction
        BigDecimal fraction11 = BigDecimal.valueOf(Double.valueOf(fraction1));
        BigDecimal fraction12 = BigDecimal.valueOf(Double.valueOf(fraction2));

        BigDecimal bigDecimal = new BigDecimal(0);
        BigDecimal bigDecimal1 = new BigDecimal(100);
        int i = bigDecimal.compareTo(fraction11);
        int i1 = bigDecimal.compareTo(fraction12);
        int i2 = bigDecimal1.compareTo(fraction11);
        int i3 = bigDecimal1.compareTo(fraction12);

        //设置范围
        if((i == 1 || i2 == -1) && (i1 == 1 || i3 == -1)){
            request.getSession().setAttribute("sc","请输入0-100分的有效分数");
            request.getSession().setAttribute("ss","请输入0-100分的有效分数");
            request.getSession().setAttribute("fraction1",fraction1);
            request.getSession().setAttribute("fraction2",fraction2);
            return "redirect:/setScore/"+articleid1+"/"+userid1;
        }
        if(i == 1 || i2 == -1){
            request.getSession().setAttribute("sc","请输入0-100分的有效分数");
            request.getSession().setAttribute("ss","");
            request.getSession().setAttribute("fraction1",fraction1);
            request.getSession().setAttribute("fraction2",fraction2);
            return "redirect:/setScore/"+articleid1+"/"+userid1;
        }
        if(i1 == 1 || i3 == -1){
            request.getSession().setAttribute("sc","");
            request.getSession().setAttribute("ss","请输入0-100分的有效分数");
            request.getSession().setAttribute("fraction1",fraction1);
            request.getSession().setAttribute("fraction2",fraction2);
            return "redirect:/setScore/"+articleid1+"/"+userid1;
        }
        Score score1 = new Score(null, fraction11, articleid1, optionid11, userid1);
        Score score2 = new Score(null, fraction12, articleid1, optionid12, userid1);
        scoreService.insetScore(score1);
        scoreService.insetScore(score2);

        request.getSession().removeAttribute("sc");
        request.getSession().removeAttribute("ss");
        request.getSession().removeAttribute("fraction1");
        request.getSession().removeAttribute("fraction2");

        return "redirect:/scoreList/" + userid1;
    }
}
