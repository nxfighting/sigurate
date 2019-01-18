package com.vict.sigurate;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Date;

/**
 * @author
 * @create 2018-12-24 16:54
 */
@Data
@Entity
@Table(name="signin")
public class SignIn {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="work_time")
    private Date workTime;
    @Column(name="leave_time")
    private Date leaveTime;
}
