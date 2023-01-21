package classes;

public class Dog {
    private String name;
    private Integer age;
    private Boolean homeless;

    public Dog() {
        this.name = "Silver";
        this.age = 5;
        this.homeless = true;
    }
    public Dog(String name, Integer age, Boolean homeless) {
        this.name = name;
        this.age = age;
        this.homeless = homeless;
    }

    public String mealsPerDay(int number) {
        if (number > 3) {
            return "eee boy";
        } else {
            return "it's not serious";
        }
    }

    public void voice() {
        System.out.println("gav");
    }

    @Override
    public String toString() {
        return ("Dog[name='" + name +
                "', age='" + age +
                "', homeless='" + homeless +
                "']");
    }
}
