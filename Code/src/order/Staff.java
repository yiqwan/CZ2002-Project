package order;

/**
 * A class to represent a staff member entity.
 * 
 */
public class Staff {
	/**
	 * Available genders.
	 *
	 */
	public enum Gender {
		MALE, FEMALE, NONE
	};

	/**
	 * The name of the staff member.
	 */
	private String name;
	/**
	 * The role/position of the staff member.
	 */
	private String jobTitle;
	/**
	 * the staff member's gender.
	 */
	private Gender gender;

	/**
	 * Constructor.
	 * @param name staff's name
	 * @param jobTitle staff's role/position
	 * @param gender staff's gender
	 */
	public Staff(String name, String jobTitle, Gender gender) {
		this.name = name;
		this.jobTitle = jobTitle;
		this.gender = gender;
	}

	/**
	 * Formats and prints a staff member's details.
	 */
	public void print() {
		System.out.println("Name          : " + name);
		System.out.println("Job Title     : " + jobTitle);
		System.out.println("Gender        : " + gender);
	}

	/**
	 * Gets a staff member's name.
	 * @return the staff member's name
	 */
	public String getName() {
		return name;
	}

	/**
	 * Set the name of the staff member.
	 * @param name the name to be given to the staff member
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Gets the role/position of the staff member.
	 * @return the staff member's role/position
	 */
	public String getJobTitle() {
		return jobTitle;
	}

	/**
	 * Set the role/position of the staff member.
	 * @param jobTitle the new role/position for the staff member
	 */
	public void setJobTitle(String jobTitle) {
		this.jobTitle = jobTitle;
	}

	/**
	 * Gets the gender of the staff member.
	 * @return the staff's gender, as an <code>enum</code>
	 */
	public Gender getGender() {
		return gender;
	}

	/**
	 * Sets the gender of the staff member.
	 * @param gender the gender to be set
	 */
	public void setGender(Gender gender) {
		this.gender = gender;
	}
}
