package com.vict.sigurate;

import com.vict.sigurate.utils.PaginationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
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
    private SignInRepository signInRepository;

    @RequestMapping("/in")
    @CrossOrigin
    public ResponseEntity<?> signIn() {
        StringBuffer msg = new StringBuffer();
        List<SignIn> list = this.signInRepository.findByWorkTime(new Date());
        SignIn in = new SignIn();
        if (CollectionUtils.isEmpty(list)) {
            in.setWorkTime(new Date());
            this.signInRepository.save(in);
            msg.append("老铁，恭喜你打卡成功！");
        } else {
            msg.append("老铁，你今天已经打过卡了！");
        }
        return ResponseEntity.ok(msg);
    }

    @CrossOrigin
    @RequestMapping("/out")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<?> signOut() {
        this.signInRepository.updateByDate(new Date(), new Date());
        return ResponseEntity.ok("老铁，恭喜你打卡成功！");
    }

    @CrossOrigin
    @RequestMapping("/records")
    @Transactional(rollbackFor = Exception.class)
    public ResponseEntity<List<SignIn>> getRecords(Pageable pa) {
        Page<SignIn> pager = this.signInRepository.findAll(pa);
        HttpHeaders headers = PaginationUtil.generatePaginationHttpHeaders(pager, "/api/sign/records");

        return new ResponseEntity<>(pager.getContent(), headers, HttpStatus.OK);
    }
}
