import adventcode.RoomNameDecoder;
import org.junit.Assert;
import org.junit.Test;

public class RoomNameDecoderTest {
    @Test
    public void shouldBeAbleToParseEncodedRoom1() {
        RoomNameDecoder decoder = new RoomNameDecoder("aaaaa-bbb-z-y-x-123[abxyz]");
        Assert.assertEquals("aaaaa-bbb-z-y-x", decoder.getRoomName());
        Assert.assertEquals(123, decoder.getSectorId());
        Assert.assertEquals("abxyz", decoder.getDecodedChecksum());
    }

    @Test
    public void shouldBeAbleToParseEncodedRoom2() {
        RoomNameDecoder decoder = new RoomNameDecoder("a-b-c-d-e-f-g-h-987[abcde]");
        Assert.assertEquals(987, decoder.getSectorId());
        Assert.assertEquals("a-b-c-d-e-f-g-h", decoder.getRoomName());
        Assert.assertEquals("abcde",decoder.getDecodedChecksum());
    }
//
    @Test
    public void shouldBeAbleToComputeCorrectChecksum1() {
        RoomNameDecoder decoder = new RoomNameDecoder("not-a-real-room-404[oarel]");
        Assert.assertTrue(decoder.isCorrectChecksum());
    }

    @Test
    public void shouldBeAbleToComputeCorrectChecksum2() {
        RoomNameDecoder decoder = new RoomNameDecoder("totally-real-room-200[decoy]");
        Assert.assertFalse(decoder.isCorrectChecksum());
    }
}