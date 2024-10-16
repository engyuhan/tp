package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommentCommand.MESSAGE_ARGUMENTS;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.person.Comment;

/**
 * Tests CommentCommand class.
 */
public class CommentCommandTest {
    public static final Comment VALID_COMMENT_BOB = new Comment("Favourite pastime is Eating");
    public static final Comment VALID_COMMENT_AMY = new Comment("Like skiing");
    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute() {
        final Comment comment = new Comment("Some comment");

        assertCommandFailure(new CommentCommand(INDEX_FIRST_PERSON, comment), model,
                String.format(MESSAGE_ARGUMENTS, INDEX_FIRST_PERSON.getOneBased(), comment));
    }

    @Test
    public void equals() {
        final CommentCommand standardCommand = new CommentCommand(INDEX_FIRST_PERSON, VALID_COMMENT_AMY);

        // same valies -> return true
        CommentCommand commandWithSameValues = new CommentCommand(INDEX_FIRST_PERSON, VALID_COMMENT_AMY);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> return true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> return false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new CommentCommand(INDEX_SECOND_PERSON, VALID_COMMENT_AMY)));

        // different remark -> returns false
        assertFalse(standardCommand.equals(new CommentCommand(INDEX_FIRST_PERSON, VALID_COMMENT_BOB)));
    }
}
