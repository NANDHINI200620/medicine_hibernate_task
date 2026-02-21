package com.kce.dao;

import com.kce.entity.Medicine;
import com.kce.util.HibernateUtil;

import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.List;

public class MedicineDAO {

    // Find Medicine by ID
    public Medicine findMedicine(String medicineID) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Medicine medicine = null;

        try {
            medicine = session.get(Medicine.class, medicineID);
        } finally {
            session.close();
        }

        return medicine;
    }

    // View All Medicines
    public List<Medicine> viewAllMedicines() {

        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Medicine> list = null;

        try {
            list = session.createQuery("from Medicine", Medicine.class).list();
        } finally {
            session.close();
        }

        return list;
    }

    // Insert Medicine
    public boolean insertMedicine(Medicine m) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            session.save(m);
            tx.commit();
            return true;
        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    // Update Available Stock
    public boolean updateAvailableStock(String medicineID, int newCount) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Medicine m = session.get(Medicine.class, medicineID);
            if (m == null) return false;

            m.setAvailableStock(newCount);
            session.update(m);

            tx.commit();
            return true;

        } catch (Exception e) {
            if (tx != null) tx.rollback();
            return false;
        } finally {
            session.close();
        }
    }

    // Delete Medicine
    public boolean deleteMedicine(String medicineID) {

        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Medicine m = session.get(Medicine.class, medicineID);
            if (m == null) return false;

            session.delete(m);
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