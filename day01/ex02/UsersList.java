public interface UsersList {
    void add(User object);
    User retrieveById(Integer id) throws UserNotFoundException;
    User retrieveByIndex(Integer index) throws UserNotFoundException;
    Integer getAmountUsers();
}
