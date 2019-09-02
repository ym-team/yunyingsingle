package com.cloud.backend.service;


import java.util.Map;

import com.cloud.util.Mail;
import com.cloud.util.Page;


public interface MailService {

    void saveMail(Mail mail);

    void updateMail(Mail mail);

    void sendMail(Mail mail);

    Mail findById(Long id);

    Page<Mail> findMails(Map<String, Object> params);
}
