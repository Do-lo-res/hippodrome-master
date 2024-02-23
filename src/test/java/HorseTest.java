import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.MockedStatic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.mockStatic;

public class HorseTest {
    @Test
    public void nullAndNameIllegalArgumentExceptionTest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(null, 1,1));
        assertEquals("Name cannot be null.", e.getMessage());
    }

    @ParameterizedTest
    @ValueSource(strings = {""," ","    "})
    public void emptyLineAndSpacesTest(String name) {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse(name, 1,1));
        assertEquals("Name cannot be blank.", e.getMessage());
    }

    @Test
    public void speedIsNegativeNumberTest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("name", -1,1));
        assertEquals("Speed cannot be negative.", e.getMessage());
    }

    @Test
    public void distanceIsNegativeNumberTest() {
        IllegalArgumentException e = assertThrows(IllegalArgumentException.class, () -> new Horse("name", 1,-1));
        assertEquals("Distance cannot be negative.", e.getMessage());
    }

    @Test
    public void getNameTest() {
        Horse horse = new Horse("winner",1,1);
        assertEquals("winner",horse.getName());
    }

    @Test
    public void getSpeedTest() {
        double speed = 55;
        Horse horse = new Horse("winner",speed,1);
        assertEquals(speed,horse.getSpeed());
    }

    @Test
    public void getDistanceTest() {
        double distance = 55;
        Horse horse = new Horse("winner",1,distance);
        assertEquals(distance,horse.getDistance());
    }

    @Test
    public void zeroDistanceTest() {
        Horse horse = new Horse("winner",1);
        assertEquals(0,horse.getDistance());
    }

    @Test
    void getRandomWithMockAndMoveTest() {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
        new Horse("winner",55,42).move();
        mockedStatic.verify(() -> Horse.getRandomDouble(0.2,0.9));
        }
    }

    @ParameterizedTest
    @ValueSource(doubles = {0.6,8.1,3.4,0.999,5.5,7.67,99.76})
    void moveTest(double random) {
        try(MockedStatic<Horse> mockedStatic = mockStatic(Horse.class)) {
            Horse horse = new Horse("winner",55,345);
            mockedStatic.when(() -> Horse.getRandomDouble(0.2,0.9)).thenReturn(random);
            horse.move();
            assertEquals(345 + 55 * random, horse.getDistance());
        }
    }
}





