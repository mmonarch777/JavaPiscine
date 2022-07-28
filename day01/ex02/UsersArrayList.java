public class UsersArrayList implements UsersList{
    private static final int MAX_NUMBERS_USERS = 10;
    private User array[];
    private Integer usersAmount;

    public UsersArrayList() {
        this.array = new User[MAX_NUMBERS_USERS];
        this.usersAmount = 0;
    }

    @Override
    public void add(User user) {
        if (array.length == usersAmount) {
            User[] newArray = new User[usersAmount * 2];
            for (int i = 0; i < array.length; i++) {
                newArray[i] = array[i];
            }
            array = newArray;
        }
        array[usersAmount] = user;
        usersAmount++;
    }

    @Override
    public Integer getAmountUsers() {
        return this.usersAmount;
    }

    @Override
    public User retrieveById(Integer id) throws UserNotFoundException {
        for (int i = 0; i < usersAmount; i++) {
            if (id.equals(array[i].getIdentifier())) {
                return array[i];
            }
        }
        throw new UserNotFoundException("ID", id);
    }

    @Override
    public User retrieveByIndex(Integer index) throws UserNotFoundException {
        if (index < 0 || index > usersAmount)
            throw new UserNotFoundException("INDEX", index);
        return array[index];
    }
}

class UserNotFoundException extends Exception {
    UserNotFoundException(String str, Integer num) {
        System.out.println("User " + str + ": " + num + " doesn't exist");
    }
}