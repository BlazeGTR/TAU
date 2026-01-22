package pl.pjatk.Zad1;

import org.junit.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Zad1ApplicationTests {

    @Autowired
    private SortController sortController;

    @Before
    public void before(){
        System.out.println("Before");
    }

    @After
    public void after(){
        System.out.println("After");
    }

    @Test
    public void addTest(){
        assertEquals(5,2+3);
    }

	@Test
	public void testSort(){
        int[] start = {5, 2, 1, 3};
        int[] end = {1, 2, 3, 5};

        int[] wynik = sortController.sortuj(start);
        assertArrayEquals(end,wynik);
    }

    @Test
    public void testSort2(){
        int[] start = {5, 2, 1, 3};
        int[] end = {1, 2, 3, 5};

        int[] wynik = sortController.sortuj(start);

        assertFalse(Arrays.equals(start, wynik));
    }

}
