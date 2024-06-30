package pl.lukasz.CarRentalManager.entities;

import jakarta.persistence.*;

import java.math.*;
import java.time.*;
import java.time.format.DateTimeFormatter;

@Entity
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private Client client;

    @ManyToOne
    private Car car;

    private BigDecimal amount;
    private LocalDate issueDate;
    private Status status;
    private boolean isArchived;

    public Invoice() {
        status = Status.Paid;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    //("yyyy-MM-dd")-uniwersalny format
    //dotyczy tylko dat
    //sprring nie odczytuje LocalDate
    public String getIssueDateDisplayValue() {
        return issueDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    public void setIssueDateDisplayValue(String value) {
        this.issueDate = LocalDate.parse(value);
    }

    public boolean isArchived() {
        return isArchived;
    }

    public void setArchived(boolean archived) {
        isArchived = archived;
    }
}