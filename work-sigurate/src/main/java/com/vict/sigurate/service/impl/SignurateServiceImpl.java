package com.vict.sigurate.service.impl;

import com.vict.sigurate.domain.SignIn;
import com.vict.sigurate.registery.SignInRepository;
import com.vict.sigurate.service.SignurateService;
import com.vict.sigurate.webrest.vm.SignVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service("signurateService")
public class SignurateServiceImpl implements SignurateService {
    @Autowired
    private SignInRepository signInRepository;
    @Override
    public SignIn sign(SignIn sign) {
        return this.signInRepository.save(sign);
    }

    @Override
    public int signOutToday() {
        return this.signInRepository.updateByDate(new Date(),new Date());
    }

    @Override
    public int signOutByDate(Date date) {
        return this.signInRepository.updateByDate(date,date);
    }


    @Override
    public Page<SignIn> findAll(Pageable page, SignVM signVm) {
        return this.signInRepository.findAll(page);
    }

    @Override
    public List<SignIn> findByDate(Date date) {
        return this.signInRepository.findByWorkTime(date);
    }

    @Override
    public SignIn forgetSign(SignIn sign) {
        return this.signInRepository.save(sign);
    }
}
