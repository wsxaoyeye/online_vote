package com.two.vote.controller;

import com.two.vote.entity.Article;
import com.two.vote.entity.Optionss;
import com.two.vote.entity.Score;
import com.two.vote.entity.view.ArticleAndOptionsView;
import com.two.vote.entity.view.OptionAndNumView;
import com.two.vote.service.CommonService;
import com.two.vote.service.ManagerService;
import com.two.vote.service.ScoreService;
import com.two.vote.service.UserService;
import com.two.vote.utils.CommonUtil;
import com.two.vote.utils.ScoreUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.List;

/**
 * @投票管理
 */
@Controller
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    @Autowired
    private CommonService commonService;

    @Autowired
    private UserService userService;

    @Autowired
    private ScoreService scoreService;


    /**
     *投票管理列表
     */
    @GetMapping("managerVoteList/{userId}")
    public String getManagerVoteList(@PathVariable("userId") long userId, Model model, HttpServletRequest request){
        HttpSession session = request.getSession();
        CommonUtil.setUserNameIdByCookie(request,model);
        List<Article> list = managerService.getManagerVoteList(userId);
        if (list!=null&&list.size()>0){
            model.addAttribute("voteList",list);
            return "manager";
        }
        String msg = "管理投票列表为空！";
        model.addAttribute("msg",msg);
        return "tipPage/voteError";
    }

    /**
     * 删除投票（删两表）
     */
    @GetMapping("deleteArticle/{articleId}/{userId}")
    public String deleteArticle(@PathVariable("articleId") long articleId,@PathVariable("userId") Integer userId){
        managerService.deleteArticleAndOptions(articleId);
        return "redirect:/managerVoteList/"+userId;
    }

    /**
     *查看投票数据
     */
    @GetMapping("manageCheckResult/{id}")
    public String manageCheckResult(@PathVariable("id") long articid,Model model,HttpServletRequest request){
        CommonUtil.setUserNameIdByCookie(request,model);
        ArticleAndOptionsView result = commonService.getCheckResulu(articid);
        int score = scoreService.getScore(articid);
        List<Optionss> optionsses = scoreService.queryOptionssByArticleid(articid);
        String optionvalue = optionsses.get(0).getOptionvalue();
        String optionvalue1 = optionsses.get(1).getOptionvalue();
        Long optionid1 = optionsses.get(0).getId();
        Long optionid2 = optionsses.get(1).getId();

        List<BigDecimal> bigDecimals = scoreService.queryFractions(articid, optionid1);
        List<BigDecimal> bigDecimals1 = scoreService.queryFractions(articid, optionid2);

        //求平均数
        BigDecimal avg = ScoreUtil.getAvg(bigDecimals);
        BigDecimal avg1 = ScoreUtil.getAvg(bigDecimals1);

        List<OptionAndNumView> optionAndNumViews = result.getOptionAndNumViews();
        double total = 0;
        for (OptionAndNumView optionAndNumView : optionAndNumViews) {
            total=total+optionAndNumView.getNum();
        }
        if (result.getHot()==null){
            result.setHot(1l);
        }
        long hot = result.getHot();
        double hotNum = (double)hot;
        double rate = (total/hotNum)*100;
        DecimalFormat df = new DecimalFormat("#.00");
        String rateString = df.format(rate)+"%";
        model.addAttribute("rate",rateString);
        model.addAttribute("total",total);
        model.addAttribute("resultView",result);
        model.addAttribute("optionvalue",optionvalue);
        model.addAttribute("optionvalue1",optionvalue1);
        model.addAttribute("avg",avg);
        model.addAttribute("avg1",avg1);
        model.addAttribute("score",score);
        return "chart";
    }

    /**
     * 修改投票
     */
    @GetMapping("retrunVote/{id}")
    public String retrunVote(@PathVariable("id")long articleid,Model model,HttpServletRequest request){
        CommonUtil.setUserNameIdByCookie(request,model);
        ArticleAndOptionsView articleAndOptionsView = commonService.findAritleAndOptionById(articleid);
        model.addAttribute("view",articleAndOptionsView);
        return "update";
    }
}
