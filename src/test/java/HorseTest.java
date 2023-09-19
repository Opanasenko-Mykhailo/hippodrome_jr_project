import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mockStatic;

import org.junit.jupiter.api.Test;
import org.mockito.MockedStatic;

public class HorseTest {

    @Test
    public void testConstructor() {
        // Перевірка на null name
        assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5.0, 10.0));
        IllegalArgumentException nullNameException = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 5.0, 10.0));
        assertEquals("Name cannot be null.", nullNameException.getMessage());

        // Перевірка на порожній name і пробільні символи
        assertThrows(IllegalArgumentException.class, () -> new Horse("", 5.0, 10.0));
        assertThrows(IllegalArgumentException.class, () -> new Horse(" ", 5.0, 10.0));
        assertThrows(IllegalArgumentException.class, () -> new Horse("\t", 5.0, 10.0));
        assertThrows(IllegalArgumentException.class, () -> new Horse("\n", 5.0, 10.0));

        IllegalArgumentException blankNameException = assertThrows(IllegalArgumentException.class, () -> new Horse("", 5.0, 10.0));
        assertEquals("Name cannot be blank.", blankNameException.getMessage());

        // Перевірка на від'ємну швидкість і відстань
        assertThrows(IllegalArgumentException.class, () -> new Horse("SomeName", -5.0, 10.0));
        assertThrows(IllegalArgumentException.class, () -> new Horse("SomeName", 5.0, -10.0));

        IllegalArgumentException negativeSpeedException = assertThrows(IllegalArgumentException.class, () -> new Horse("SomeName", -5.0, 10.0));
        assertEquals("Speed cannot be negative.", negativeSpeedException.getMessage());

        IllegalArgumentException negativeDistanceException = assertThrows(IllegalArgumentException.class, () -> new Horse("SomeName", 5.0, -10.0));
        assertEquals("Distance cannot be negative.", negativeDistanceException.getMessage());
    }
    @Test
    public void testGetName(){
        String name = "TEsT";
        Horse horse = new Horse(name, 1,1);
        String result = horse.getName();
        assertEquals(name, result);
    }
    @Test
    public void testGetSpeed(){
        double speed = 1.74;
        Horse horse = new Horse("TEsT", speed,1);
        double result = horse.getSpeed();
        assertEquals(speed, result);
    }
    @Test
    public void testGetDistance(){
        double distance = 45.9;
        Horse horse = new Horse("TEsT", 1,distance);
        double result = horse.getDistance();
        assertEquals(distance, result);
    }
    @Test
    public void testMove() {
        String name = "SomeName";
        double speed = 5.0;
        double distance = 10.0;

        Horse horse = new Horse(name, speed, distance);

        try (MockedStatic<Horse> horseMock = mockStatic(Horse.class)) {
            horseMock.when(() -> Horse.getRandomDouble(0.2, 0.9)).thenReturn(0.5);
            horse.move();
            horseMock.verify(() -> Horse.getRandomDouble(0.2, 0.9));
            double expectedDistance = distance + speed * 0.5;
            assertEquals(expectedDistance, horse.getDistance());
        }
    }
}