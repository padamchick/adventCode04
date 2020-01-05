package adventcode.exception;

public class InvalidRoomNameException extends RuntimeException {
    public InvalidRoomNameException(String invalidRoomName) {
        super(invalidRoomName);
    }
}