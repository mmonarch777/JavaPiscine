package classes;

public class Book {
    private String title;
    private String author;
    private Integer numberPages;

    public Book() {
        this.title = "Metro 2033";
        this.title = "Dmitry Glukhovsky";
        this.numberPages = 500;
    }
    public Book(String title, String author, Integer numberPages) {
        this.title = title;
        this.author = author;
        if (numberPages < 0) {
            numberPages *= -1;
        }
        this.numberPages = numberPages;
    }

    public double timeToRead() {
        return numberPages/15;
    }

    public void ripOutThePage() {
        if (numberPages > 0) {
            numberPages--;
        }
    }

    @Override
    public String toString() {
        return ("Book[title='" + title +
                "', author='" + author +
                "', numberPages='" + numberPages +
                "']");
    }
}
