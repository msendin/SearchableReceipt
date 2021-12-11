package cat.udl.demosEP.mocks;

import cat.udl.demosEP.ProductDB;
import cat.udl.demosEP.exceptions.DoesNotExistException;

import java.math.BigDecimal;

public class StubProductDB implements ProductDB {

    @Override
    public BigDecimal getPrice (String productID) throws DoesNotExistException {
        if (productID.equals("KEYBOARD"))
            return new BigDecimal("150.00");
        else if (productID.equals("CARD"))
            return new BigDecimal("75.00");
        else {
            throw new DoesNotExistException("Product does not exist at DDBB");
        }
    }
}