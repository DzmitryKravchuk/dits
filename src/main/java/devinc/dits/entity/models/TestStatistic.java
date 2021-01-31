package devinc.dits.entity.models;

import java.util.Objects;

public class TestStatistic {
    private Integer id;
    private String name;
    private Integer numberPassedTimes;
    private Double percentOfRightAnswers;

    public TestStatistic() {
    }

    public TestStatistic(Integer id, String name, Integer numberPassedTimes, Double percentOfRightAnswers) {
        this.id = id;
        this.name = name;
        this.numberPassedTimes = numberPassedTimes;
        this.percentOfRightAnswers = percentOfRightAnswers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return "TestStatistic{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", numberPassedTimes=" + numberPassedTimes +
                ", percentOfRightAnswers=" + percentOfRightAnswers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TestStatistic that = (TestStatistic) o;
        return id.equals(that.id) && name.equals(that.name) && numberPassedTimes.equals(that.numberPassedTimes) && percentOfRightAnswers.equals(that.percentOfRightAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, numberPassedTimes, percentOfRightAnswers);
    }
}
