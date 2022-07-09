package com.two.vote.dao;

import com.two.vote.entity.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;

@Mapper
//public interface ScoreDao extends JpaRepository<Score,String> {
public interface ScoreDao {

    @Select("select fraction from score where articleid=#{articleid}")
    Score getScore(Long articleid);

//    @Transactional
//    @Query(value = "insert into score(score,articleid) values (score=?1,articleid=?2)",nativeQuery = true)
    @Insert("insert into score(fraction,articleid) values(#{fraction},#{articleid});")
    int setScore(@Param("fraction") BigDecimal fraction, @Param("articleid") Long articleid);

    @Insert("insert into score values(#{scoreid},#{fraction},#{articleid});")
    int insetScore(Score score);
}
