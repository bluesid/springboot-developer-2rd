import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import static org.assertj.core.api.Assertions.assertThat;

public class JUnitQuiz {
    @Test
    public void junitTest() {
        String name1 = "홍길동";
        String name2 = "홍길동";
        String name3 = "홍길동";

        assertThat(name1).isNotNull();
        assertThat(name2).isNotNull();
        assertThat(name3).isNotNull();

        assertThat(name1).isEqualTo(name2);

        assertThat(name1).isNotEqualTo(name3);
    }

    @Test
    public void junitTest2() {
        int number1 = 15;
        int number2 = 0;
        int number3 = -5;

        assertThat(number1).isPositive();
        assertThat(number2).isZero();


        assertThat(number3).isNegative();
        assertThat(number1).isGreaterThan(number2);
        assertThat(number1).isLessThan(number3);
    }

    @BeforeEach
    public void hello() {
        System.out.println("Hello!");
    }

    @AfterAll
    public static void bye() {
        System.out.println("Bye!");
    }

    @Test
    public void junitTest3() {
        System.out.println("This is first test");
    }

    @Test
    public void junitTest4() {
        System.out.println("This is second test");
    }
}
