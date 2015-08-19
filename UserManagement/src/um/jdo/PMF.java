package um.jdo;

import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManagerFactory;

/**
 * 
 * Persistence Manager Factory
 * 
 * @author rajatkhanna
 *
 */
public class PMF {
	/**
	 * The persistance manager instance. Saved for reuse.
	 */
	private static final PersistenceManagerFactory pmfInstance
	 = JDOHelper.getPersistenceManagerFactory("transactions-optional");
	
	/**
	 * Does not allow instances.
	 */
	private PMF() {}
	
	/**
	 * Get the persistence manager instance.
	 * @return the persistence manager instance
	 */
	public static PersistenceManagerFactory get() {
		return pmfInstance;
	}

}
