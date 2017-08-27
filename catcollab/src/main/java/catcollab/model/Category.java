package catcollab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Category {

    @JsonIgnore
    @ManyToOne
    private Account account;

    @OneToMany(mappedBy = "category")
    private Set<Item> items = new HashSet<>();


    @Id
    @GeneratedValue
    private Long id;

    Category() { // jpa only
    }

    public Category(Account account, String name, String description) {
        this.name = name;
        this.description = description;
        this.account = account;
    }

    public String name;
    public String description;

    public Account getAccount() {
        return account;
    }

    public Set<Item> getItems() {
        return items;
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