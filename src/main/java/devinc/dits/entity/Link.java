package devinc.dits.entity;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Link {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int linkId;

    @Column
    private String link;

    @ManyToOne(cascade = {CascadeType.REFRESH}, fetch = FetchType.LAZY)
    @JoinColumn(name = "literatureId")
    Literature literature;

    @Override
    public String toString() {
        return "Link{" +
                "linkId=" + linkId +
                ", link='" + link + '\'' +
                ", literatureId=" + literature.getLiteratureId() +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Link link1 = (Link) o;
        return linkId == link1.linkId && link.equals(link1.link) && literature.getLiteratureId() == link1.literature.getLiteratureId();
    }

    @Override
    public int hashCode() {
        return Objects.hash(linkId);
    }

    public int getLinkId() {
        return linkId;
    }

    public void setLinkId(int linkId) {
        this.linkId = linkId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public Literature getLiterature() {
        return literature;
    }

    public void setLiterature(Literature literature) {
        this.literature = literature;
    }
}
