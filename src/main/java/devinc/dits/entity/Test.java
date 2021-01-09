package devinc.dits.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Test {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int testId;
    @Column
    private String name;
    @Column
    private String description;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "topicId")
    Topic topic;

    @Override
    public String toString() {
        return "Test{" +
                "testId=" + testId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", topicId=" + topic.getTopicId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Test test = (Test) o;
        return testId == test.testId && name.equals(test.name) && description.equals(test.description) && topic.getTopicId()==test.getTopic().getTopicId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(testId);
    }

    public int getTestId() {
        return testId;
    }

    public void setTestId(int testId) {
        this.testId = testId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Topic getTopic() {
        return topic;
    }

    public void setTopic(Topic topic) {
        this.topic = topic;
    }
}
