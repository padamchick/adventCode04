import adventcode.RoomNameDecoder;
import org.junit.jupiter.api.Test;
import adventcode.exception.InvalidRoomNameException;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RoomNameDecoderTest {
    @Test
    public void shouldBeAbleToParseEncodedRoom1() {
        RoomNameDecoder decoder = new RoomNameDecoder("aaaaa-bbb-z-y-x-123[abxyz]");
        assertEquals("aaaaa-bbb-z-y-x", decoder.getRoomName());
        assertEquals(123, decoder.getSectorId());
        assertEquals("abxyz", decoder.getDecodedChecksum());
    }

    @Test
    public void shouldBeAbleToParseEncodedRoom2() {
        RoomNameDecoder decoder = new RoomNameDecoder("a-b-c-d-e-f-g-h-987[abcde]");
        assertEquals(987, decoder.getSectorId());
        assertEquals("a-b-c-d-e-f-g-h", decoder.getRoomName());
        assertEquals("abcde",decoder.getDecodedChecksum());
    }
//
    @Test
    public void shouldBeAbleToComputeCorrectChecksum1() {
        RoomNameDecoder decoder = new RoomNameDecoder("not-a-real-room-404[oarel]");
        assertTrue(decoder.isCorrectChecksum());
    }

    @Test
    public void shouldBeAbleToComputeCorrectChecksum2() {
        RoomNameDecoder decoder = new RoomNameDecoder("totally-real-room-200[decoy]");
        assertFalse(decoder.isCorrectChecksum());
    }
}