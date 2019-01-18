package com.vict.sigurate;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
public interface SignInRepository extends CommonJpaRepository<SignIn,Long> {
    @Modifying
    @Query("update SignIn  set leaveTime=:leaveTime where  DATEDIFF(workTime,:currentDate)=0 ")
    void updateByDate(@Param("currentDate")Date currentDate,@Param("leaveTime")Date leaveTime);
    @Query("select distinct  si from SignIn si where DATEDIFF(workTime,:workTime)=0")
    List<SignIn> findByWorkTime(@Param("workTime")Date workTime);
}
