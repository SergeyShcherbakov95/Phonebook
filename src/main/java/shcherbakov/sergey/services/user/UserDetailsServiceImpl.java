package shcherbakov.sergey.services.user;

import java.util.Collection;
import java.util.LinkedList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import shcherbakov.sergey.dao.user.UserDao;
import shcherbakov.sergey.model.User;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserDao userDao;
	
	public void setUserDao(UserDao userDao){
		this.userDao = userDao;
	}
	
    @Transactional
	@Override
	public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
		User user = this.userDao.getUser(login);
		if (user == null)
			throw new UsernameNotFoundException("User with given login " + login + " isn't found. ");

		return new UserDetailsImpl(user);
	}

	private static class UserDetailsImpl extends User implements UserDetails {
		
		private static final long serialVersionUID = 1L;
		
		public UserDetailsImpl(User user){
			super(user.getLogin(), user.getUserPassword(), user.getFullName());
		}
		
		@Override
		public Collection<? extends GrantedAuthority> getAuthorities() {
			Collection<GrantedAuthority> authorities = new LinkedList<GrantedAuthority>();
			authorities.add(new SimpleGrantedAuthority("USER"));
			return authorities;
		}

		@Override
		public String getUsername() { return getLogin(); }
		
		@Override
		public String getPassword() { return getUserPassword(); }

		@Override
		public boolean isAccountNonExpired() { return true; }

		@Override
		public boolean isAccountNonLocked() { return true; }

		@Override
		public boolean isCredentialsNonExpired() { return true; }

		@Override
		public boolean isEnabled() { return true; }
	}
}
