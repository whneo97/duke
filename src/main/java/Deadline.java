class Deadline extends Task{
    Deadline(String taskString) {
        super("[D]");
        String[] arr = taskString.split(" /by ");
        this.taskString = arr[0];
        this.dateAndTime = arr[1];
        this.printAdded();
    }

    @Override
    public String toString() {
        return super.toString() + " (by: " + this.dateAndTime + ")";
    }
}
