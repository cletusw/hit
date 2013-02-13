package mcontrollers;

import java.util.Observable;
import java.util.Observer;

/**
 * Controller object that acts as a liaison between the model's ProductManager and the GUI
 * view.
 * 
 * @author Matt Hess
 * @version 1.0 CS 340 Group 4 Phase 2
 * 
 */
public class ProductController implements Observer {

	/**
	 * Method intended to notify the view when ProductManager sends a "change" notice.
	 * 
	 * @param o
	 *            The
	 * @param arg
	 *            Object passed to the view so the view can determine which changes to make
	 * 
	 * @pre o != null
	 * @pre arg != null
	 * @pre o instanceof ProductManager
	 * @post true
	 * 
	 */
	@Override
	public void update(Observable o, Object arg) {
		// TODO Auto-generated method stub

	}

}
