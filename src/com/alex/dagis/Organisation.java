package com.alex.dagis;
/***
 * An organisation is an unit of tasks that are done, with eg. like an company or municipality
 * @author Alexander
 *
 */
public interface Organisation {
		/**
		 * Gets the address to the organisation
		 * @return
		 */
		public String getAddress();
		/***
		 * Sets the address to the organisation
		 * @param address
		 */
		public void setAddress(String address);
		
}
