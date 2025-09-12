package com.example.FoodExpress;
import com.example.FoodExpress.entity.UserEntity;
import com.example.FoodExpress.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;
@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private EntityManager entityManager;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testFindByEmail() {
        // Arrange: Create a user and persist it
        UserEntity user = new UserEntity();
        user.setEmail("test@example.com");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();

        // Act: Fetch the user by email
        UserEntity foundUser = userRepository.findByEmail("test@example.com");

        // Assert: Verify the user details
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getEmail()).isEqualTo("test@example.com");
        assertThat(foundUser.getPassword()).isEqualTo("password123");
    }

    @Test
    public void testInvalidLogin() {
        // Act: Try to fetch a non-existing user
        UserEntity foundUser = userRepository.findByEmail("nonexistent@example.com");

        // Assert: Verify that the user is not found
        assertThat(foundUser).isNull();
    }
}

