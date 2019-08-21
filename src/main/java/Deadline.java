class Deadline extends Task{
    Deadline(String taskString) throws DukeException {
        super("[D]");

        if (taskString.equals("")) {
            throw new DukeException("The description of a deadline cannot be empty.");
        } else if (!taskString.contains("/by ")) {
            throw new DukeException("Please separate deadline description and date/time by \" /by \".");
        }

        Task.tasklist.add(this);

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
