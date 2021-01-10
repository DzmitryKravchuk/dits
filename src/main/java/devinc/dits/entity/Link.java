package devinc.dits.entity;

import javax.persistence.*;

@Entity
public class Link {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int linkId;

}
