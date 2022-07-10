package com.two.vote.service.impl;

import com.two.vote.dao.ScoreDao;
import com.two.vote.entity.Score;
import com.two.vote.service.ScoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
public class ScoreServiceImpl implements ScoreService {

    @Autowired
    private ScoreDao scoreDao;

    @Override
    public int insetScore(Score score) {
        return scoreDao.insetScore(score);
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
