package guru.springframework.spring5recipeapp.services;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class RecipeServiceImplTest {


    // service that we are testing
    RecipeService recipeService;

    // we need repository service
    @Mock
    RecipeRepository recipeRepository;

    @BeforeEach
    public void setUp() throws Exception {
        MockitoAnnotations.openMocks(this); // give me a mock recipe repository

        recipeService = new RecipeServiceImpl(recipeRepository);
    }

    @Test
    void getRecipes() {

        // mock to return a set
        Recipe recipe = new Recipe();
        HashSet recipeData = new HashSet();
        recipeData.add(recipe);

        Mockito.when(recipeRepository.findAll()).thenReturn(recipeData);

        Set<Recipe> recipes = recipeService.getRecipes();

        assertEquals(recipes.size(), 1);

        // verify findAll() method is called once
        Mockito.verify(recipeRepository, Mockito.times(1)).findAll();
    }
}