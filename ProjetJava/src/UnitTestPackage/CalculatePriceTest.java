package UnitTestPackage;

import static org.junit.jupiter.api.Assertions.*;

import businessPackage.CalculatePrice;
import org.junit.jupiter.api.*;
class CalculatePriceTest {
    private CalculatePrice calculatePrice;
    @BeforeEach
    void setUp() {
         calculatePrice = new CalculatePrice();
    }
    @Test
    void totalPriceTVA() {
        assertEquals(0,calculatePrice.totalPriceTVA(0,0,0), 0.1);
        assertEquals(0,calculatePrice.totalPriceTVA(0,1,0), 0.1);
        assertEquals(0,calculatePrice.totalPriceTVA(1,0,0), 0.1);
        assertEquals(10,calculatePrice.totalPriceTVA(1,10,0), 0.1);
        assertEquals(10,calculatePrice.totalPriceTVA(10,1,0), 0.1);
        assertEquals(30.25,calculatePrice.totalPriceTVA(10,2.5,21), 0.1);
        assertEquals(26.5,calculatePrice.totalPriceTVA(10,2.5,6), 0.1);
    }
    @Test
    void totalPriceWTVA() {
        assertEquals(0,calculatePrice.totalPriceWTVA(0,0));
        assertEquals(0,calculatePrice.totalPriceWTVA(0,1));
        assertEquals(0,calculatePrice.totalPriceWTVA(1,0));
        assertEquals(10,calculatePrice.totalPriceWTVA(1,10));
        assertEquals(10,calculatePrice.totalPriceWTVA(10,1));
    }
}