package com.example.community.service;

import com.example.community.Exception.CustomizeErrorCode;
import com.example.community.Exception.CustomizeException;
import com.example.community.dto.PaginationDTO;
import com.example.community.dto.QuestionDTO;
import com.example.community.mapper.QuestionExtMapper;
import com.example.community.mapper.QuestionMapper;
import com.example.community.mapper.UserMapper;
import com.example.community.model.Question;
import com.example.community.model.QuestionExample;
import com.example.community.model.User;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 同时使用QuestionMapper 和 UserMapper
 * 起到一个组装的作用
 * 中间层 --- service
 */
@Service
public class QuestionService {

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private QuestionMapper questionMapper;
    @Autowired
    private QuestionExtMapper questionExtMapper;


    public PaginationDTO list(Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

        Integer totalCount = (int)questionMapper.countByExample(new QuestionExample());
        Integer totalPage = (totalCount % size == 0) ? totalCount / size : totalCount / size + 1;

        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;

        paginationDTO.setPagination(totalPage, page);

        //size*(page - 1)
        Integer offset = size * (page - 1);
//        List<Question> questions = questionMapper.list(offset, size);
        QuestionExample questionExample = new QuestionExample();
        questionExample.setOrderByClause("gmt_create desc");
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(questionExample, new RowBounds(offset, size));

        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public PaginationDTO list(Long UserId, Integer page, Integer size) {
        PaginationDTO paginationDTO = new PaginationDTO();

//        Integer totalCount = questionMapper.countByUserId(UserId);
        QuestionExample questionExample = new QuestionExample();
        questionExample.createCriteria()
                .andCreatorEqualTo(UserId);
        Integer totalCount = (int)questionMapper.countByExample(questionExample);
        Integer totalPage = (totalCount % size == 0) ? totalCount / size : totalCount / size + 1;

        if (page < 1) page = 1;
        if (page > totalPage) page = totalPage;

        paginationDTO.setPagination(totalPage, page);


        //size*(page - 1)
        Integer offset = size * (page - 1);
//        List<Question> questions = questionMapper.listByUserId(UserId, offset, size);
        QuestionExample example = new QuestionExample();
        example.createCriteria()
                .andCreatorEqualTo(UserId);
        List<Question> questions = questionMapper.selectByExampleWithRowbounds(example, new RowBounds(offset, size));
        List<QuestionDTO> questionDTOList = new ArrayList<>();

        for (Question question : questions) {
            User user = userMapper.selectByPrimaryKey(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question, questionDTO);
            questionDTO.setUser(user);
            questionDTOList.add(questionDTO);
        }

        paginationDTO.setQuestions(questionDTOList);
        return paginationDTO;
    }

    public QuestionDTO getById(Long id) {
        Question question = questionMapper.selectByPrimaryKey(id);
        if (question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question, questionDTO);

        User user = userMapper.selectByPrimaryKey(question.getCreator());
        questionDTO.setUser(user);

        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if (question.getId() == null) {
            // 创建新的问题
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setCommentCount(0);
            question.setLikeCount(0);
            questionMapper.insert(question);
        }else {
            // 更新旧的问题
//            question.setGmtModified(question.getGmtCreate());
            Question updateQuestion = new Question();
            updateQuestion.setGmtModified(System.currentTimeMillis());
            updateQuestion.setTitle(question.getTitle());
            updateQuestion.setDescription(question.getDescription());
            updateQuestion.setTag(question.getTag());

            QuestionExample questionExample = new QuestionExample();
            questionExample.createCriteria()
                    .andIdEqualTo(question.getId());

            int updated = questionMapper.updateByExampleSelective(updateQuestion, questionExample);
            if (updated != 1) {
                throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
            }
        }
    }

    public void incView(Long id) {
        Question question = new Question();
        question.setId(id);
        question.setViewCount(1);
        questionExtMapper.incView(question);
    }

    public List<QuestionDTO> selectRelated(QuestionDTO questionDTO) {
        if (StringUtils.isBlank(questionDTO.getTag())){
            return new ArrayList<>();
        }

        Question question = new Question();
        question.setId(questionDTO.getId());
        question.setTag(questionDTO.getTag().replace(',','|'));

        List<Question> questions = questionExtMapper.selectRelated(question);
        List<QuestionDTO> relatedQuestionDTOS = questions.stream().map(q -> {
            QuestionDTO questionDTO1 = new QuestionDTO();
            BeanUtils.copyProperties(q, questionDTO1);
            return questionDTO1;
        }).collect(Collectors.toList());

        return relatedQuestionDTOS;
    }
}
