package guru.springframework.spring5recipeapp.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Set;


@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipes"}) // it will exclude equals and hash code of recipes
@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String description;

    // need to tell after adding of @JoinTable this is mapped by categories
    @ManyToMany(mappedBy = "categories")
    private Set<Recipe> recipes;

    public Category() {
    }

    protected boolean canEqual(final Object other) {
        return other instanceof Category;
    }

}
