import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class HippodromeTest {
    @Test
    public void nullList(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", exception.getMessage());
    }
    @Test
    public void blankList(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", exception.getMessage());
    }
    @Test
    public void getHorses(){
        List<Horse> horses = Arrays.asList(
                new Horse("Kirill", 2),
                new Horse("Anton", 5),
                new Horse("Lui", 3),
                new Horse("Mo", 1)
        );

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses, hippodrome.getHorses());
    }
    @Test
    public void move(){
        List<Horse> horses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse horse = Mockito.mock(Horse.class);
            horses.add(horse);
        }

        Hippodrome hippodrome = new Hippodrome(horses);
        hippodrome.move();

        for (Horse horse : horses){
            Mockito.verify(horse).move();
        }
    }
    @Test
    public void getWinner(){
        List<Horse> horses = Arrays.asList(
                new Horse("Kirill", 2, 4),
                new Horse("Anton", 5, 7),
                new Horse("Lui", 3, 2),
                new Horse("Mo", 1, 6)
        );

        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(7, hippodrome.getWinner().getDistance());
    }
}
