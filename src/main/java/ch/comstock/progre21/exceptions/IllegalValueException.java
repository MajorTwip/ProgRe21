package ch.comstock.progre21.exceptions;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class IllegalValueException extends IllegalArgumentException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	   private final String messageKey;
	
	public IllegalValueException(String msg) {
		super(msg);
		this.messageKey = "";
	}
	
	public IllegalValueException(String msg, String key) {
		super(msg);
		this.messageKey = key;
	}

    public String getLocalizedMessage() {
    	try {
    		return ResourceBundle.getBundle("MessagesBundle").getString(messageKey);
    	}catch(MissingResourceException e) {
    		return super.getMessage();
    	}
    }
	
}
