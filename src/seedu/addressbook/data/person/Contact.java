package seedu.addressbook.data.person;

/**
 * Represents a Person's contact in the address book.
 */
public class Contact {
    public String value;
    protected boolean isPrivate;

    public boolean isPrivate() {
        return isPrivate;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }
}
