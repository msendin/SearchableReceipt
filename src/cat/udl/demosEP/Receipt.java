package cat.udl.demosEP;

import cat.udl.demosEP.exceptions.DoesNotExistException;
import cat.udl.demosEP.exceptions.IsClosedException;
import cat.udl.demosEP.mocks.StubProductDB;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

class Receipt {

    private final List<ReceiptLine> listOfProducts;
    private BigDecimal total;
    private boolean isClosed;

    private ProductDB prodDB;

    Receipt() {
        listOfProducts = new ArrayList<>();
        total = new BigDecimal("0.00");
        isClosed = false;
    }

    void setProductDB (ProductDB pDB) {
        prodDB = pDB;
    }

    public void addLine(String productID, int numUnits) throws IsClosedException, DoesNotExistException {
        BigDecimal price;
        ReceiptLine rcline;

        if (isClosed)
            throw new IsClosedException ("Recibo ya cerrado");
        else {
            price = prodDB.getPrice(productID);
            rcline = new ReceiptLine(productID, numUnits);
            listOfProducts.add(rcline);
            total = total.add(price.multiply(BigDecimal.valueOf(rcline.getNumUnits())));
        }
    }

    public void addTaxes(BigDecimal percent) throws IsClosedException {
        if (isClosed)
            throw new IsClosedException ("Recibo ya cerrado");
        else {
            BigDecimal cent = new BigDecimal("100.00");
            total = total.add(total.multiply(percent).divide(cent,2, RoundingMode.CEILING));
            isClosed = true;
        }
    }

    BigDecimal getTotal() {
        return total;
    }
}

