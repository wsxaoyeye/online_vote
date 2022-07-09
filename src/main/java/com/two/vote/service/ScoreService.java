package com.two.vote.service;

import com.two.vote.entity.Score;

import java.math.BigDecimal;

public interface ScoreService {
    Score getScore(Integer scoreid);
    int setScore(BigDecimal fraction, Long articleid);

    int insetScore(Score score);
}
