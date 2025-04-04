package Assignments.assignment;

public class InvoiceItem_Demo1 {
	
	private String invoiceId;
    private String invoiceDesc;
    private int invoiceQty;
    private double invoiceItemPrice;
    
    public InvoiceItem_Demo1(String invoiceId, String invoiceDesc, int invoiceQty, double invoiceItemPrice)
    {
    	this.invoiceId = invoiceId;
    	this.invoiceDesc = invoiceDesc;
    	this.invoiceQty = invoiceQty;
    	this.invoiceItemPrice = invoiceItemPrice;
    }
	public String getInvoiceId() {
		return invoiceId;
	}
	public void setInvoiceId(String invoiceId) {
		this.invoiceId = invoiceId;
	}
	public String getInvoiceDesc() {
		return invoiceDesc;
	}
	public void setInvoiceDesc(String invoiceDesc) {
		this.invoiceDesc = invoiceDesc;
	}
	public int getInvoiceQty() {
		return invoiceQty;
	}
	public void setInvoiceQty(int invoiceQty) {
		this.invoiceQty = invoiceQty;
	}
	public double getInvoiceItemPrice() {
		return invoiceItemPrice;
	}
	public void setInvoiceItemPrice(double invoiceItemPrice) {
		this.invoiceItemPrice = invoiceItemPrice;
	}
	
	// Method to print the invoice amount based on quantity
    public void printInvoiceAmount() {
        if (invoiceQty == 1) {
            System.out.println("Invoice Price for one item: " + invoiceItemPrice);
        } else if (invoiceQty > 1) {
            double totalAmount = invoiceItemPrice * invoiceQty;
            System.out.println("Invoice Price for " + invoiceQty + " items: " + totalAmount);
        } else {
            System.out.println("Invalid quantity");
        }
    }
    
    public static void main(String args[]) {
    	
    	 // Creating an instance of the InvoiceItem_Demo class
        InvoiceItem_Demo1 invoiceItem = new InvoiceItem_Demo1("INV001", "Laptop", 1, 500.0);

        // Call the method to print the invoice amount
        invoiceItem.printInvoiceAmount();
    }
	


}
