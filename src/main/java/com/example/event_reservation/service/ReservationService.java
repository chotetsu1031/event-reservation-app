package com.example.event_reservation.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.example.event_reservation.model.User;

@Service
public class ReservationService {

	@Autowired
	private JavaMailSender mailSender;

	public void sendConfirmationMail(User user) {
	    SimpleMailMessage message = new SimpleMailMessage();
	    message.setTo(user.getMailaddress());
	    message.setSubject("イベント予約完了のお知らせ");
	    message.setText(user.getUsername() + "さん\n\nイベントの予約が完了しました。\n当日お会いできるのを楽しみにしております。");
	    message.setFrom("a17005t@reitaku.jp");
	    mailSender.send(message);
	}
}
