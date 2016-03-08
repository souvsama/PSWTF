package com.guidewire.pstesting.policycenter.data;

import com.guidewire.pstesting.data.QuestionSetTestData;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import java.util.Collections;
import java.util.List;

@XmlAccessorType(XmlAccessType.FIELD)
public class QualificationsTestData {
    @XmlAttribute(name = "policy-type") private String policyType;

    @XmlElement(name = "questions") private List<QuestionSetTestData> questionSets;

    public String getPolicyType() {
        return policyType;
    }

    public void setPolicyType(String policyType) {
        this.policyType = policyType;
    }

    public List<QuestionSetTestData> getQuestionSets() {
        return (questionSets == null ? Collections.<QuestionSetTestData>emptyList() : questionSets);
    }

    public void setQuestionSets(List<QuestionSetTestData> questionSets) {
        this.questionSets = questionSets;
    }

    @Override
    public String toString() {
        return "QualificationsTestData{" +
                "policyType='" + policyType + '\'' +
                '}';
    }
}
