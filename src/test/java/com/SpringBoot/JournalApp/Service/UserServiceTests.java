package com.SpringBoot.JournalApp.Service;

import com.SpringBoot.JournalApp.repositor.UserRepository;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class UserServiceTests {

    @Autowired
    private UserRepository userRepository;


    @ParameterizedTest
    @CsvSource({
            "raj",
            "shyam",
            "vipul"
    })
    public void testFindByUserName(String name) {
        assertNotNull(userRepository.findByUserName(name), "failed for: " + name);
    }



    // Parameterized test that runs the method with multiple input sets from the CSV source
    @Disabled
    @ParameterizedTest
    @CsvSource({
            "1,1,2",
            "2,10,12",
            "3,3,9" // Intentional error? This actually adds up to 6, not 9.
    })
    public void testAddition(int a, int b, int expected) {
        assertEquals(expected, a + b); // Verifies sum of a and b matches expected value
    }
}
