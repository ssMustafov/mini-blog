package org.uni.ruse.mse.utils;

import javax.faces.application.FacesMessage;

/**
 * @author sinan
 */
public class FacesMessageUtils {

    public static FacesMessage constructFacesMessage(String message) {
	return new FacesMessage(message);
    }

}
