package cat.udl.demosEP;

import cat.udl.demosEP.exceptions.DoesNotExistException;
import cat.udl.demosEP.exceptions.IsClosedException;
import cat.udl.demosEP.interfaces.ReceiptInterfaceTest;
import cat.udl.demosEP.mocks.StubProductDB;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class EmptyReceiptTest implements ReceiptInterfaceTest {

    Receipt receipt;
    ProductDB prodDB;

    @BeforeEach
    void setUp() {
        receipt = new Receipt();
        prodDB = new StubProductDB();
        receipt.setProductDB(prodDB);
    }

    @Override
    @Test
    public void addLineTest() throws IsClosedException, DoesNotExistException {
        String pID = "KEYBOARD";
        receipt.addLine(pID,10);
        assertEquals(new BigDecimal("1500"),receipt.getTotal());
    }

    @Override
    @Test
    public void addTaxesTest() throws IsClosedException {
        BigDecimal perc = new BigDecimal("15");
        receipt.addTaxes(perc);
        assertEquals(new BigDecimal("0"),receipt.getTotal());
    }

    @Override
    @Test
    public void getIsClosedExceptionTest() {
        assertThrows(IsClosedException.class,
                () -> {
                    BigDecimal perc = new BigDecimal("15");
                    receipt.addTaxes(perc);
                    String pID = "KEYBOARD";
                    receipt.addLine(pID,10);
                });
    }

    @Override
    @Test
    public void getDoesNotExistExceptionTest() {
        assertThrows(DoesNotExistException.class,
                () -> {
                    String pID = "LAPTOP";
                    receipt.addLine(pID,10);
                });
    }
}
