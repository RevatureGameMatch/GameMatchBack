package com.revature.g2g.services.helpers;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lambdaworks.crypto.SCryptUtil;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PasswordHelper {
	public PasswordHelper() {
	}
	public String encryptPassword(String unencryptedPassword) {
		return SCryptUtil.scrypt(unencryptedPassword, 16384, 8, 1);
	}
	public boolean checkPassword(String unencryptedPassword, String passwordHash) {
		return SCryptUtil.check(unencryptedPassword, passwordHash);
	}
}
