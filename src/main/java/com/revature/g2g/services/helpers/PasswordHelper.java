package com.revature.g2g.services.helpers;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.lambdaworks.crypto.SCryptUtil;

@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_SINGLETON)
public class PasswordHelper {
	/**
	 * Takes unencrypted password and returns hashed password with appended salt
	 * @param unencryptedPassword
	 * @return String hashOfPassword
	 */
	public String encryptPassword(String unencryptedPassword) {
		return SCryptUtil.scrypt(unencryptedPassword, 16384, 8, 1);
	}
	/**
	 * Takes unencrypted password and hash of password and checks for match
	 * @param String unencryptedPassword
	 * @param String passwordHash
	 * @return boolean
	 */
	public boolean checkPassword(String unencryptedPassword, String passwordHash) {
		return SCryptUtil.check(unencryptedPassword, passwordHash);
	}
}
