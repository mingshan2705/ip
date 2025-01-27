package yapper.task;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

class TaskListTest {

    @Test
    void addTask_addsTaskToList() {
        TaskList taskList = new TaskList();
        Task task = new Task("Write code");

        taskList.addTask(task);
        assertEquals(1, taskList.size());
        assertEquals(task, taskList.tasks.get(0));
    }

    @Test
    void getTask_throwsExceptionForInvalidIndex() {
        TaskList taskList = new TaskList();
        assertThrows(IndexOutOfBoundsException.class, () -> taskList.tasks.get(0));
    }
}
