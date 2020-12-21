package cat.udl.demosEP;

import cat.udl.demosEP.exceptions.DoesNotExistException;

import java.math.BigDecimal;

public interface ProductDB {
    BigDecimal getPrice (String productID) throws DoesNotExistException;
}