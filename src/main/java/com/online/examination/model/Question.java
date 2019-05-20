package com.online.examination.model;

import java.util.Objects;

/**
 * Created by HP on 2019/5/14.
 */
public class Question {
    private String id;
    private String title;
    private String answer;
    private String multiSelect;
    private String pageNum;
    private String rightRate;
    private String note;

    public Question title(String title) {
        this.title = title;
        return this;
    }

    public Question answer(String answer){
        this.answer = answer;
        return this;
    }
    public Question note(String note){
        this.note = note;
        return this;
    }
    public Question rightRate(String rightRate){
        this.rightRate = rightRate;
        return this;
    }
    public Question pageNum(String pageNum){
        this.pageNum = pageNum;
        return this;
    }
    public Question multiSelect(String multiSelect){
        this.multiSelect = multiSelect;
        return this;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return Objects.equals(id, question.id) &&
                Objects.equals(title, question.title) &&
                Objects.equals(answer, question.answer) &&
                Objects.equals(multiSelect, question.multiSelect) &&
                Objects.equals(pageNum, question.pageNum) &&
                Objects.equals(rightRate, question.rightRate) &&
                Objects.equals(note, question.note);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, answer, multiSelect, pageNum, rightRate, note);
    }

    @Override
    public String toString() {
        return "Question{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", answer='" + answer + '\'' +
                ", multiSelect=" + multiSelect +
                ", pageNum='" + pageNum + '\'' +
                ", rightRate='" + rightRate + '\'' +
                ", note='" + note + '\'' +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public String getMultiSelect() {
        return multiSelect;
    }

    public void setMultiSelect(String multiSelect) {
        this.multiSelect = multiSelect;
    }

    public String getPageNum() {
        return pageNum;
    }

    public void setPageNum(String pageNum) {
        this.pageNum = pageNum;
    }

    public String getRightRate() {
        return rightRate;
    }

    public void setRightRate(String rightRate) {
        this.rightRate = rightRate;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }
}
