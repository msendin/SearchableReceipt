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

class OneOrMoreLinesReceiptTest implements ReceiptInterfaceTest {

      Receipt receipt;
      ProductDB prodDB;


@BeforeEach
void setUp() throws IsClosedException, DoesNotExistException {
     String pID;
            receipt = new Receipt();
            prodDB = new StubProductDB();
            receipt.setProductDB(prodDB);

            pID = "KEYBOARD";
            receipt.addLine(pID,1);
            pID = "CARD";
            receipt.addLine(pID,2);
}

@Override
@Test
public void addLineTest() throws IsClosedException, DoesNotExistException {
        String pID = "KEYBOARD";
        receipt.addLine(pID,10);
        assertEquals(new BigDecimal("1800.00"),receipt.getTotal());
}

@Override
@Test
public void addTaxesTest() throws IsClosedException {
        BigDecimal perc = new BigDecimal("15.00");
        receipt.addTaxes(perc);
        assertEquals(new BigDecimal("345.00"),receipt.getTotal());
}

@Override
@Test
public void getIsClosedExceptionTest() {
        BigDecimal perc = new BigDecimal("15.00");
        String pID = "CARD";
        assertThrows(IsClosedException.class,
          () -> {receipt.addTaxes(perc);
                 receipt.addLine(pID,10);
          });
        }

@Override
@Test
public void getDoesNotExistExceptionTest() {
        String pID = "LAPTOP";
        assertThrows(DoesNotExistException.class,
          () -> receipt.addLine(pID,10));
        }
}
