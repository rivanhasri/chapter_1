package aplikasi.pesanMakanan.modelTest;

import aplikasi.pesanMakanan.model.MenuItem;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;

import static org.junit.jupiter.api.Assertions.*;

public class MenuItemTest {
    private MenuItem menuItem;

    @BeforeEach
    public void setUp() {
        // Set up any common initialization code here.
        menuItem = new MenuItem("Nasi Goreng", 2, 10);
    }

    @AfterEach
    public void tearDown() {
        // Clean up or release any resources here.
        menuItem = null;
    }

    @Test
    public void testGetTotalPrice() {
        assertEquals(20, menuItem.getTotalPrice());
    }

    @Test
    public void testGetSpecialNote() {
        assertEquals("", menuItem.getSpecialNote());
    }

    @Test
    public void testSetSpecialNote() {
        menuItem.setSpecialNote("Pedas");
        assertEquals("Pedas", menuItem.getSpecialNote());
    }

    @Test
    public void testGetPrice() {
        assertEquals(10, menuItem.getPrice());
    }

    @Test
    public void testNegativeQty() {
        assertThrows(IllegalArgumentException.class, () -> {
            MenuItem menuItemWithNegativeQty = new MenuItem("Nasi Goreng", -1, 10);
        });
    }
}
