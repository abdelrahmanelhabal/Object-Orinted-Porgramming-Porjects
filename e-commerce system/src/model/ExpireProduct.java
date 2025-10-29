package model;
import java.time.LocalDate ;

public class ExpireProduct extends Product {
    protected LocalDate expireDate;

    public ExpireProduct() {

    }

    public ExpireProduct(String name, double price, int quantity, LocalDate expireDate) {
        super(name, price, quantity);
        setExpireDate(expireDate);
    }

    public LocalDate getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(LocalDate expireDate) {
        if (expireDate == null) {
            throw new IllegalArgumentException("Expire date cannot be null");
        }
        if (expireDate.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Expire date cannot be in the past");
        }
        this.expireDate = expireDate;
    }
    public boolean isExpired() {
        return expireDate.isBefore(LocalDate.now());
    }

    @Override
    public String toString() {
        return String.format(
                "ExpireProduct{name='%s', price=%.2f, quantity=%d, expireDate=%s}",
                name , price , quantity , expireDate
        );
    }
}
