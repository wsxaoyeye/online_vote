package com.two.vote.utils;

import java.math.BigDecimal;
import java.util.List;

public class ScoreUtil {

    //求BigDecimal的平均值
    public static BigDecimal getAvg(List<BigDecimal> list){
        if (list.size() > 0){
            BigDecimal  bigDecimal = new BigDecimal(0);
            //list的长度
            BigDecimal bigDecimal1 = new BigDecimal(list.size());
            for (BigDecimal decimal : list) {
                bigDecimal = bigDecimal.add(decimal);
            }
            BigDecimal divide = bigDecimal.divide(bigDecimal1, 2, BigDecimal.ROUND_HALF_UP);
            return divide;
        }else {
            return new BigDecimal(0);
        }
    }
}
