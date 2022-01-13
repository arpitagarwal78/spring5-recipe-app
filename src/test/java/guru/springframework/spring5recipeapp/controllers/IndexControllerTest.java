package guru.springframework.spring5recipeapp.controllers;

import guru.springframework.spring5recipeapp.domain.Recipe;
import guru.springframework.spring5recipeapp.services.RecipeService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MockMvcBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.ui.Model;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class IndexControllerTest {

    @Mock
    RecipeService recipeService;

    @Mock
    Model model;

    IndexController controller;

    @BeforeEach
    public void setUp(){
        MockitoAnnotations.openMocks(this);

        controller = new IndexController(recipeService);
    }

    @Test
    void getIndexPage() {
        // Argument captor to verify the set
        // given
        Set<Recipe> recipes = new HashSet<>();

        Recipe recipeOne = new Recipe();
        recipeOne.setId(1L);
        recipes.add(recipeOne);

        Recipe recipeTwo = new Recipe();
        recipeTwo.setId(2L);
        recipes.add(recipeTwo);

        Mockito.when(recipeService.getRecipes()).thenReturn(recipes);

        ArgumentCaptor<Set<Recipe>> argumentCaptor = ArgumentCaptor.forClass(Set.class);

        // when
        String viewName = controller.getIndexPage(model);

        // then
        assertEquals("index", viewName);

        Mockito.verify(recipeService, Mockito.times(1)).getRecipes();
        Mockito.verify(model, Mockito.times(1))
                .addAttribute(ArgumentMatchers.eq("recipes"), argumentCaptor.capture());

        Set<Recipe> setInController = argumentCaptor.getValue();

        assertEquals(2, setInController.size());
    }

    @Test
    public void testMockMVC() throws Exception {
        // this is mock servlet context, it gives a lot of things
        // preferred way of testing
        MockMvc mockMvc = MockMvcBuilders.standaloneSetup(controller).build();

        mockMvc.perform(MockMvcRequestBuilders.get("/"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.view().name("index"));
    }
}