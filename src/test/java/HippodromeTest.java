import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void testConstructor() {
// Перевірка конструктора з null параметром
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        IllegalArgumentException nullException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", nullException.getMessage());

        // Перевірка конструктора з порожнім списком
        List<Horse> emptyList = new ArrayList<>();
        assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
        IllegalArgumentException emptyException = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(emptyList));
        assertEquals("Horses cannot be empty.", emptyException.getMessage());

        // Перевірка конструктора з непорожнім списком
        List<Horse> horses = new ArrayList<>();
        horses.add(new Horse("Horse1", 10.0, 100.0));
        horses.add(new Horse("Horse2", 12.0, 120.0));
        Hippodrome hippodrome = new Hippodrome(horses);

        // Перевірка, що конструктор ініціалізує об'єкт Hippodrome з переданим списком коней
        assertEquals(horses, hippodrome.getHorses());
    }
    @Test
    public void testGetHorses() {
        List<Horse> originalHorses = new ArrayList<>();
        for (int i = 1; i <= 30; i++) {
            originalHorses.add(new Horse("Horse " + i, 10.0, 100.0));
        }
        Hippodrome hippodrome = new Hippodrome(originalHorses);
        List<Horse> returnedHorses = hippodrome.getHorses();
        assertEquals(originalHorses, returnedHorses);
    }
    @Test
    public void testMove() {
        List<Horse> mockHorses = new ArrayList<>();
        for (int i = 0; i < 50; i++) {
            Horse mockHorse = Mockito.mock(Horse.class);
            mockHorses.add(mockHorse);
        }
        Hippodrome hippodrome = new Hippodrome(mockHorses);
        hippodrome.move();
        for (Horse mockHorse : mockHorses) {
            verify(mockHorse).move();
        }
    }
    @Test
    public void testGetWinner() {
        Horse horse1 = new Horse("Horse1", 10.0, 100.0);
        Horse horse2 = new Horse("Horse2", 15.0, 150.0);
        Horse horse3 = new Horse("Horse3", 12.0, 120.0);

        List<Horse> horses = new ArrayList<>();
        horses.add(horse1);
        horses.add(horse2);
        horses.add(horse3);
        Hippodrome hippodrome = new Hippodrome(horses);
        Horse expectedWinner = horse2;

        Horse actualWinner = hippodrome.getWinner();
        assertEquals(expectedWinner, actualWinner);
    }
}
