package com.guidewire.pstesting.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

/**
 * This class represents a question set question in a test data XML file.
 */
@XmlAccessorType(XmlAccessType.FIELD)
public class QuestionTestData {
    @XmlAttribute(name = "message") private String message;
    @XmlAttribute(name = "answer") private  String answer;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public String toString() {
        return "QuestionTestData{" +
                "message='" + message + '\'' +
                ", answer='" + answer + '\'' +
                '}';
    }
}
