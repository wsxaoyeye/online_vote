package com.two.vote.service;

import com.two.vote.entity.Article;
import com.two.vote.entity.Optionss;
import com.two.vote.entity.Score;

import java.math.BigDecimal;
import java.util.List;

public interface ScoreService {
    int getScore(Long articleid);
    int setScore(BigDecimal fraction, Long articleid);

    int insetScore(Score score);

    List<Article> queryNo(Integer userid);

    List<Article> queryYes(Integer userid);

    List<Optionss> queryOptionssByArticleid(Long articleid);
}
