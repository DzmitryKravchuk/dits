package devinc.dits.entity.models;

public class PersonalStatisticModel {
    String userName;
    String testName;
    String questionDescription;
    int numberOfAnswer;
    double questionRate;

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getTestName() {
        return testName;
    }

    public void setTestName(String testName) {
        this.testName = testName;
    }

    public String getQuestionDescription() {
        return questionDescription;
    }

    public void setQuestionDescription(String questionDescription) {
        this.questionDescription = questionDescription;
    }

    public int getNumberOfAnswer() {
        return numberOfAnswer;
    }

    public void setNumberOfAnswer(int numberOfAnswer) {
        this.numberOfAnswer = numberOfAnswer;
    }

    public double getQuestionRate() {
        return questionRate;
    }

    public void setQuestionRate(double questionRate) {
        this.questionRate = questionRate;
    }
}
