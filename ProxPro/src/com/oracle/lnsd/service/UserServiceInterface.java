package com.oracle.lnsd.service;

public interface UserServiceInterface {

	/* (non-Javadoc)
	 * @see com.oracle.lnsd.service.UserServiceInterface#saveOrUpdate()
	 */
	public abstract void saveOrUpdate();

	/* (non-Javadoc)
	 * @see com.oracle.lnsd.service.UserServiceInterface#deleteUser()
	 */
	public abstract void delete();

	/* (non-Javadoc)
	 * @see com.oracle.lnsd.service.UserServiceInterface#shearchByName()
	 */
	public abstract void shearchByName();

	/* (non-Javadoc)
	 * @see com.oracle.lnsd.service.UserServiceInterface#shearchById()
	 */
	public abstract void shearchById();

}