package com.online.examination.service;

import com.online.examination.dao.QuestionDao;
import com.online.examination.model.Question;
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

/** Created by HP on 2019/5/14. */
@Service
public class UploadService {
  @Autowired
  private QuestionDao questionDao;

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
    System.out.println("firstRowIndex: " + firstRowIndex);
    System.out.println("lastRowIndex: " + lastRowIndex);
    for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
      Row row = sheet.getRow(rIndex);
      if (row != null) {
        try {
          Question question = new Question();
          question
              .title(getCellVal(row.getCell(0)))
              .answer(getCellVal(row.getCell(1)))
              .multiSelect(getCellVal(row.getCell(2)));
          int lastCellIndex = row.getLastCellNum();
          if (lastCellIndex > 3) {
            question
                .pageNum(getCellVal(row.getCell(3)))
                .rightRate(getCellVal(row.getCell(4)))
                .note(getCellVal(row.getCell(5)));
          }
          questionDao.upsert(question);
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }

  private String getCellVal(Cell cell) {
    if (cell != null) {
      return cell.toString();
    }
    return null;
  }
}
