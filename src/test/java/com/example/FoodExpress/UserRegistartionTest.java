package com.example.FoodExpress;
import com.example.FoodExpress.entity.UserEntity;
import com.example.FoodExpress.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import jakarta.persistence.EntityManager;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.useRepresentation;

@ExtendWith(SpringExtension.class)
@DataJpaTest

public class UserRegistartionTest {
    @Autowired
    private EntityManager entityManager;
    @Autowired
    private UserRepository userRepository;

    @Test
    public void testRegisterUser() {
        UserEntity user = new UserEntity();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPhoneNumber("1234567890");
        user.setPassword("password123");
        entityManager.persist(user);
        entityManager.flush();
        UserEntity foundUser = userRepository.findByEmail("testuser@example.com");
        assertThat(foundUser).isNotNull();
        assertThat(foundUser.getUsername()).isEqualTo("testuser");
        assertThat(foundUser.getEmail()).isEqualTo("testuser@example.com");
        assertThat(foundUser.getPhoneNumber()).isEqualTo("1234567890");
        assertThat(foundUser.getPassword()).isEqualTo("password123");
    }
    @Test
    public void testInvalidRegistration() {
        UserEntity foundUser = userRepository.findByEmail("nonexistent@example.com");
        assertThat(foundUser).isNull();
    }
}

