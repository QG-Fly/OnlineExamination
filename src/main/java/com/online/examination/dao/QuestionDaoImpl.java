package com.online.examination.dao;

import com.online.examination.model.Question;
import org.bson.Document;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.Aggregation;
import org.springframework.data.mongodb.core.aggregation.AggregationResults;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class QuestionDaoImpl implements QuestionDao {
  @Autowired private MongoTemplate mongoTemplate;

  @Override
  public void upsert(Question question) {
    Document doc = new Document();
    mongoTemplate.getConverter().write(question, doc);
    Update update = Update.fromDocument(doc);
    mongoTemplate.upsert(
        Query.query(Criteria.where("title").is(question.getTitle())), update, Question.class);
  }

  @Override
  public List<Question> randomSample(int total, String multiSelect) {
    Aggregation agg =
        newAggregation(match(Criteria.where("multiSelect").is(multiSelect)), sample(total));
    // Convert the aggregation result into a List
    AggregationResults<Question> groupResults =
        mongoTemplate.aggregate(agg, Question.class, Question.class);
    List<Question> result = groupResults.getMappedResults();
    return result;
  }
}
