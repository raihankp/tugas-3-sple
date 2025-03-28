package aisco.donation.banktransfer;

import java.util.ArrayList;
import java.util.List;
import java.util.Arrays;
import java.util.Random;
import aisco.donation.core.DonationComponent;

public class DonationImpl extends DonationComponent {
    private String bankName;
    private String virtualAccountNumber;
    private static List<DonationImpl> donationList = new ArrayList<>();

    public DonationImpl() {
        super("", "", "", 0, "");
        this.bankName = "";
        this.virtualAccountNumber = "";
    }

    public DonationImpl(String name, String email, String phone, int amount, String paymentMethod, String bankName) {
        super(name, email, phone, amount, paymentMethod);
        this.bankName = bankName;
        this.virtualAccountNumber = requestVANumber(phone, bankName);
    }

    private String requestVANumber(String phone, String bankName) {
        String bankCode = getBankCode(bankName);
        String userPhoneLast4 = phone.substring(phone.length() - 4);
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

    public void addDonation() {
        donationList.add(new DonationImpl("Anisa", "anisa@jmail.com", "+62878 6654 3321", 2500000, "Transfer", "BCA"));
        donationList.add(new DonationImpl("Dave", "dave@jmail.com", "+62828 2345 3091", 500000, "Transfer", "Mandiri"));
        donationList.add(new DonationImpl("Edo", "edo@jmail.com", "+62828 2345 3091", 300000, "Transfer", "BNI"));
        System.out.println("Donations added via Bank Transfer.");
    }

    public void getDonation() {
        System.out.println("Fetching all bank transfer donations:");
        for (DonationImpl donation : donationList) {
            System.out.println(donation);
        }
    }

    @Override
    public String toString() {
        return "- Donasi " + name + ": Rp" + amount + " via " + bankName + " (VA: " + virtualAccountNumber + ")";
    }
}
