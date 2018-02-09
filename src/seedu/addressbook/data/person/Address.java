package seedu.addressbook.data.person;

import seedu.addressbook.data.exception.IllegalValueException;

import java.util.ArrayList;

/**
 * Represents a Person's address in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidAddress(String)}
 */
public class Address {

    public static final String EXAMPLE = "123, some street";
    public static final String MESSAGE_ADDRESS_CONSTRAINTS = "Person addresses can be in any format";
    public static final String ADDRESS_VALIDATION_REGEX = ".+";

    public final String value;
    private boolean isPrivate;

    public Block block;
    public Street street;
    public Unit unit;
    public PostalCode postalCode;

    /**
     * Validates given address.
     *
     * @throws IllegalValueException if given address string is invalid.
     */
    public Address(String address, boolean isPrivate) throws IllegalValueException {
        String trimmedAddress = address.trim();
        this.isPrivate = isPrivate;
        if (!isValidAddress(trimmedAddress)) {
            throw new IllegalValueException(MESSAGE_ADDRESS_CONSTRAINTS);
        }

        this.value = trimmedAddress;

        ArrayList<String> addressData = splitIntoAddressData(trimmedAddress);
        initAddressProperties(addressData);
    }

    /**
     * Returns true if a given string is a valid person address.
     */
    public static boolean isValidAddress(String test) {
        return test.matches(ADDRESS_VALIDATION_REGEX);
    }

    /**
     * Returns an ArrayList of the respective data from an Address
     */
    public static ArrayList<String> splitIntoAddressData(String rawAddress) {
        ArrayList<String> addressData = new ArrayList<String>();
        int j = 0;
        int indexOfComma = rawAddress.indexOf(',');
        while (indexOfComma != -1) {
            addressData.add(rawAddress.substring(0, indexOfComma));
            j = indexOfComma + 2;
            rawAddress = rawAddress.substring(j);
            indexOfComma = rawAddress.indexOf(',');
        }
        addressData.add(rawAddress);

        return addressData;
    }

    /**
     * Stores the address data in its respective object attributes
     */
    public void initAddressProperties(ArrayList<String> addressData) {
        for (int i = 0; i < addressData.size(); i++) {
            String currAddressData = addressData.get(i);
            switch (i) {
            case 0:
                block = new Block(currAddressData);
                break;
            case 1:
                street = new Street(currAddressData);
                break;
            case 2:
                unit = new Unit(currAddressData);
                break;
            case 3:
                postalCode = new PostalCode(currAddressData);
                break;
            default:
                break;
            }
        }
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Address // instanceof handles nulls
                && this.value.equals(((Address) other).value)); // state check
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

    public boolean isPrivate() {
        return isPrivate;
    }
}
