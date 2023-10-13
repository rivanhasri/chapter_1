package aplikasi.pesanMakanan.viewTest;

import aplikasi.pesanMakanan.view.impl.MenuViewImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MenuViewTest {
    private MenuViewImpl menuView;

    @BeforeEach
    public void setUp() {
        menuView = new MenuViewImpl();
    }

    @Test
    public void testRepeatedChar() {
        // Test the repeatedChar method.
        String repeated = menuView.repeatedChar("=", 5);
        assertEquals("======", repeated);
        System.out.println("Test berhasil!");
    }

    @Test
    public void testUserChoice() {
        // Test userChoice method by providing an input stream.
        String input = "3\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int choice = menuView.userChoice();
        assertEquals(3, choice);

        System.out.println("Test berhasil!");
    }

    @Test
    public void testUserQty() {
        // Test userQty method by providing an input stream.
        String input = "2\n";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);

        int qty = menuView.userQty();
        assertEquals(2, qty);

        System.out.println("Test berhasil!");
    }
}
