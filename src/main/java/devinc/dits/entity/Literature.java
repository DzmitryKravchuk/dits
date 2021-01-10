package devinc.dits.entity;

import javax.persistence.*;

@Entity
public class Literature {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerId;
}
