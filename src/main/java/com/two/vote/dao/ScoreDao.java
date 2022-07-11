package com.two.vote.dao;

import com.two.vote.entity.Article;
import com.two.vote.entity.Optionss;
import com.two.vote.entity.Score;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

@Mapper
//public interface ScoreDao extends JpaRepository<Score,String> {
public interface ScoreDao {

    @Select("select AVG(fraction) from score where articleid=#{articleid}")
    int getScore(Long articleid);

//    @Transactional
//    @Query(value = "insert into score(score,articleid) values (score=?1,articleid=?2)",nativeQuery = true)
    @Insert("insert into score(fraction,articleid) values(#{fraction},#{articleid});")
    int setScore(@Param("fraction") BigDecimal fraction, @Param("articleid") Long articleid);

    //评分
    @Insert("insert into score values(#{scoreid},#{fraction},#{articleid},#{optionssid},#{userid})")
    int insetScore(Score score);

    //查看未评分
    @Select("select * from article where id not in (select articleid from score where userid = #{userid})")
    List<Article> selectNo(@Param("userid") Integer userid);

    //查看已评分
    @Select("select * from article where id in (select articleid from score where userid = #{userid})")
    List<Article> selectYes(@Param("userid") Integer userid);

    //查看选项
    @Select("select * from optionss where articleid = #{articleid}")
    List<Optionss> selectOptionssByArticleid(@Param("articleid") Long articleid);

    //查看某一选项的评分
    @Select("select fraction from score where articleid = #{articleid} and optionssid = #{optionssid}")
    List<BigDecimal> selectFractions(@Param("articleid") Long articleid,@Param("optionssid") Long optionssid);
}
