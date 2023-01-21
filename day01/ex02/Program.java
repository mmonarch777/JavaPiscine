package day01.ex02;
public class Program {
    public static void main(String[] args) {

            UsersArrayList list = UsersArrayList.getUserList();
        System.out.println(list.getLength());
            for (int i = 0; i < 15; i++) {
                list.addUser(new User("Mikle", 1000));
            }
            System.out.println(list.getLength());
            System.out.println(list.getAmountUsers());
            System.out.println(list.getUserByIndex(19));;
            System.out.println(list.getUserByIndex(11));

            UsersArrayList list2 = UsersArrayList.getUserList();
            System.out.println(list2.getUserByIndex(8));

            System.out.println(list.getUserByIndex(-9));

    }
}
