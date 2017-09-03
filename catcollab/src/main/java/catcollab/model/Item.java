package catcollab.model;

import catcollab.model.Account;
import catcollab.model.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Item {
    @JsonIgnore
    @ManyToOne
    private Category category;

    @JsonIgnore
    @ManyToOne
    private Account account;

    @Id
    @GeneratedValue
    private Long id;

    Item() { // jpa only
    }

    public Item(Account account, Category category, String name, String description) {
        this.name = name;
        this.description = description;
        this.account = account;
        this.category = category;
    }

    private String name;
    private String description;

    public Category getCategory() {
        return category;
    }

    public Account getAccount() {
        return account;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}