import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

public class HippodromeTest {
    @Test
    public void horsesNotBeNullTest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(null));
        assertEquals("Horses cannot be null.", e.getMessage());
    }
    @Test
    public void emptyHorsesException() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Hippodrome(new ArrayList<>()));
        assertEquals("Horses cannot be empty.", e.getMessage());
    }

    @Test
    public  void getHorsesTest() {
        List<Horse> horses = new ArrayList<>();
        for(int i = 1; i < 31; i++){
            horses.add(new Horse("horse"+i,10+i,15+i));
        }
        Hippodrome hippodrome = new Hippodrome(horses);
        assertEquals(horses,hippodrome.getHorses());
    }

    @Test
    public void moveTest() {
        List<Horse> horses = new ArrayList<>();
        for(int i = 0; i < 50; i++) {
            horses.add(mock(Horse.class));
        }
        new Hippodrome((horses)).move();
        for(Horse horse : horses) {
            verify(horse).move();
        }
    }

    @Test
    public void getWinnerTest() {
        Horse pegasus = new Horse("Pegasus",1,20);
        Horse bob = new Horse("Bob",1,10);
        Horse cherry = new Horse("Cherry",1,15);
        Horse lin = new Horse("Lin",1,22);
        Hippodrome hippodrome = new Hippodrome(List.of(pegasus, bob, cherry, lin));

        assertSame(lin, hippodrome.getWinner());

    }
}
