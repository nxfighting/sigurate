package com.vict.sigurate.webrest;

import com.vict.sigurate.registery.SignInRepository;
import com.vict.sigurate.domain.SignIn;
import com.vict.sigurate.service.SignurateService;
import com.vict.sigurate.utils.PaginationUtil;
import com.vict.sigurate.webrest.vm.SignVM;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;

/**
 * @author
 * @create 2018-12-24 17:07
 */
@RestController
@RequestMapping("/api/sign")
public class SignController {

    @Autowired
    private SignurateService signurateService;

    @RequestMapping("/in")
    @CrossOrigin
    public ResponseEntity<?> signIn(SignIn signIn) {
        StringBuffer msg = new StringBuffer();
        List<SignIn> list = this.signurateService.findByDate(new Date());
        if(ObjectUtils.isEmpty(signIn)){
            signIn =new SignIn();
            signIn.setSignName("zhougf");
        }
        if (CollectionUtils.isEmpty(list)) {
            signIn.setWorkTime(new Date());
            this.signurateService.sign(signIn);
            msg.append("老铁，恭喜你打卡成功！");
        } else {
            msg.append("老铁，你今天已经打过卡了！");
        }
        return ResponseEntity.ok(msg);
    }

    @CrossOrigin
    @RequestMapping("/out")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> signOut(Date leaveTime) {
        if(leaveTime==null)
            leaveTime=new Date();
        this.signurateService.signOutByDate(leaveTime);
        return ResponseEntity.ok("老铁，恭喜你打卡成功！");
    }

    @CrossOrigin
    @RequestMapping("/forget")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> getRecords(SignIn signIn) {
        this.signurateService.sign(signIn);
        return ResponseEntity.ok("老铁，恭喜你补打卡成功！");
    }

    @CrossOrigin
    @RequestMapping("/records")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<List<SignIn>> getRecords(Pageable pa, SignVM vm) {
        Page<SignIn> pager = this.signurateService.findAll(pa,vm);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pager, "/api/sign/records");
        return new ResponseEntity<>(pager.getContent(), headers, HttpStatus.OK);
    }
}
