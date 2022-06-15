package services.implementations;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import beans.dtos.UserLoginDTO;
import beans.models.User;
import services.interfaces.ILoginService;

public class LoginService implements ILoginService {

	@Override
	public User login(UserLoginDTO userLoginDTO, Collection<User> users) {
		User user = null;
		for (User u : users) {
			if(u.getUsername().compareTo(userLoginDTO.getUsername()) == 0 && u.getPassword().compareTo(userLoginDTO.getPassword())== 0)
			{
				user = u;
				return user;
			}
		}
		return user;
	}

	@Override
	public boolean logout(HttpServletRequest request) {
		User user = null;
		user = (User) request.getSession().getAttribute("user");
		if (user != null) {
			request.getSession().invalidate();
		}
		return true;
	}

}
