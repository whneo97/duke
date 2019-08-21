class Event extends Task{
    Event(String taskString) {
        super("[E]");
        this.taskString = taskString;

        String[] arr = taskString.split(" /at ");
        this.taskString = arr[0];
        this.dateAndTime = arr[1];

        System.out.println("Got it. I've added this task:\n  " + this);
        System.out.print("Now you have " + tasklist.size());
        if (tasklist.size() == 1) {
            System.out.print(" task in the list");
        }  else {
            System.out.print(" tasks in the list");
        }
        System.out.println();
    }

    @Override
    public String toString() {
        return super.toString() + " (at: " + this.dateAndTime + ")";
    }
}
