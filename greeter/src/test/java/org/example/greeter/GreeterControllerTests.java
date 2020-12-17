package org.example.greeter;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.core.Is.is;
import static org.mockito.BDDMockito.given;

@SpringBootTest
@RunWith(SpringRunner.class)
public class GreeterControllerTests {

    @Test
    public void contextLoads() {
    }

    @MockBean
    DayTimeClient dayTimeClient;

    @Autowired
    GreeterController greeterController;

    @Test
    public void testGreeterControllerGreetsKarol() {
        given(this.dayTimeClient.getDayTimeText()).willReturn("morning");
        String actualGreeting = this.greeterController.greetPerson("Karol");
        Assert.assertThat(actualGreeting, is("Good morning Karol"));
        System.out.println("===================");
        System.out.println("Actual greeting as expected is: " + actualGreeting);
        System.out.println("===================");
    }

    @Test
    public void testGreeterControllerGreetsKarolAt02PM() {
        given(this.dayTimeClient.getDayTimeText("14")).willReturn("afternoon");
        String actualGreeting = this.greeterController.greetPerson("Karol", "14");
        Assert.assertThat(actualGreeting, is("Good afternoon Karol"));
        System.out.println("===================");
        System.out.println("Actual greeting as expected is: " + actualGreeting);
        System.out.println("===================");
    }

    @Test
    public void testGreeterControllerGreetsKarolAtWrongHourParam() {
        given(this.dayTimeClient.getDayTimeText("777")).willThrow(RuntimeException.class);
        String actualGreeting = null;
        try {
            actualGreeting = this.greeterController.greetPerson("Karol","777");
            Assert.fail("Expected to be thrown before reaching here");
        } catch (RuntimeException e) {
            // Success
        }
    }
}
