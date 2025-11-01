import model.Book;
import model.Member;
import service.BookService;
import service.MemberService;
import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    static Scanner input ;
    static MemberService memberService ;
    static BookService bookService ;
    static Book cleanCode , Algorithm;
    static Member User;
    public static void main(String[] args) {
        input = new Scanner(System.in);
        memberService = new MemberService();
        cleanCode = new Book("Clean Code","Robert C. Martin","9780132350884", LocalDate.of(2008, 8, 1));
        Algorithm = new Book("Effective Java","Joshua Bloch","9780134685991", LocalDate.of(2018, 1, 6));
        bookService =  new BookService();
        bookService.addBook(cleanCode);
        bookService.addBook(Algorithm);
        LoginOrRegisterMenu();
    }

    static void menu(){
        System.out.println("=========================================");
        System.out.println("Welcome to our library");
        System.out.println("=========================================");
        int sel = 0 ;
        do {
            System.out.println("1.Borrowing\n2.Returning\n3.Exit");
            sel = input.nextInt();
            input.nextLine();
            switch (sel){
                case 1:
                    Borrowing(User);
                    break;
                case 2:
                    Returning(User);
                    break;
                case 3:
                    System.out.println("Good Bye!");
                    sel = 0 ;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while (sel != 0);
    }
    static void LoginOrRegisterMenu(){
        System.out.println("=========================================");
        System.out.println("Login or Register");
        System.out.println("=========================================");
        int sel = 0 ;
        do {
            System.out.println("1.Register\n2.Login\n3.Exit");
            sel = input.nextInt();
            input.nextLine();
            switch (sel){
                case 1:
                    Register();
                    break;
                case 2:
                    User =  Login();
                    if (User != null){
                        menu();
                    }
                    else{
                        System.out.println("Login failed. try again");
                    }
                    break;
                case 3:
                    sel = 0 ;
                    break;
                default:
                    System.out.println("Invalid option");
            }
        }while (sel!=0);

    }
    static Member Login(){
        System.out.print("Enter your username : ");
        String username = input.nextLine();

        System.out.print("Enter your password : ");
        String password = input.nextLine();

        System.out.print("Enter your email : ");
        String email = input.nextLine();

        if (memberService.findMember(username, password, email)){
            System.out.println("Login successful");
            return memberService.getMember(username,password,email);
        }
        else{
            System.out.println("Invalid username or password");
            return null;
        }
    }
    static void Register(){
        System.out.print("Enter Your Username: ");
        String username = input.nextLine();

        System.out.print("Enter Your Password: ");
        String password = input.nextLine();

        System.out.print("Enter Your Email : ");
        String email = input.nextLine();

        System.out.print("Enter Your Phone : ");
        String phone = input.nextLine();

        System.out.print("Enter Your Address : ");
        String address = input.nextLine();

        if (memberService.findMember(username,password,email)){
            System.out.println("This Username Already Exists . Try Again");
            return;
        }
        memberService.addMember(new Member(username,password,email,phone,address));
    }
    static void Returning(Member member){
        System.out.print("Enter Book title : ");
        String title = input.nextLine();
        if (bookService.findBook(title)) {
            Book book = bookService.getBook(title);
            bookService.addBook(book);
            printReturnReceipt(member,book);
        }
    }
    static void Borrowing(Member member){
        System.out.print("Enter Book title : ");
        String title = input.nextLine();
        if (bookService.findBook(title)){
            Book book = bookService.getBook(title);
            if (bookService.isAvailable(book)){
                bookService.deleteBook(book);
                printBorrowReceipt(member,book);
            }
            else{
                throw new RuntimeException("Book is not available");
            }
        }
    }
    static void printBorrowReceipt(Member member,Book book) {
        System.out.println("=========================================");
        System.out.println("** Borrowing Receipt **");
        System.out.println("Member: " + member.getUsername());
        System.out.println("Book: " + book.getTitle());
        System.out.println("--------------------------");
        System.out.println("Book successfully borrowed!");
        System.out.println("=========================================");
        System.out.println();
    }
    static void printReturnReceipt(Member member , Book book) {
        System.out.println("=========================================");
        System.out.println("** Return Receipt **");
        System.out.println("Member: " + member.getUsername());
        System.out.println("Book: " + book.getTitle());
        System.out.println("--------------------------");
        System.out.println("Book returned successfully!");
        System.out.println("=========================================");
        System.out.println();
    }

}
