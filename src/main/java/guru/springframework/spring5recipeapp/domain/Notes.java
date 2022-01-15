package guru.springframework.spring5recipeapp.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@EqualsAndHashCode(exclude = {"recipe"}) // for recipe param ignore equalsAndHashCode
@Entity
public class Notes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // recipe will own this
    // -> if delete notes object we don't want to delete recipe,
    // inverse is true
    @OneToOne
    private Recipe recipe;

    // To help store large value more than 256 chars
    @Lob
    private String recipeNotes;

}
