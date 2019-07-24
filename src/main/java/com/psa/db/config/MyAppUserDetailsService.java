/*package com.psa.db.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.psa.db.dao.UserProfileDao;
import com.psa.db.entity.UserProfile;



@Service
public class MyAppUserDetailsService implements UserDetailsService {
	@Autowired
	private UserProfileDao userDao;
	@Override
	public UserDetails loadUserByUsername(String userId)
			throws UsernameNotFoundException {
		UserProfile activeUserInfo = userDao.getUserById(userId);
		GrantedAuthority authority = new SimpleGrantedAuthority(activeUserInfo.getRole());
		UserDetails userDetails = (UserDetails)new User(activeUserInfo.getUserId(),
				activeUserInfo.getAuthCode(), Arrays.asList(authority));
		return userDetails;
	}
}*/