package service;
import model.Book;
import java.util.HashMap;

public class BookService {
    HashMap<Book,Boolean> books ;

    public BookService(){
        books = new HashMap();
    }

    public void addBook(Book book){
        books.put(book,true);
    }
    public boolean isAvailable(Book book){
        return books.get(book);
    }
    public boolean findBook(String title){
        for(HashMap.Entry<Book,Boolean> book : books.entrySet()){
            Book book1 = book.getKey();
            if (book1.getTitle().equals(title)){
                return true;
            }
        }
        throw new RuntimeException("This book is not found");
    }
    public Book getBook(String title){
        for(HashMap.Entry<Book,Boolean> book : books.entrySet()){
            Book book1 = book.getKey();
            if (book1.getTitle().equals(title)){
                return book1;
            }
        }
        throw new RuntimeException("This book is not found");
    }
    public void deleteBook(Book book){
        books.put(book,false);
    }
}
