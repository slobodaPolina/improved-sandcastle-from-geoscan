package config;

import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import dao.UserDao;
import entity.User;

@Component
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserDao userDao;
	
	@Override
	public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {
		User myUser = userDao.getByName(name);
		if (myUser == null) {
			throw new UsernameNotFoundException("");
		}
		org.springframework.security.core.userdetails.User securityUser 
				= new org.springframework.security.core.userdetails.User(
						myUser.getLogin(), myUser.getPassword(), Arrays.asList(new SimpleGrantedAuthority("USER"))
						);
		return securityUser;
	}

}
