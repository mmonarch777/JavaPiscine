package day01.ex02;

public interface UsersList {
    public void addUser(User user);
    public User getUserById(Integer id) throws UserNotFoundException;
    public User getUserByIndex(Integer index) throws UserNotFoundException;
    public int getAmountUsers();
}
