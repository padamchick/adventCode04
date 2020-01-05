import adventcode.InputFileReader;
import adventcode.RoomNameDecoder;
import adventcode.exception.InvalidRoomNameException;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class SectorSumTest {

    @Test
    public void shouldComputeCorrectSectorIdSum() {
        long sectorSum = 0L;

        for (String encodedRoom : InputFileReader.readFileAsLines("day04_input2.txt")) {
            try {
                RoomNameDecoder decoder = new RoomNameDecoder(encodedRoom);
                if(decoder.isCorrectChecksum())
                    sectorSum += decoder.getSectorId();
            }
            catch (InvalidRoomNameException e) {
                // not important
            }
        }
        assertEquals(1573, sectorSum);
    }

    @Test
    public void shouldComputeCorrectSectorIdSum2() {
        long sectorSum = 0L;

        for (String encodedRoom : InputFileReader.readFileAsLines("day04_input.txt")) {
            try {
                RoomNameDecoder decoder = new RoomNameDecoder(encodedRoom);
                if(decoder.isCorrectChecksum())
                    sectorSum += decoder.getSectorId();
            }
            catch (InvalidRoomNameException e) {
                // not important
            }
        }
        System.out.println(sectorSum);
        assertEquals(245102, sectorSum);
    }
}

