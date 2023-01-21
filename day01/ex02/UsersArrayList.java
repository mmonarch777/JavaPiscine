package day01.ex02;

public class UsersArrayList implements UsersList{

    private static final UsersArrayList LIST = new UsersArrayList();
    private User[] array;
    private Integer amountUsers;

    private UsersArrayList() {
        array = new User[10];
        amountUsers = 0;
    }

    public static UsersArrayList getUserList(){
        return LIST;
    }

    @Override
    public void addUser(User user) {
        if (user == null) {
            return;
        }
        int i = 0;
        if (array.length == getAmountUsers()) {
            User[] mass = new User[array.length*2];
            for (; i < array.length; i++) {
                mass[i] = array[i];
            }
            array = mass;
        }
        for(; i < array.length; i++) {
            if (array[i] != null && array[i].getId().equals(user.getId())) {
                System.out.println("User already inside");
                return;
            }
            if (array[i] == null) {
                array[i] = user;
                break;
            }
        }
        ++amountUsers;
    }

    @Override
    public User getUserById(Integer id) {
        int len = getAmountUsers();
        for (int i = 0; i < len; i++) {
            if (array[i].getId().equals(id)) {
                return array[i];
            }
        }
        throw new UserNotFoundException(id + " : wrong id");
    }

    @Override
    public User getUserByIndex(Integer index) throws UserNotFoundException {
        if (index < 0 || index >= array.length) {
            throw new UserNotFoundException(index + " : index out of bounds..");
        }
        return array[index];
    }

    @Override
    public int getAmountUsers() {

        return amountUsers;
    }

    public int getLength() {
        return array.length;
    }

    public void printUsers() {
        for (User user : array) {
            if (user == null) {
                break;
            }
            System.out.println(user);
        }
    }
}

class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String message) {
        super(message);
    }
}
