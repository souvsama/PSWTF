package com.guidewire.pstesting.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.List;

/**
 * This class represents a set of question data configured in a test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionSetTestData {
    @XmlAttribute(name = "set") private String questionSetId;

    @XmlElement(name = "question") private List<QuestionTestData> questions;

    public String getQuestionSetId() {
        return questionSetId;
    }

    public void setQuestionSetId(String questionSetId) {
        this.questionSetId = questionSetId;
    }

    public List<QuestionTestData> getQuestions() {
        return questions;
    }

    public void setQuestions(List<QuestionTestData> questions) {
        this.questions = questions;
    }
}
