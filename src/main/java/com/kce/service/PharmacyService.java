package com.kce.service;

import com.kce.entity.Medicine;
import com.kce.entity.Prescription;
import com.kce.util.HibernateUtil;
import com.pharmacy.util.ValidationException;
import com.pharmacy.util.OutOfStockException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.Date;

public class PharmacyService {

    public boolean issuePrescription(String medicineID,
                                     String patientName,
                                     int quantity,
                                     Date date) throws Exception {

        if (medicineID == null || patientName == null || quantity <= 0)
            throw new ValidationException();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            // Fetch medicine
            Medicine med = session.get(Medicine.class, medicineID);
            if (med == null) return false;

            if (med.getExpiryDate().before(date))
                throw new ValidationException();

            if (quantity > med.getAvailableStock())
                throw new OutOfStockException();

            // Update stock
            med.setAvailableStock(
                    med.getAvailableStock() - quantity
            );

            session.update(med);

            // Create prescription
            Prescription p = new Prescription();
            p.setMedicine(med);   // 🔥 Relationship
            p.setPatientName(patientName);
            p.setQuantity(quantity);
            p.setPrescribedDate(date);
            p.setStatus("ISSUED");

            session.save(p);   // ID auto-generated

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;

        } finally {
            session.close();
        }
    }

    public boolean cancelPrescription(int prescriptionID)
            throws Exception {

        if (prescriptionID <= 0)
            throw new ValidationException();

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Prescription p =
                    session.get(Prescription.class, prescriptionID);

            if (p == null) return false;

            p.setStatus("CANCELLED");
            session.update(p);

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;

        } finally {
            session.close();
        }
    }
}