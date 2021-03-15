package com.lt.dailytest.controller;

import com.alibaba.fastjson.JSON;
import com.lt.dailytest.bean.RetVO;
import com.lt.dailytest.bean.SysEnum;
import com.lt.dailytest.service.MailSenderService;
import com.lt.dailytest.service.impl.MailSenderToolService;
import com.lt.dailytest.vo.MailVo;
import com.lt.dailytest.vo.ReqVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author tong.luo
 * @description MailContoller
 * @date 2021/3/8 17:06
 */
@RequestMapping("mail")
@RestController
public class MailContoller {
    private final Logger LOGGER = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private MailSenderToolService mailSenderToolService;

    @GetMapping(value = {"","/"})
    public ModelAndView index() {
        ModelAndView modelAndView = new ModelAndView("mail/sendMail");
        modelAndView.addObject("from", mailSenderToolService.getMailSendFrom());
//        modelAndView.addObject("from", "luo12828@foxmail.com");
        return modelAndView;
    }

    @PostMapping("sendMail")
    public MailVo sendMail(MailVo mailVo, MultipartFile[] files) {
        LOGGER.info("req：{}", JSON.toJSONString(mailVo));
        mailVo.setMultipartFiles(files);
        return mailSenderToolService.sendMail(mailVo);
    }


    /**
     * @param reqVO
     * @return
     */
    @RequestMapping(value = "sendHtmlMail", method = RequestMethod.POST)
    public RetVO<Object> sendHtmlMail(@RequestBody ReqVO reqVO) {
        LOGGER.info("req:{}", JSON.toJSONString(reqVO));
        this.mailSenderService.sendHtmlMail(reqVO.getTo(), reqVO.getSubject(), reqVO.getContent());
        RetVO<Object> retVO = new RetVO<>();
        retVO.setCode(SysEnum.SUCCESS_CODE);
        retVO.setMsg(SysEnum.SUCCESS_MSG);
        retVO.setData(reqVO);
        return retVO;
    }

    /**
     * @param reqVO
     * @return
     */
    @RequestMapping(value = "sendSimpleMail", method = RequestMethod.POST)
    public RetVO<Object> sendSimpleMail(@RequestBody ReqVO reqVO) {
        LOGGER.info("req:{}", JSON.toJSONString(reqVO));
        this.mailSenderService.sendSimpleMail(reqVO.getTo(), reqVO.getSubject(), reqVO.getContent());
        RetVO<Object> retVO = new RetVO<>();
        retVO.setCode(SysEnum.SUCCESS_CODE);
        retVO.setMsg(SysEnum.SUCCESS_MSG);
        retVO.setData(reqVO);
        return retVO;
    }
}
