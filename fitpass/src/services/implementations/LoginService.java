package services.implementations;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import beans.dtos.UserLoginDTO;
import beans.models.User;
import services.interfaces.ILoginService;

public class LoginService implements ILoginService {

	@Override
	public User login(UserLoginDTO userLoginDTO, Collection<User> users) {
		// TODO Auto-generated method stub
		return null;
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
