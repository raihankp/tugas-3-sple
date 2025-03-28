package aisco.donation.banktransfer;
import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import aisco.donation.core.DonationComponent;
import java.util.Random;

public class DonationImpl extends DonationComponent {
    private String bankName;
    private String virtualAccountNumber;

    public DonationImpl(String name, String email, String phone, int amount, String paymentMethod, String bankName) {
        super(name, email, phone, amount, paymentMethod);
        this.bankName = bankName;
        this.virtualAccountNumber = requestVANumber(name, bankName);
    }

    private String requestVANumber(String name, String bankName) {
        String bankCode = getBankCode(bankName);
        String userPhoneLast4 = this.phone.substring(this.phone.length() - 4);
        return bankCode + userPhoneLast4 + generateRandomNumber(4);
    }

    public String getVANumber() {
        return virtualAccountNumber;
    }

    private String getBankCode(String bankName) {
        switch (bankName.toLowerCase()) {
            case "bca": return "014";
            case "bni": return "009";
            case "mandiri": return "008";
            case "bri": return "002";
            default: return "000";
        }
    }

    private String generateRandomNumber(int length) {
        Random random = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(random.nextInt(10));
        }
        return sb.toString();
    }

    @Override
    public void addDonation() {
    	System.out.println("Donation added via Transfer Bank.");
        System.out.println("Name: " + this.name);
        System.out.println("Bank: " + bankName);
        System.out.println("Virtual Account: " + virtualAccountNumber);
        System.out.println("Amount: Rp" + this.amount);
    }

    @Override
    public void getDonation() {
        System.out.println("Fetching donation details for " + this.name);
        System.out.println("Bank: " + bankName);
        System.out.println("Virtual Account: " + virtualAccountNumber);
        System.out.println("Amount: Rp" + this.amount);
    }
}
