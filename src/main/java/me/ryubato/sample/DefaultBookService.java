package me.ryubato.sample;

public class DefaultBookService implements BookService {
    @Override
    public void rentBook() {
        System.out.println("rent : book");
    }

    @Override
    public void returnBook() {
        System.out.println("return : book");
    }
}
