package org.example.thelooker;

import org.example.thelooker.model.Category;
import org.example.thelooker.repository.CategoryRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.TestPropertySource;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@TestPropertySource(locations = "classpath:application.properties")
public class CategoryRepositoryTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    void whenSaveCategory_thenRetrieveSameCategory() {
        // Create a new category
        Category newCategory = new Category();
        newCategory.setName("Electronics");
        // You can add more fields here based on your Category entity

        // Save the new category
        categoryRepository.save(newCategory);

        // Retrieve the category
        Optional<Category> retrievedCategory = categoryRepository.findById(newCategory.getId());

        // Validate the retrieved category
        assertThat(retrievedCategory).isPresent()
                .hasValueSatisfying(category ->
                        assertThat(category.getName()).isEqualTo("Electronics"));
        // Add more assertions if needed
    }
}

