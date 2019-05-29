package com.online.examination.service;

import com.online.examination.dao.QuestionDao;
import com.online.examination.model.Question;
import javafx.util.Pair;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/** Created by HP on 2019/5/14. */
@Service
public class UploadService {
  @Autowired private QuestionDao questionDao;
  private static final int SINGLE_SELECT_COUNT = 4;
  private static final int MULTI_SELECT_COUNT = 3;

  public void analysis(MultipartFile file) throws IOException {
    Workbook wb = null;
    if (file.getOriginalFilename().endsWith(".xls")) {
      wb = new HSSFWorkbook(file.getInputStream());
    } else if (file.getOriginalFilename().endsWith(".xlsx")) {
      wb = new XSSFWorkbook(file.getInputStream());
    } else {
      System.out.println("excel类型错误");
      return;
    }
    Sheet sheet = wb.getSheetAt(0);
    int firstRowIndex = sheet.getFirstRowNum() + 1;
    int lastRowIndex = sheet.getLastRowNum();
    for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
      Row row = sheet.getRow(rIndex);
      if (row != null) {
        try {
          Question question = new Question();
          Pair<String, List<String>> selects = analysisTitleAndAnsw(getCellVal(row.getCell(0)));
          question
              .title(selects.getKey())
              .selects(selects.getValue())
              .answer(getCellVal(row.getCell(1)));
          if (question.getAnswer().length() > 1) {
            question.setType(Question.Type.MULTI);
          } else {
            question.setType(Question.Type.SINGLE);
          }
          int lastCellIndex = row.getLastCellNum();
          if (lastCellIndex > 3) {
            question
                .pageNum(getCellVal(row.getCell(2)))
                .rightRate(getCellVal(row.getCell(3)))
                .note(getCellVal(row.getCell(4)));
          }
          questionDao.upsert(question);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  private Pair<String, List<String>> analysisTitleAndAnsw(String question) {
    List<String> answs = Arrays.asList(question.split("\n"));
    return new Pair<>(answs.get(0), answs.subList(1, answs.size()));
  }

  private String getCellVal(Cell cell) {
    if (cell != null) {
      return cell.toString();
    }
    return null;
  }

  public List<Question> randomSample(Question.Type type, int count) {
    return questionDao.randomSample(count, type);
  }
}
