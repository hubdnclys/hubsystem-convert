package com.practice.spring_java.domain.employee.service;

import com.practice.spring_java.domain.employee.dto.request.VerifyEmailRequestDTO;
import com.practice.spring_java.domain.employee.dto.response.VerifyEmailResponseDTO;
import com.practice.spring_java.domain.employee.exception.EmailVerifyTimeExpiredException;
import com.practice.spring_java.global.util.MakeRandomNumber;
import com.practice.spring_java.global.util.ReadHtmlContentFromFile;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

@Service
@Transactional
@RequiredArgsConstructor
public class MailSendService {

    @Autowired
    private StringRedisTemplate redisTemplate;
    @Autowired
    private JavaMailSender mailSender;

    public void verifyMail(VerifyEmailRequestDTO verifyEmailRequestDTO) throws IOException {

        String verifyString = joinEmail(verifyEmailRequestDTO.email());
        //redis에 이메일 인증번호를 저장하고 인증 번호 발송
        redisTemplate.opsForValue().set("verifyString", verifyString, 3, TimeUnit.MINUTES);
    }

    public VerifyEmailResponseDTO checkVerifyMailCode(String code) {

        //reids에 저장된 인증번호와 사용자가 입력한 인증번호와 비교
        String storedVerifyString = redisTemplate.opsForValue().get("verifyString");
        if (storedVerifyString == null) {
            throw new EmailVerifyTimeExpiredException();
        }

        return new VerifyEmailResponseDTO(storedVerifyString.equals(code));
    }

    //mail을 어디서 보내는지, 어디로 보내는지 , 인증 번호를 html 형식으로 어떻게 보내는지 작성합니다.
    public String joinEmail(String email) throws IOException {
        String randomNumber = MakeRandomNumber.makeRandomNumber();
        String setFrom = "hubdnclystest@gmail.com"; // email-config에 설정한 자신의 이메일 주소를 입력
        String title = "회원 인증 이메일 입니다."; // 이메일 제목
        String content = ReadHtmlContentFromFile.readHtmlContentFromFile("src/main/resources/emailTemplate.html");
        content = content.replace("{randomNumber}", randomNumber);
        mailSend(setFrom, email, title, content);
        return randomNumber;
    }

    //이메일을 전송합니다.
    public void mailSend(String setFrom, String toMail, String title, String content) {
        MimeMessage message = mailSender.createMimeMessage();//JavaMailSender 객체를 사용하여 MimeMessage 객체를 생성
        try {
            MimeMessageHelper helper = new MimeMessageHelper(message, true, "utf-8");//이메일 메시지와 관련된 설정을 수행합니다.
            // true를 전달하여 multipart 형식의 메시지를 지원하고, "utf-8"을 전달하여 문자 인코딩을 설정
            helper.setFrom(setFrom);//이메일의 발신자 주소 설정
            helper.setTo(toMail);//이메일의 수신자 주소 설정
            helper.setSubject(title);//이메일의 제목을 설정
            helper.setText(content, true);//이메일의 내용 설정 두 번째 매개 변수에 true를 설정하여 html 설정으로한다.
            mailSender.send(message);
        } catch (MessagingException e) {//이메일 서버에 연결할 수 없거나, 잘못된 이메일 주소를 사용하거나, 인증 오류가 발생하는 등 오류
            // 이러한 경우 MessagingException이 발생
            throw new RuntimeException();
        }
    }
}

