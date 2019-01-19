package com.vict.sigurate.domain;
import com.fasterxml.jackson.annotation.JsonFormat;
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
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name="work_time")
    private Date workTime;
    @JsonFormat( pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @Column(name="leave_time")
    private Date leaveTime;
    @Column(name="sign_person")
    private String signName;
}
