package com.two.vote.service.impl;

import com.two.vote.dao.ScoreDao;
import com.two.vote.entity.Article;
import com.two.vote.entity.Optionss;
import com.two.vote.entity.Score;
import com.two.vote.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDao scoreDao;

    @Override
    public int insetScore(Score score) {
        return scoreDao.insetScore(score);
    }

    @Override
    public List<Article> queryNo(Integer userid) {
        return scoreDao.selectNo(userid);
    }

    @Override
    public List<Article> queryYes(Integer userid) {
        return scoreDao.selectYes(userid);
    }

    @Override
    public List<Optionss> queryOptionssByArticleid(Long articleid) {
        return scoreDao.selectOptionssByArticleid(articleid);
    }

    @Override
    public List<BigDecimal> queryFractions(Long articleid, Long optionssid) {
        return scoreDao.selectFractions(articleid,optionssid);
    }

    @Override
    public int getScore(Long articleid) {
        return scoreDao.getScore(articleid);
    }

    @Override
    public int setScore(BigDecimal fraction, Long articleid) {
        return scoreDao.setScore(fraction,articleid);
    }
}
