package com.guidewire.pstesting;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

public class QuestionSet extends ApplicationComponent {
    private final By questionSetLocator;

    public QuestionSet(ScreenObjectController controller, By questionSetLocator) {
        super(controller);
        this.questionSetLocator = questionSetLocator;
    }

    public boolean isVisible() {
        return getController().pageContains(questionSetLocator);
    }

    public QuestionSet waitUntilVisible() {
        getController().waitUntilPageContains(questionSetLocator);
        return this;
    }

    public void answerQuestion(String question, boolean yes) {
        WebElement rowElement = getController().findRow(questionSetLocator, question);
        if (rowElement == null) {
            throw new IllegalArgumentException("No row with the text \"" + question + "\" exists in the question set.");
        }
        WebElement cellElement = rowElement.findElements(By.tagName("td")).get((yes ? 2 : 3));
        getController()
                .click(cellElement.findElement(By.tagName("input")))
                .waitUntilUpdateDone();
    }
}
