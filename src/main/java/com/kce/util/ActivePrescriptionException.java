package com.kce.util;
	public class ActivePrescriptionException extends Exception {

	    private static final long serialVersionUID = 1L;

	    public ActivePrescriptionException() {
	        super("Active Prescriptions Exist for this Medicine");
	    }

	    public ActivePrescriptionException(String message) {
	        super(message);
	    }

}
