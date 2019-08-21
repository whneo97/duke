class Event extends Task{
    Event(String taskString) throws DukeException {
        super("[E]");

        if (taskString.equals("")) {
            throw new DukeException("The description of an event cannot be empty.");
        } else if (!taskString.contains("/at ")) {
            throw new DukeException("Please separate event description and date/time by \" /at \".");
        }

        Task.tasklist.add(this);

        String[] arr = taskString.split(" /at ");
        this.taskString = arr[0];
        this.dateAndTime = arr[1];
        this.printAdded();
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.dateAndTime + ")";
    }
}
