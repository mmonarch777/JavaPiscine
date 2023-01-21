package day01.ex02;

public class UserIdsGenerator {
    private static final UserIdsGenerator GENERATOR = new UserIdsGenerator();
    private Integer count;
    private UserIdsGenerator() {
        this.count = 1;
    }

    public static UserIdsGenerator getGenerator() {
        return GENERATOR;
    }

    public Integer generateId() {
        return count++;
    }
}
