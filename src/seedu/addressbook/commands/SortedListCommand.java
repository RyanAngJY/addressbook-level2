package seedu.addressbook.commands;

import seedu.addressbook.data.person.ReadOnlyPerson;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


/**
 * Lists all persons in the address book to the user.
 */
public class SortedListCommand extends Command {

    public static final String COMMAND_WORD = "sortedList";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Displays all persons in the address book as an alphabetically sorted list with index numbers.\n"
            + "Example: " + COMMAND_WORD;


    @Override
    public CommandResult execute() {
        List<ReadOnlyPerson> allPersons = addressBook.getAllPersons().mutableListView();
        // List<String> stringListAllPersons = convertToStringList(allPersons);
        allPersons.sort((person1, person2) -> person1.getName().compareToIgnoreCase(person2.getName()));

        // need to alter this
        return new CommandResult(getMessageForPersonListShownSummary(allPersons), allPersons);
    }

    /**
     * Converts List<ReadOnlyPerson> into ArrayList<String>
     */
    public ArrayList<String> convertToStringList (List<ReadOnlyPerson> allPersons) {
        ArrayList<String> stringListAllPersons = new ArrayList<String>();
        for (ReadOnlyPerson person : allPersons) {
            stringListAllPersons.add(person.toString());
        }
        return stringListAllPersons;
    }
}