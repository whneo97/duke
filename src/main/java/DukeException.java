class DukeException extends Exception {

    /**
     * DukeException constructor that takes in the display message for users.
     * @param message Message that indicates to users details regarding the exception.
     */
    DukeException(String message) {
        super(message);
    }

    /**
     * DukeException constructor that takes in the display message and a Throwable cause for users.
     * @param message Message that indicates to users details regarding the exception.
     * @param cause Throwable cause that chains this exception to the exception (or other Throwable)
     *              that gave rise to it.
     */
    DukeException(String message, Throwable cause) {
        super(message, cause);
    }
}
