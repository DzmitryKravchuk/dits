package devinc.dits.entity.models;

import java.util.Objects;

public class QuestionStatistic {
    private Integer id;
    private String description;
    private Integer numberOfAnswers;
    private Double percentOfRightAnswers;

    public QuestionStatistic() {
    }

    public QuestionStatistic(Integer id, String description, Integer numberOfAnswers, Double percentOfRightAnswers) {
        this.id = id;
        this.description = description;
        this.numberOfAnswers = numberOfAnswers;
        this.percentOfRightAnswers = percentOfRightAnswers;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getNumberOfAnswers() {
        return numberOfAnswers;
    }

    public void setNumberOfAnswers(Integer numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }

    public Double getPercentOfRightAnswers() {
        return percentOfRightAnswers;
    }

    public void setPercentOfRightAnswers(Double percentOfRightAnswers) {
        this.percentOfRightAnswers = percentOfRightAnswers;
    }

    @Override
    public String toString() {
        return "QuestionStatistic{" +
                "id=" + id +
                ", description='" + description + '\'' +
                ", numberOfAnswers=" + numberOfAnswers +
                ", percentOfRightAnswers=" + percentOfRightAnswers +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        QuestionStatistic that = (QuestionStatistic) o;
        return id.equals(that.id) && description.equals(that.description) && numberOfAnswers.equals(that.numberOfAnswers) && percentOfRightAnswers.equals(that.percentOfRightAnswers);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, description, numberOfAnswers, percentOfRightAnswers);
    }
}
