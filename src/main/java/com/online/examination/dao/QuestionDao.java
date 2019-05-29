package com.online.examination.dao;

import com.online.examination.model.Question;

import java.util.List;

public interface QuestionDao {
    void upsert(Question question);
    List<Question> randomSample(int total, Question.Type type);
}
