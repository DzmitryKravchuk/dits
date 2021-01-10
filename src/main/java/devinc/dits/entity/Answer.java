package devinc.dits.entity;

import javax.persistence.*;

@Entity
public class Answer {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int literatureId;


}
