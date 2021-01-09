package devinc.dits.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

public class Literature {
    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int answerId;
}
