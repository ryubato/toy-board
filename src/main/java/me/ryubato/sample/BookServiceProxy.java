package me.ryubato.sample;

public class BookServiceProxy implements BookService {

    BookService bookService;

    public BookServiceProxy(BookService bookService) {
        this.bookService = bookService;
    }

    @Override
    public void rentBook() {
        System.out.println("prepare rent");
        bookService.rentBook();
        System.out.println("completed rent");
    }

    @Override
    public void returnBook() {
        System.out.println("prepare return");
        bookService.returnBook();
        System.out.println("completed return");
    }
}
