package day01.ex04;

import java.util.UUID;

public interface UsersList {
    public void addUser(User user);
    public User getUserById(UUID id) throws UserNotFoundException;
    public User getUserByIndex(Integer index) throws UserNotFoundException;
    public int getAmountUsers();
}
