class Todo extends Task{
    Todo(String taskString) {
        super("[T]");
        this.taskString = taskString;
        this.printAdded();
    }
}
