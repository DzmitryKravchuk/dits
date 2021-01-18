package devinc.dits.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Answer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerId;

    @Column
    private String description;

    @Column
    private Boolean correct;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "questionId")
    Question question;

    @Override
    public String toString() {
        return "Answer{" +
                "answerId=" + answerId +
                ", description='" + description + '\'' +
                ", correct=" + correct +
                ", questionId=" + question.getQuestionId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Answer answer = (Answer) o;
        return answerId == answer.answerId && description.equals(answer.description) && correct.equals(answer.correct) && question.getQuestionId()== answer.question.getQuestionId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(answerId);
    }

    public int getAnswerId() {
        return answerId;
    }

    public void setAnswerId(int answerId) {
        this.answerId = answerId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Boolean getCorrect() {
        return correct;
    }

    public void setCorrect(Boolean correct) {
        this.correct = correct;
    }

    public Question getQuestion() {
        return question;
    }

    public void setQuestion(Question question) {
        this.question = question;
    }
}
