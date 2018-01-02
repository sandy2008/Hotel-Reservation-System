import java.util.Calendar;

/**
 * Custom date class containing methods for date calculation.
 * 
 * @author	Stefan Hahn
 */
public class Date implements Comparable<Date> {
	/**
	 * Holds maximum amount of days per month.
	 */
	public static final byte[] MONTH_MAX_DAYS = new byte[] {31, 29, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
	
	/**
	 * Holds a full date integer for the date this object represents with format: YYYMMDD
	 */
	private int fullDate = 0;
	
	/**
	 * Creates a new Date object from given date string with format DD.MM.YYYY.
	 * 
	 * @param		date					String representing a date with format DD.MM.YYYY
	 * @throws		DateFormatException			Thrown if given date string specifies an invalid date
	 */
	public Date(String date) throws DateFormatException {
		if (date.contains(".")) {
			String[] parts = date.split("\\.");
			
			if (parts.length != 3) {
				throw new DateFormatException("Format Wrong");
			}
			
			try {
				this.fullDate = Integer.parseInt(parts[0]) + Integer.parseInt(parts[1]) * 100 + Integer.parseInt(parts[2]) * 10000;
			}
			catch (NumberFormatException e) {
				throw new DateFormatException("Format Wrong");
			}
			
			this.check();
		}
		else {
			throw new DateFormatException("Format Wrong");
		}
	}
	
	/**
	 * Creates a new Date object with given parameters.
	 * 
	 * @param		day					Day of date
	 * @param		month					Month of date
	 * @param		year					Year of date
	 * @throws		DateFormatException			Thrown if parameters specify an invalid date
	 */
	public Date(int day, int month, int year) throws DateFormatException {
		this.fullDate = day + month * 100 + year * 10000;
		this.check();
	}
	
	/**
	 * Creates a new Date object with given day and month and current year.
	 * 
	 * @param		day					Day of date
	 * @param		month					Month of year
	 * @throws		DateFormatException			Thrown if parameters specify an invalid date
	 */
	public Date(int day, int month) throws DateFormatException {
		this(day, month, Date.getCurrentDate().getYear());
	}
	
	/**
	 * Creates a new Date object with given day and current month and year.
	 * 
	 * @param		day					Day of date
	 * @throws		DateFormatException			Thrown if parameter specifies an invalid date
	 */
	public Date(int day) throws DateFormatException {
		this(day, Date.getCurrentDate().getMonth(), Date.getCurrentDate().getYear());
	}
	
	/**
	 * Creates a new Date object with current date.
	 */
	public Date() {
		this(Date.getCurrentDate().getDay(), Date.getCurrentDate().getMonth(), Date.getCurrentDate().getYear());
	}
	
	/**
	 * Checks if this date object represents a valid date, throws a DateFormatException if date is invalid.
	 * 
	 * @throws		DateFormatException			Thrown if date this object represents is invalid
	 */
	public void check() throws DateFormatException {
		int day = this.getDay();
		int month = this.getMonth();
		int year = this.getYear();
		
		if ((year) < 1) {
			throw new DateFormatException("Wrong Year");
		}
		
		if ((month < 1) || (month > 12)) {
			throw new DateFormatException("Wrong Month");
		}
		
		if (day > Date.getDaysForMonth(month, year)) {
			throw new DateFormatException("Wrong Tag");
		}
	}
	
	/**
	 * Formats the date this object represents in a readable format.
	 * 
	 * @return							Date string with following format: DD.MM.YYYY
	 */
	public String format() {
		StringBuilder s = new StringBuilder();
		
		s.append(this.getDay());
		s.append(".");
		s.append(this.getMonth());
		s.append(".");
		s.append(this.getYear());
		
		return s.toString();
	}
	
	/**
	 * Returns a Date object for the next day after the date this object represents.
	 * 
	 * @return							Date object representing the next day
	 * @throws		DateFormatException			Thrown when resulting Date object represents an invalid date
	 */
	public Date getFollowingDate() throws DateFormatException {
		return this.getFollowingDate(1);
	}
	
	/**
	 * Returns a fDate object for the next day(s) after/before the date this object represents.
	 * Amount of days to jump over is indicated by daysJump.
	 * 
	 * @param		daysJump				Amount of days to jump over, can be negative to receive previous dates, too
	 * @return							Date object representing the following/preceding date
	 * @throws		DateFormatException			Thrown when result date is invalid
	 */
	public Date getFollowingDate(int daysJump) throws DateFormatException {
		int year = this.getYear();
		int month = this.getMonth();
		int day = this.getDay();
		int maxDays = Date.getDaysForMonth(month, year);
		int signumDaysJump = daysJump / Math.abs(daysJump);
		
		if (signumDaysJump == 1) {
			for (int i = 0; i < daysJump; i++) {
				day++;
				
				if (day > maxDays) {
					day = 1;
					month++;
					
					if (month > 12) {
						month = 1;
						year++;
					}
					
					maxDays = Date.getDaysForMonth(month, year);
				}
			}
		}
		else if (signumDaysJump == -1) {
			for (int i = 0; i > daysJump; i--) {
				day--;
				
				if (day < 1) {
					month--;
					
					if (month < 1) {
						month = 12;
						year--;
					}
					
					day = Date.getDaysForMonth(month, year);
				}
			}
			
			if (year < 1) {
				throw new DateFormatException("Bad Year");
			}
		}
		
		return new Date(day, month, year);
	}
	
	/**
	 * Calculates the amount of days between two dates (exclusive).
	 * This date must be ahead of secondDate.
	 * 
	 * @param		secondDate				Date object represening a date after date this object represents
	 * @return							Amount of days between this date and given date (exclusive)
	 * @throws		DateFormatException			Thrown when date represented by secondDate is ahead of date this object represents
	 */
	public int delta(Date secondDate) throws DateFormatException {
		int daysDiff = 0;
		
		if (this.compareTo(secondDate) > 0) {
			throw new DateFormatException("Illegal Date");
		}
		
		while (!this.equals(secondDate)) {
			daysDiff++;
			
			secondDate = secondDate.getFollowingDate(-1);
		}
		
		return daysDiff;
	}
	
	/**
	 * Returns the full date integer for the date this object represents.
	 * 
	 * @return							Full date integer representing the dae this object represents with format: YYYYMMDD
	 */
	public int getFullDate() {
		return this.fullDate;
	}
	
	/**
	 * Returns the day of this date.
	 * 
	 * @return							Day of this date
	 */
	public int getDay() {
		return this.getFullDate() % 100;
	}
	
	/**
	 * Returns the month of this day.
	 * 
	 * @return							Month of this date
	 */
	public int getMonth() {
		return (this.getFullDate() / 100) % 100;
	}
	
	/**
	 * Returns the year of this date.
	 * 
	 * @return							Year of this date
	 */
	public int getYear() {
		return this.getFullDate() / 10000;
	}
	
	/**
	 * @see	Comparable#compareTo(Object)
	 */
	public int compareTo(Date date) {
		return (int) Math.signum(this.getFullDate() - date.getFullDate());
	}
	
	/**
	 * @see	Object#toString()
	 */
	@Override
	public String toString() {
		return this.format();
	}
	
	/**
	 * @see	Object#equals(Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if ((null == obj) || (obj.getClass() != Date.class)) {
			return false;
		}
		
		return ((Date) obj).getFullDate() == this.getFullDate();
	}
	
	/**
	 * Gets a Date object representing the current date.
	 * 
	 * @return							Date object representing the current date
	 */
	public static Date getCurrentDate() throws DateFormatException {
		Calendar calender = Calendar.getInstance();
		
		return new Date(calender.get(Calendar.DAY_OF_MONTH), calender.get(Calendar.MONTH) + 1, calender.get(Calendar.YEAR)); 
	}
	
	/**
	 * Takes a month and a year, returns the maximum amount of day for this month.
	 * Considers leap years.
	 * 
	 * @param		month					integer containing values from 1 to 12
	 * @param		year					integer containing values larger than 1
	 * @return							Maximum amount of days for the given month ind the given year
	 * @throws		DateFormatException			Thrown when month or year contain invalid values
	 */
	public static int getDaysForMonth(int month, int year) throws DateFormatException {
		if ((month < 1) || (month > 12)) {
			throw new DateFormatException("Bad Month");
		}
		
		if (year < 1) {
			throw new DateFormatException("Bad Year");
		}
		
		return Date.MONTH_MAX_DAYS[month - 1] - (((month == 2) && !Date.isLeapYear(year)) ? 1 : 0);
	}
	
	/**
	 * Tests wether the given year is a leap year.
	 * 
	 * @param		year					Values representing any year, has to me larger than 0
	 * @return							true if given date is lep year, false otherwise
	 */
	public static boolean isLeapYear(int year) throws DateFormatException {
		if (year < 1) {
			throw new DateFormatException("Leap Year");
		}
		
		return (((year % 400) == 0) || (((year % 4) == 0) && ((year % 100) != 0)));
	}
}
