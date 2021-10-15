package ProjectC.Gold.Note.SpringIOCV1.IOCTestChapter03.pojo;

import java.util.List;

public class Person {
    private String name;
    private Phone phone;
    private Car car;
    private List<Book> books;
    private double salary;
    private String email;
    private String favoriteBook;

    public Person() {
        super();
    }

    public Person(Car car) {
        this.car = car;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Phone getPhone() {
        return phone;
    }

    public void setPhone(Phone phone) {
        this.phone = phone;
    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFavoriteBook() {
        return favoriteBook;
    }

    public void setFavoriteBook(String favoriteBook) {
        this.favoriteBook = favoriteBook;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", phone=" + phone +
                ", car=" + car +
                ", books=" + books +
                ", salary=" + salary +
                ", email='" + email + '\'' +
                ", favoriteBook='" + favoriteBook + '\'' +
                '}';
    }
}
