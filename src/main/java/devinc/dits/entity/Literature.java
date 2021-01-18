package devinc.dits.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Literature {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int literatureId;

    @Column
    private String description;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId")
    Question question;

    @Override
    public String toString() {
        return "Literature{" +
                "literatureId=" + literatureId +
                ", description='" + description + '\'' +
                ", questionId=" + question.getQuestionId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Literature that = (Literature) o;
        return literatureId == that.literatureId && description.equals(that.description) && question.getQuestionId() == that.question.getQuestionId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(literatureId);
    }

    public int getLiteratureId() {
        return literatureId;
    }

    public void setLiteratureId(int literatureId) {
        this.literatureId = literatureId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
