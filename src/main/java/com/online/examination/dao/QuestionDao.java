package com.online.examination.dao;

import com.online.examination.model.Question;

public interface QuestionDao {
    void upsert(Question question);
}
