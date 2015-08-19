package um.jdo;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.jdo.annotations.Extension;
import javax.jdo.annotations.IdGeneratorStrategy;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;
import javax.jdo.annotations.PrimaryKey;

import org.mindrot.jbcrypt.BCrypt;
import org.omg.PortableInterceptor.USER_EXCEPTION;

/**
 * 
 * Main Database object for UserAuthenticaiton
 * 
 * @author rajatkhanna
 *
 */

@PersistenceCapable
public class UserAuthentication {
	
	//
	// ENCODED KEY
	//
	
	/**
	 * The primary key as a string corresponding to the nativeGAE key for automatic management.
	 */
	@PrimaryKey
	@Persistent(valueStrategy = IdGeneratorStrategy.IDENTITY)
	@Extension(vendorName = "datanucleus", key="gae.encoded-pk", value = "true")
	private String encodedKey;
	
	/**
	 * Returns the primary key - an encoded String (GAE agnostic)
	 * 
	 * @return the primary key (a string) of this UserAuthentication (GAE agnostic).
	 */
	public String getEncodedKey() {
		
		return encodedKey;
		
	}
	
	//
	//EMAIL
	//
	
	/**
	 * The regular expression pattern for the email.
	 */
	private static final Pattern EMAIL_PATTERN=
			Pattern.compile("\\A[A-Z0-9]._%+-]+@(?:[A-Z0-9-]+\\.)+[A-Z]{2-4}\\Z");
	
	/**
	 * Check if the email is correct.
	 * 
	 * @param name
	 * 			The checked string
	 */
	protected static void checkEmail(String email) {
		if (email==null) {
			throw new RuntimeException("The email is required.");
		}
		Matcher matcher = EMAIL_PATTERN.matcher(email.toUpperCase());
		if (!matcher.find()) {
			throw new RuntimeException("The email syntax is invalid.");
		}
	}
	
	//
	// PRETTY EMAIL
	//
	
	/**
	 * The pretty email used for user authentication. As typed by the user,
	 * using both lower and upper case letters.
	 */
	@Persistent
	private String prettyEmail;
	
	/**
	 * Returns the pretty email used for user authentication. As typed by the user,
	 * using both lower and upper case letters.
	 * 
	 * @return the pretty email (a String) used for this UserAuthentication
	 */
	public String getPrettyEmail() {
		return prettyEmail;
	}
	
	//
	// UPPERCASE EMAIL
	//
	
	/**
	 * The upper case email used for searching.
	 */
	@Persistent
	private String uppercaseEmail;
	
	/**
	 * Returns the upper case email used for searching
	 * 
	 * @return the upper case email (a String) used for this UserAuthentication
	 */
	public String getUppercaseEmail() {
		return uppercaseEmail;
	}
	
	/**
	 * Set the email related fields for a provided email
	 * @param email
	 */
	private void setEmail(String email) {
		checkEmail(email);
		prettyEmail = email;
		uppercaseEmail = email.toUpperCase();
	}
	
	//
	// HASHED PASSWORD
	//
	
	/**
	 * The hashed password used for authentication.
	 */
	@Persistent
	private String hashedPassword;
	
	/**
	 * Returns hashed password used for authentication
	 * 
	 * @return the hashed password used for authentication (a String)
	 */
	private String getHashedPassword() {
		return hashedPassword;
	}
	
	//
	// PASSWORD
	//
	
	/**
	 * Set the current hashedPassword with a hash of the provided plain password.
	 * @param password
	 */
	private void setPassword(String password) {
		//TODO check secure password
		hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt());
	}
	
	//
	// CREATE
	//
	
	private UserAuthentication(String email, String password) {
		setEmail(email);
		setPassword(password);
	}
	
	//
	// PUBLIC MEHTODS
	//
	
	//
	// TO JSON
	//
	
	/**
	 * Creates an external image for this user authentication.
	 * containing only the email of the authenticated user.
	 * It is not safe to return any other data.
	 * 
	 * @return a JSON String with a structure with the email.
	 */
	public String toExternalJsonString() {
		String json = "{";
		json += "\"email\":" + JsonUtil.toJsonString(getPrettyEmail());
		json += "}";
		return json;
	}
	
	//
	// PUBLIC STATIC METHODS
	//
	
	//
	// STATIC SEARCH
	//
	
	/**
	 * Return the unique user registered with this email.
	 * @param pm the current PersistanceManager
	 * @param email The searched email (in the user format)
	 * @return The UserAuthentication if found. Null otherwise.
	 */
	public static UserAuthentication getUserWithEmail(PersistenceManager pm, String email) {
		UserAuthentication ua = null;
		try {
			Query query = pm.newQuery(UserAuthentication.class);
			query.setFilter("uppercaseEmail == emailParam");
			query.setOrdering("uppercaseEmail asc");
			query.declareParameters("String emailParam");
			@SuppressWarnings("unchecked")
			List<UserAuthentication> result = (List<UserAuthentication>)query.execute(email.toUpperCase());
		
			if (result != null && result.size() > 0) {
				ua = result.get(0);
			}
		} catch (Exception e) {
			//TODO log the error
		}
		return ua;
	}
	
	//
	// SUBSCRIBE
	//
	
	/**
	 * Subscribe a user to the application if possible.
	 * The user must not be already subscribed and the email must have a
	 * valid syntax.
	 * 
	 * @param email
	 * 			The email of the user, used as identifier
	 * @param password
	 * 			The password of the user.
	 * @return the newly created user authentication
	 */
	public static UserAuthentication subscribeUser(String email, String password) {
		
		UserAuthentication ua = null;
		PersistenceManager pm = PMF.get().getPersistenceManager();
		
		try {
			ua = getUserWithEmail(pm, email);
			if (ua != null) {
				throw new RuntimeException("User with email " + email + " is already subscribed");
			}
			ua = new UserAuthentication(email, password);
			pm.makePersistent(ua);
			return ua;
		} finally {
			pm.close();
		}
		
	}
	
}
