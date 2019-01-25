package com.vict.sigurate.registery;

import com.vict.sigurate.domain.SignIn;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.List;

@Component
public interface SignInRepository extends CommonJpaRepository<SignIn,Long> {
    @Modifying
    @Query("update SignIn  set leaveTime=:leaveTime where  DATEDIFF(workTime,:currentDate)=0 ")
    int  updateByDate(@Param("currentDate")Date currentDate,@Param("leaveTime")Date leaveTime);
    @Query("select distinct  si from SignIn si where DATEDIFF(workTime,:workTime)=0")
    List<SignIn> findByWorkTime(@Param("workTime")Date workTime);
    @Query("select distinct  si from SignIn si where SUBSTRING(work_time , 1 , 7)=:month")
    List<SignIn> queryByMonth(@Param("month") String month);
}
