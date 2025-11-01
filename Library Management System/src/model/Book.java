package model;

import java.time.LocalDate;
import java.util.List;
import java.util.regex.Pattern;

public class Book {
    private String Title;
    private String Author;
    private String ISBN;
    private LocalDate PublishedDate;
    public Book(){

    }

    public Book(String Title, String Author, String ISBN, LocalDate PublishedDate){
        setTitle(Title);
        setAuthor(Author);
        setISBN(ISBN);
        setPublishedDate(PublishedDate);
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        String trimmed = (title == null) ? "" : title.trim();
        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Title cannot be empty");
        }
        if (trimmed.length() < 2 || trimmed.length() > 100) {
            throw new IllegalArgumentException("Title must be between 2 and 100 characters");
        }
        this.Title = trimmed;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        String trimmed = (author == null) ? "" : author.trim();
        Pattern authorPattern = Pattern.compile("^[\\p{L}\\s'.-]+$");

        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("Author name cannot be empty");
        }
        if (!authorPattern.matcher(trimmed).matches()) {
            throw new IllegalArgumentException("Author name contains invalid characters");
        }
        this.Author = trimmed;
    }

    public String getISBN() {
        return ISBN;
    }

    public void setISBN(String ISBN) {
        String trimmed = (ISBN == null) ? "" : ISBN.trim();
        Pattern isbnPattern = Pattern.compile("^(97(8|9))?\\d{9}(\\d|X)$|^(97(8|9))-?\\d{1,5}-?\\d{1,7}-?\\d{1,7}-?[\\dX]$");

        if (trimmed.isEmpty()) {
            throw new IllegalArgumentException("ISBN cannot be empty");
        }
        if (!isbnPattern.matcher(trimmed).matches()) {
            throw new IllegalArgumentException("Invalid ISBN format");
        }
        this.ISBN = trimmed;
    }


    public LocalDate getPublishedDate() {
        return PublishedDate;
    }

    public void setPublishedDate(LocalDate publishedDate) {
        if (publishedDate == null) {
            throw new IllegalArgumentException("Published date cannot be null");
        }
        if (publishedDate.isAfter(LocalDate.now())) {
            throw new IllegalArgumentException("Published date cannot be in the future");
        }
        this.PublishedDate = publishedDate;
    }



    @Override
    public String toString() {
        return "Book{" +
                "Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", ISBN='" + ISBN + '\'' +
                ", PublishedDate=" + PublishedDate +
                '}';
    }
}
