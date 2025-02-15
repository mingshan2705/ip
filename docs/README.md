# Yapper User Guide 📋

Yapper is a task management application designed to help you keep track of your tasks, deadlines, and events efficiently.

## Managing tasks 🛠️

Yapper allows you to manage your tasks efficiently with various commands. You can add, delete, mark as done, and list tasks.

### Adding a task ➕

To add a task, use the `task` command followed by the task description.

Example: `task Read book`

This will add a new task with the description "Read book".

```
[T] Read book
```

### Deleting a task ❌

To delete a task, use the `delete` command followed by the task number.

Example: `delete 1`

This will delete the first task in the list.

```
Task 1 deleted successfully.
```

### Marking a task as done ✅

To mark a task as done, use the `done` command followed by the task number.

Example: `done 1`

This will mark the first task in the list as done.

```
[T][X] Read book
```

### Listing all tasks 📜

To list all tasks, use the `list` command.

Example: `list`

This will display all tasks in the list.

```
1. [T] Read book
2. [D] Submit report (by: Oct 10 2023)
```

## Types of tasks 🗂️

Yapper supports different types of tasks to help you organize your work better.

### Todo 📝

A basic task with no specific deadline.

Example: `todo Read book`

```
[T] Read book
```

### Deadline ⏰

A task that needs to be completed by a specific date.

Example: `deadline Submit report /by 2023-10-10`

```
[D] Submit report (by: Oct 10 2023)
```

### Event 📅

A task that occurs at a specific date and time.

Example: `event Team meeting /from 2023-10-10T10:00 /to 2023-10-10T12:00`

```
[E] Team meeting (at: Oct 10 2023 10:00)
```
