package com.two.vote.service;

import com.two.vote.entity.Score;

import java.math.BigDecimal;

public interface ScoreService {
    int getScore(Long articleid);
    int setScore(BigDecimal fraction, Long articleid);

    int insetScore(Score score);
}
