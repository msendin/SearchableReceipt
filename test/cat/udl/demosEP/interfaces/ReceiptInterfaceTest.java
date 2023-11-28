package cat.udl.demosEP.interfaces;

import cat.udl.demosEP.ProductDB;
import cat.udl.demosEP.Receipt;
import cat.udl.demosEP.exceptions.DoesNotExistException;
import cat.udl.demosEP.exceptions.IsClosedException;
import cat.udl.demosEP.mocks.StubProductDB;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertThrows;

public interface ReceiptInterfaceTest {

    Receipt receipt = new Receipt();

    @Test
    void addLineTest() throws IsClosedException, DoesNotExistException;

    @Test
    void addTaxesTest() throws IsClosedException;

    @Test
    default void getIsClosedExceptionTest() {
        BigDecimal perc = new BigDecimal("15.00");
        String pID = "KEYBOARD";
        assertThrows(IsClosedException.class,
                () -> {receipt.addTaxes(perc);
                       receipt.addLine(pID,10);
                });
    }

    @Test
    void getDoesNotExistExceptionTest();

}