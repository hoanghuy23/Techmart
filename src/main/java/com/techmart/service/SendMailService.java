package com.techmart.service;

import com.techmart.model.Account;

public interface SendMailService {
	void SendEmail(Account account, String url);
}
