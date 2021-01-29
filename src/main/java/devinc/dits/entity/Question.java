package devinc.dits.entity;

import javax.persistence.*;
import java.util.Objects;
import java.util.Set;

@Entity
public class Question {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int questionId;

    @Column
    private String description;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "testId")
    Test test;

    @OneToMany(mappedBy = "question", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private Set<Literature> literatureSet;

    @Override
    public String toString() {
        return "Question{" +
                "questionId=" + questionId +
                ", description='" + description + '\'' +
                ", testId=" + test.getTestId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Question question = (Question) o;
        return questionId == question.questionId && description.equals(question.description) && test.getTestId() == question.test.getTestId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(questionId);
    }

    public int getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Test getTest() {
        return test;
    }

    public void setTest(Test test) {
        this.test = test;
    }

    public Set<Literature> getLiteratureSet() {
        return literatureSet;
    }

    public void setLiteratureSet(Set<Literature> literatureSet) {
        this.literatureSet = literatureSet;
    }
}
