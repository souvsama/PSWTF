package com.guidewire.pstesting.claimcenter.data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;

@XmlAccessorType(XmlAccessType.FIELD)
public class PaymentDetailTestData {
    @XmlAttribute(name = "category")   private String category;
    @XmlAttribute(name = "amount")  private String amount;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "PaymentDetailTestData{" +
                "category='" + category + '\'' +
                ", amount='" + amount + '\'' +
                '}';
    }
}
