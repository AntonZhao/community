package com.example.community.mapper;

import com.example.community.dto.QuestionQueryDTO;
import com.example.community.model.Question;
import com.example.community.model.QuestionExample;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import java.util.List;

public interface QuestionExtMapper {
    int incView(Question record);

    int incComment(Question record);

    List<Question> selectRelated(Question record);

    Integer countBySearch(QuestionQueryDTO questionQueryDTO);

    List<Question> selectBySearch(QuestionQueryDTO questionQueryDTO);
}