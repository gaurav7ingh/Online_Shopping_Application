

import com.shopping.model.User;
import com.shopping.exception.UserException;

public interface LogInService {
	
	public User addUser(User user) throws UserException;
	
	public User removeUser(User user) throws UserException;
	
	public User validateUser(User user) throws UserException;
	
	public User signOutUser(User user) throws UserException;
	
}
