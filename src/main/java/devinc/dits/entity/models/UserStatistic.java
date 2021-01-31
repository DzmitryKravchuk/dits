package devinc.dits.entity.models;

import java.util.Objects;

public class UserStatistic {
    private Integer id;
    private String userName;
    private String testName;
    private Integer numberPassedTimes;
    private Double percentOfRightAnswers;

    public UserStatistic() {
    }

    public UserStatistic(Integer id, String userName, String testName, Integer numberPassedTimes, Double percentOfRightAnswers) {
        this.id = id;
        this.userName = userName;
        this.testName = testName;
        this.numberPassedTimes = numberPassedTimes;
        this.percentOfRightAnswers = percentOfRightAnswers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    public Integer getNumberPassedTimes() {
        return numberPassedTimes;
    }

    public void setNumberPassedTimes(Integer numberPassedTimes) {
        this.numberPassedTimes = numberPassedTimes;
    }

    public Double getPercentOfRightAnswers() {
        return percentOfRightAnswers;
    }

    public void setPercentOfRightAnswers(Double percentOfRightAnswers) {
        this.percentOfRightAnswers = percentOfRightAnswers;
    }

    @Override
    public String toString() {
        return "UserStatistic{" +
                "id=" + id +
                ", userName='" + userName + '\'' +
                ", testName='" + testName + '\'' +
                ", numberPassedTimes=" + numberPassedTimes +
                ", percentOfRightAnswers=" + percentOfRightAnswers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserStatistic that = (UserStatistic) o;
        return id.equals(that.id) && userName.equals(that.userName) && testName.equals(that.testName) && numberPassedTimes.equals(that.numberPassedTimes) && percentOfRightAnswers.equals(that.percentOfRightAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, userName, testName, numberPassedTimes, percentOfRightAnswers);
    }
}
