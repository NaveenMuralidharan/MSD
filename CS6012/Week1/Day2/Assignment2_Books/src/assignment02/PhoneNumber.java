package assignment02;

/**
 * Class representing a phone number with area code, trunk, and rest of the number.
 * The phone number is validated upon construction, and if it is invalid,
 * a default "000-000-0000" number is set.
 */
public class PhoneNumber {

  private String areaCode;

  private String trunk;

  private String rest;
  /**
   * Constructs a PhoneNumber object from a string representation of a phone number.
   * The input string is cleaned by removing all non-numeric characters and is validated
   * to ensure it is a valid 10-digit phone number.
   *
   * If the phone number is valid, it is split into area code, trunk, and rest.
   * Otherwise, the number is initialized as "000-000-0000", and an error is printed.
   *
   * @param phoneNum The string representation of the phone number, which can include
   *                 non-numeric characters such as dashes, spaces, parentheses, or periods.
   */
  public PhoneNumber(String phoneNum) {
    // Remove non-numeric characters
    phoneNum = phoneNum.replaceAll("-|\\s|\\.|\\(|\\)", "");

    boolean isValid = true;
    if (phoneNum.length() != 10)
      isValid = false;
    for (int i = 0; isValid && i < 10; i++)
      if (!Character.isDigit(phoneNum.charAt(i)))
        isValid = false;

    if (isValid) {
      areaCode = phoneNum.substring(0, 3);
      trunk = phoneNum.substring(3, 6);
      rest = phoneNum.substring(6, 10);
    } else {
      areaCode = "000";
      trunk = "000";
      rest = "000";
      System.err
          .println("Phone number \"" + phoneNum + "\" is not formatted correctly, initializing as " + toString() + ".");
    }
  }
  /**
   * Compares this PhoneNumber to another object for equality.
   * Two PhoneNumber objects are considered equal if their area code, trunk, and rest
   * are all the same.
   *
   * @param other The object to compare this PhoneNumber to.
   * @return {@code true} if the given object is a PhoneNumber with the same area code, trunk, and rest;
   *         {@code false} otherwise.
   */
  public boolean equals(Object other) {
    if (!(other instanceof PhoneNumber))
      return false;

    PhoneNumber rhs = (PhoneNumber) other;
    PhoneNumber lhs = this;

    return lhs.areaCode.equals(rhs.areaCode) && lhs.trunk.equals(rhs.trunk) && lhs.rest.equals(rhs.rest);
  }
  /**
   * Returns a string representation of the phone number in the format (XXX) XXX-XXXX.
   *
   * @return The formatted phone number as a string.
   */
  public String toString() {
    return "(" + areaCode + ") " + trunk + "-" + rest;
  }
  /**
   * Returns a hash code for this PhoneNumber object.
   * The hash code is computed by combining the hash codes of the area code, trunk, and rest.
   *
   * @return The hash code of this PhoneNumber object.
   */
  @Override
  public int hashCode() {
    return areaCode.hashCode() + trunk.hashCode() + rest.hashCode();
  }
}
