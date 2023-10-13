package aplikasi.pesanMakanan.controllerTest;

import aplikasi.pesanMakanan.controller.impl.MenuControllerImpl;
import aplikasi.pesanMakanan.model.MenuItem;
import aplikasi.pesanMakanan.view.impl.MenuViewImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class MenuControllerTest {
    private MenuControllerImpl menuController;

    @BeforeEach
    public void setUp() {
        menuController = new MenuControllerImpl();
    }

    @AfterEach
    public void tearDown() {
        // Perform any necessary cleanup here.
        menuController = null;
    }

    @Test
    public void testHandleConfirmationChoice() {
        // positive test
        boolean result = menuController.handleConfirmationChoice(1);
        assertTrue(result);

        // negative test
        result = menuController.handleConfirmationChoice(100);
        assertFalse(result);
    }

    @Test
    public void testCalculateTotalPrices() {
        // positive test
        MenuItem menuItem1 = new MenuItem("Nasi Goreng", 2, 15000);
        MenuItem menuItem2 = new MenuItem("Mie Goreng", 3, 13000);
        menuController.orderedMenus.add(menuItem1);
        menuController.orderedMenus.add(menuItem2);
        List<Integer> totalPrices = menuController.calculateTotalPrices(menuController.orderedMenus);
        assertEquals(69000, (int) totalPrices.get(0));
        assertEquals(5, (int) totalPrices.get(1));
    }

    @Test
    public void testWriteHistoryFile() {
        // positive test
        MenuItem menuItem1 = new MenuItem("Nasi Goreng", 2, 15000);
        MenuItem menuItem2 = new MenuItem("Mie Goreng", 3, 13000);
        menuController.orderedMenus.add(menuItem1);
        menuController.orderedMenus.add(menuItem2);
        menuController.writeHistoryFile(menuController.orderedMenus);

        // negative test
        MenuItem invalidMenuItem = new MenuItem("Invalid Item", 1, 100);
        menuController.orderedMenus.add(invalidMenuItem);
        try {
            menuController.writeHistoryFile(menuController.orderedMenus);
            fail("Expected an exception to be thrown");
        } catch (RuntimeException e) {
            fail("Exception occurred while writing history file: " + e.getMessage());
        }
    }
}
