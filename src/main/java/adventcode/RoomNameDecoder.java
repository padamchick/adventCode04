package adventcode;

import adventcode.exception.InvalidRoomNameException;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;


public class RoomNameDecoder {
    private static final Pattern ROOM_NAME_PATTERN = Pattern.compile("(?<roomName>(?:[a-z]+-?)+)-(?<roomNumber>\\d+)\\[(?<decodedRoomName>[a-z]+)\\]");
    private String roomName;
    private int sectorId;
    private String decodedChecksum;
    private String correctChecksum;

    public RoomNameDecoder(String encodedRoomName) {
        Matcher matcher = ROOM_NAME_PATTERN.matcher(encodedRoomName);
        if (!matcher.matches()) {
            throw new InvalidRoomNameException(encodedRoomName);
        }
        roomName = matcher.group("roomName");
        sectorId = Integer.parseInt(matcher.group("roomNumber"));
        decodedChecksum = matcher.group("decodedRoomName");
        correctChecksum = computeCorrectChecksum();
    }

    public boolean isCorrectChecksum() {
        if(!correctChecksum.equals(decodedChecksum)) {
            return false;
        }
        return true;
    }

    private String computeCorrectChecksum() {
        Map<Character,Integer> letters = countLetters();
        List<Map.Entry<Character,Integer>> sortedLetters = sortByLettersCount(letters);
        int checkSumLength = Math.min(5, sortedLetters.size());
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<checkSumLength; i++) {
            sb.append(sortedLetters.get(i).getKey());
        }
        return sb.toString();
    }

    private Map<Character, Integer> countLetters() {
        Map<Character, Integer> letters = new HashMap<>();
        String allLetters = roomName.replaceAll("-", "");
        for (Character letter : allLetters.toCharArray()) {
            int letterCount = 1;
            if (letters.containsKey(letter)) {
                letterCount += letters.get(letter);
            }
            letters.put(letter, letterCount);
        }
        return letters;
    }

    private List<Map.Entry<Character, Integer>> sortByLettersCount(Map<Character, Integer> letters) {
        List<Map.Entry<Character, Integer>> entries = new ArrayList<>(letters.entrySet());
//        entries = entries.stream().sorted(Map.Entry.<Character, Integer>comparingByValue(Comparator.reverseOrder())
//                .thenComparing(Map.Entry.comparingByKey()))
//                .collect(Collectors.toList());
        Collections.sort(entries, (o1, o2) -> {
            if (o2.getValue().compareTo(o1.getValue()) != 0)
                return o2.getValue().compareTo(o1.getValue());
            else return o1.getKey().compareTo(o2.getKey());
        });

        return entries;
    }

    public String getRoomName() {
        return roomName;
    }

    public String getDecodedChecksum() {
        return decodedChecksum;
    }

    public String getCorrectChecksum() {
        return correctChecksum;
    }

    public int getSectorId() {
        return sectorId;
    }


}