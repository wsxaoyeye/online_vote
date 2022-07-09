package com.two.vote.service;

import com.two.vote.entity.Score;

import java.math.BigDecimal;

public interface ScoreService {
    Score getScore(Long articleid);
    int setScore(BigDecimal fraction, Long articleid);

    int insetScore(Score score);
}
