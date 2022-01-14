package guru.springframework.spring5recipeapp.domain;

import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Data
@Entity
public class Recipe {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // how Id should be generated
    private Long id;

    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private Integer servings;
    private String source;
    private String url;

    @Lob
    private String directions;

    // EnumType.ORDINAL -> it stores in 0, 1 , 2 index order of enum string
    // problem if we change position it will screw up database values
    // thus we are using EnumType.STRING to store actual value
    @Enumerated(value = EnumType.STRING)
    private Difficulty difficulty;

    // blob field bigger object
    @Lob
    private Byte[] image;

    // relationship OneToOne
    @OneToOne(cascade = CascadeType.ALL) //this defines the relationship
    private Notes notes;

    // If we remove @JoinTable we will get two table Category_recipes and Recipe_Categories table
    // JoinTable tells hibernate to add single table and map both , as one table is enough
    @JoinTable(name = "recipe_category", // table name to create
            joinColumns = @JoinColumn(name = "recipe_id"), // join column
            inverseJoinColumns = @JoinColumn(name = "category_id") // opposite join column
    )
    @ManyToMany
    private Set<Category> categories = new HashSet<>();

    // each recipe will have many ingredients, if ingredient deleted recipe deleted
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "recipe")
    private Set<Ingredient> ingredients = new HashSet<>();

    public void setNotes(Notes notes) {
        if (notes != null) {
            this.notes = notes;
            notes.setRecipe(this);
        }

    }

    public Recipe addIngredient(Ingredient ingredient){
        ingredient.setRecipe(this);
        this.ingredients.add(ingredient);
        return this;
    }
}
