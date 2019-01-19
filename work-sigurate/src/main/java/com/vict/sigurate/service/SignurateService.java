package com.vict.sigurate.service;

import com.vict.sigurate.domain.SignIn;
import com.vict.sigurate.webrest.vm.SignVM;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Date;
import java.util.List;

public interface SignurateService {

    SignIn sign(SignIn sign);

    int   signOutToday();

    int   signOutByDate(Date date);

    Page<SignIn> findAll(Pageable page, SignVM signVm);

    List<SignIn> findByDate(Date date);

    SignIn  forgetSign(SignIn sign);
}
