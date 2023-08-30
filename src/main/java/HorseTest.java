import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.*;

import static org.junit.jupiter.api.Assertions.*;

public class HorseTest {
    @Test
    public void nullName(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 2.3));
        assertEquals("Name cannot be null.", exception.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {"", " ", "\t", "\n"})
    public void blankName(String str){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> {
            new Horse(str, 2.3);
        });
        assertEquals("Name cannot be blank.", exception.getMessage());
    }

    @Test
    public void minusSpeed(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bocik", -2.3));
        assertEquals("Speed cannot be negative.", exception.getMessage());
    }
    @Test
    public void minusDistance(){
        Throwable exception = assertThrows(IllegalArgumentException.class, () -> new Horse("Bocik", 2.3, -5));
        assertEquals("Distance cannot be negative.", exception.getMessage());
    }
    @Test
    public void getName() {
        Horse horse = new Horse("Bocik", 2.3);
        assertEquals("Bocik", horse.getName());
    }
    @Test
    public void getSpeed() {
        Horse horse = new Horse("Bocik", 2.3);
        assertEquals(2.3, horse.getSpeed());
    }
    @Test
    public void getDistanceThreeParams() {
        Horse horse = new Horse("Bocik", 2.3, 5);
        assertEquals(5, horse.getDistance());
    }
    @Test
    public void getDistanceTwoParams() {
        Horse horse = new Horse("Bocik", 2.3);
        assertEquals(0, horse.getDistance());
    }
    @Test
    public void getRandomDouble(){
        try(MockedStatic<Horse> obj = Mockito.mockStatic(Horse.class)){
            obj.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            Horse.getRandomDouble(0.2, 0.9);
            obj.verify(() -> Horse.getRandomDouble(0.2, 0.9));
        }catch (Exception e){
            e.printStackTrace();
        }
    }
    @Test
    public void move(){
        try(MockedStatic<Horse> obj = Mockito.mockStatic(Horse.class)){
            obj.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);

            Horse horse = new Horse("Bocik", 2.3);
            double startDistance = horse.getDistance();
            horse.move();

            double check = startDistance + horse.getSpeed() * Horse.getRandomDouble(0.2, 0.9);
            assertEquals(check, horse.getDistance());
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
