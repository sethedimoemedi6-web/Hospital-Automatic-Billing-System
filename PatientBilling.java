public class PatientBilling {
//to no shaka mari dzose dzi no no rwegwa ne ngwele te ba te thama receipt

    private int numberofLabTests;
    private int consultationFee;
    private final double Lab_Test_price=150.0;
    private final double Vat_Rate=0.14;




    public int getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(int consultationFee) {
        this.consultationFee = consultationFee;
    }

    public  PatientBilling(int numberofLabTests, int consultationFee){
        this.numberofLabTests = numberofLabTests;
        this.consultationFee = consultationFee;

    }
    public double calculateSubtotal() {
        return numberofLabTests * Lab_Test_price;
    }
public double calculateVatRate() {
    return (consultationFee+calculateSubtotal())*Vat_Rate;
}
public double calculateTotal() {
        return consultationFee + calculateVatRate()+ calculateSubtotal();
}



}
