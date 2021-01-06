package devinc.dits.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Topic {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int topicId;
    @Column
    private String name;
    @Column
    private String description;

    @OneToMany(mappedBy = "topic", fetch = FetchType.EAGER)
    private List<Test> tests;

    @Override
    public String toString() {
        return "Topic{" +
                "topicId=" + topicId +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", tests=" + tests +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Topic topic = (Topic) o;
        return topicId == topic.topicId && name.equals(topic.name) && description.equals(topic.description);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topicId);
    }

    public int getTopicId() {
        return topicId;
    }

    public void setTopicId(int topicId) {
        this.topicId = topicId;
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

    public List<Test> getTests() {
        return tests;
    }

    public void setTests(List<Test> tests) {
        this.tests = tests;
    }
}
