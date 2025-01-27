package yapper.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void markAsDone_marksTaskAsCompleted() {
        Task task = new Task("Complete assignment");
        assertFalse(task.isDone); // Initially not done

        task.markAsDone();
        assertTrue(task.isDone);  // After marking, it should be done
    }

    @Test
    void testToString_correctFormat() {
        Task task = new Task("Read book");
        assertEquals("[ ] Read book", task.toString());

        task.markAsDone();
        assertEquals("[X] Read book", task.toString());
    }
}