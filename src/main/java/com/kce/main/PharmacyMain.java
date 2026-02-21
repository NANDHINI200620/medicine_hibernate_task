package com.kce.main;

import com.pharmacy.service.PharmacyService;
import com.kce.util.HibernateUtil;

import java.util.Date;
import java.util.Scanner;

public class PharmacyMain {

    private static PharmacyService pharmacyService;

    public static void main(String[] args) {

        pharmacyService = new PharmacyService();
        Scanner sc = new Scanner(System.in);

        System.out.println("--- Pharmacy Console ---");

        // Use java.util.Date
        Date testDate = new Date();  

        try {
            boolean r =
                pharmacyService.issuePrescription(
                        "MED1002",
                        "Dr. Kaur",
                        10,
                        testDate
                );
            System.out.println(r ? "ISSUED" : "FAILED");

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            boolean r =
                pharmacyService.issuePrescription(
                        "MED1001",
                        "Patient A",
                        20,
                        testDate
                );
            System.out.println(r ? "ISSUED" : "FAILED");

        } catch (Exception e) {
            System.out.println(e);
        }

        try {
            boolean r =
                pharmacyService.cancelPrescription(130001);
            System.out.println(r ? "CANCELLED" : "FAILED");

        } catch (Exception e) {
            System.out.println(e);
        }

        sc.close();

        // 🔥 Very Important – Close SessionFactory
        HibernateUtil.shutdown();
    }
}