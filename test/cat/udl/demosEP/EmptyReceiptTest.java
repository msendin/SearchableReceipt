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
        assertEquals(new BigDecimal("1500.00"),receipt.getTotal());
    }

    @Override
    @Test
    public void addTaxesTest() throws IsClosedException {
        BigDecimal perc = new BigDecimal("15.00");
        receipt.addTaxes(perc);
        assertEquals(new BigDecimal("0.00"),receipt.getTotal());
    }

    @Override
    @Test
    public void getDoesNotExistExceptionTest() {
        String pID = "LAPTOP";
        assertThrows(DoesNotExistException.class,
                () -> receipt.addLine(pID,10));
    }
}
