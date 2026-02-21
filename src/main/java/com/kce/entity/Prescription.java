package com.kce.entity;

import java.util.Date;
import jakarta.persistence.*;

@Entity
@Table(name = "PRESCRIPTION")
public class Prescription {

    @Id
    @SequenceGenerator(
        name = "prescription_seq",
        sequenceName = "prescription_seq",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "prescription_seq"
    )
    @Column(name = "PRESCRIPTION_ID")
    private int prescriptionID;

    // 🔥 RELATIONSHIP
    @ManyToOne
    @JoinColumn(name = "MEDICINE_ID")
    private Medicine medicine;

    @Column(name = "PATIENT_NAME")
    private String patientName;

    @Column(name = "QUANTITY")
    private int quantity;

    @Temporal(TemporalType.DATE)
    @Column(name = "PRESCRIBED_DATE")
    private Date prescribedDate;

    @Column(name = "STATUS")
    private String status;

    public Prescription() {}

    public int getPrescriptionID() {
        return prescriptionID;
    }

    public void setPrescriptionID(int prescriptionID) {
        this.prescriptionID = prescriptionID;
    }

    public Medicine getMedicine() {
        return medicine;
    }

    public void setMedicine(Medicine medicine) {
        this.medicine = medicine;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getPrescribedDate() {
        return prescribedDate;
    }

    public void setPrescribedDate(Date prescribedDate) {
        this.prescribedDate = prescribedDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}