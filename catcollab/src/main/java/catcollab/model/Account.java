package catcollab.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Account {

    @OneToMany(mappedBy = "account")
    private Set<Category> categories = new HashSet<>();

    @OneToMany(mappedBy = "account")
    private Set<Item> items = new HashSet<>();

    @Id
    @GeneratedValue
    private Long id;

    public Set<Category> getCategories() {
        return categories;
    }

    public Set<Item> getItems() {
        return items;
    }

    public void setItems(Set<Item> items) {
        this.items = items;
    }

    public Long getId() {
        return id;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return username;
    }

    @JsonIgnore
    public String password;
    public String username;

    public Account(String name, String password) {
        this.username = name;
        this.password = password;
    }

    Account() { // jpa only
    }
}


