package com.kce.entity;

import java.util.Date;
import java.util.List;
import jakarta.persistence.*;

@Entity
@Table(name = "MEDICINE")
public class Medicine {

    @Id
    @Column(name = "MEDICINE_ID")
    private String medicineID;

    @Column(name = "NAME")
    private String name;

    @Column(name = "BATCH_NO")
    private String batchNo;

    @Column(name = "TOTAL_STOCK")
    private int totalStock;

    @Column(name = "AVAILABLE_STOCK")
    private int availableStock;

    @Temporal(TemporalType.DATE)
    @Column(name = "EXPIRY_DATE")
    private Date expiryDate;

    // 🔥 Reverse Relationship
    @OneToMany(mappedBy = "medicine", cascade = CascadeType.ALL)
    private List<Prescription> prescriptions;

    public Medicine() {}

    // Getters and Setters

    public String getMedicineID() {
        return medicineID;
    }

    public void setMedicineID(String medicineID) {
        this.medicineID = medicineID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }

    public int getAvailableStock() {
        return availableStock;
    }

    public void setAvailableStock(int availableStock) {
        this.availableStock = availableStock;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(Date expiryDate) {
        this.expiryDate = expiryDate;
    }

    public List<Prescription> getPrescriptions() {
        return prescriptions;
    }

    public void setPrescriptions(List<Prescription> prescriptions) {
        this.prescriptions = prescriptions;
    }
}