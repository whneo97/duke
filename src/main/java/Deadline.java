class Deadline extends Task{
    Deadline(String taskString) {
        super("[E]", taskString);
        String[] arr = taskString.split(" /at ");
        this.dateAndTime = arr[1];
        this.taskString = arr[0];
    }

    @Override
    public String toString() {
        return type + isDone + " " + this.taskString + "(at: " + this.dateAndTime + ")";
    }
}
