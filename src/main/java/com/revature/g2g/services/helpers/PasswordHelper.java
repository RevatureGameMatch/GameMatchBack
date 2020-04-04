package com.revature.g2g.services.helpers;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lambdaworks.crypto.SCryptUtil;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PasswordHelper {
	private PasswordHelper() {
	}
	public static String encryptPassword(String unencryptedPassword) {
		return SCryptUtil.scrypt(unencryptedPassword, 16384, 8, 1);
	}
	public static boolean checkPassword(String unencryptedPassword, String passwordHash) {
		return SCryptUtil.check(unencryptedPassword, passwordHash);
	}
}
