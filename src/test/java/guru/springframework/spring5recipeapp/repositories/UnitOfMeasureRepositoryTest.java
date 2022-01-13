package guru.springframework.spring5recipeapp.repositories;

import guru.springframework.spring5recipeapp.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

// this will load the entire spring context and then we are testing value of teaspoon
@ExtendWith(SpringExtension.class)
@DataJpaTest
class UnitOfMeasureRepositoryTest {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    public void setUp(){

    }

    @Test
    //@DirtiesContext // this will result in reload of the context - just for ex
    void findByDescription() {

        Optional<UnitOfMeasure> unitOfMeasureOptional =
                unitOfMeasureRepository.findByDescription("Teaspoon");

        assertEquals("Teaspoon", unitOfMeasureOptional.get().getDescription());
    }

    @Test
    void findByDescriptionCup() {

        Optional<UnitOfMeasure> unitOfMeasureOptional =
                unitOfMeasureRepository.findByDescription("Cup");

        assertEquals("Cup", unitOfMeasureOptional.get().getDescription());
    }
}