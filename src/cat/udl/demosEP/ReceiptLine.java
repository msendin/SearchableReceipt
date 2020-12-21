package cat.udl.demosEP;

class ReceiptLine {
    private int numUnits;
    private String productID;

    ReceiptLine (String productID, int num) {
        this.productID = productID;
        numUnits = num;
    }

    int getNumUnits () {
        return numUnits;
    }

    String getProductID() {
        return productID;
    }
}
