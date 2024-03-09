package entites.model.repository;

import java.util.ArrayList;
import java.util.List;

import entites.model.User;

public class User_Repository {
	private List<User> userList = new ArrayList<>();
	private List<User> listWait = new ArrayList<>();
	
	public User_Repository() {
		
	}
	
	public void addList(User user) {
		userList.add(user);
	}
	public void addlistWait(User user) {
		listWait.add(user);
	}

	public List<User> getUserList() {
		return userList;
	}

	public List<User> getUserListWait(){
		return listWait;
	}

	
	
}
