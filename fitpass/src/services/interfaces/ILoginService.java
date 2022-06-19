package services.interfaces;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import beans.dtos.UserLoginDTO;
import beans.models.User;

public interface ILoginService {

	public User login(UserLoginDTO userLoginDTO, Collection<User> users);
	public boolean logout(@Context HttpServletRequest request);
}
