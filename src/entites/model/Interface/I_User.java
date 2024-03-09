package entites.model.Interface;

import java.util.Scanner;

import entites.model.repository.User_Repository;

public interface I_User {
	void register(Scanner sc, User_Repository userR);

	int options(Scanner sc2, User_Repository userR);

}
