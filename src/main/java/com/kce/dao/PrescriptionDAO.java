package com.kce.dao;

import com.kce.entity.Prescription;
import com.kce.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

public class PrescriptionDAO {

    // Save Prescription (No manual ID needed)
    public boolean recordPrescription(Prescription p) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(p);   // Hibernate auto-generates ID
            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;

        } finally {
            session.close();
        }
    }

    // Cancel Prescription
    public boolean cancelPrescription(int prescriptionID) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Prescription p = session.get(Prescription.class, prescriptionID);
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