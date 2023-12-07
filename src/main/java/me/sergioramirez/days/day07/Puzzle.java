package me.sergioramirez.days.day07;

import me.sergioramirez.util.InputTextUtil;

import java.nio.file.Paths;
import java.util.*;
import java.util.stream.IntStream;
import java.util.stream.Stream;

public class Puzzle {
    private static final String filepath = Paths.get("").toAbsolutePath()+"\\src\\main\\java\\me\\sergioramirez\\days\\day07\\input.txt";
    private static final List<String> input = InputTextUtil.read_all_lines(filepath);
    private static final Map<String, Integer> label_value = new HashMap<>(){{
        put("A", 13);
        put("K", 12);
        put("Q", 11);
        put("J", 10);
        put("T", 9);
        put("9", 8);
        put("8", 7);
        put("7", 6);
        put("6", 5);
        put("5", 4);
        put("4", 3);
        put("3", 2);
        put("2", 1);
    }};
    private static final LinkedHashMap<String, List<Hand>> type_hands = new LinkedHashMap<>(){{
        put("High card", new ArrayList<>());
        put("One pair", new ArrayList<>());
        put("Two pair", new ArrayList<>());
        put("Three of kind", new ArrayList<>());
        put("Full house", new ArrayList<>());
        put("Four of a kind", new ArrayList<>());
        put("Five of a kind", new ArrayList<>());
    }};

    public static void init_hands() {
        List<Hand> hands = input.stream().map(l -> new Hand(l.split(" ")[0], Integer.parseInt(l.split(" ")[1]), label_value)).toList();
        for(Hand hand: hands) {
            String type_hand = hand.determine_type_hand();
            type_hands.get(type_hand).add(hand);
        }
    }

    public static long calcule_total_winnings() {
        type_hands.forEach((key, value) -> Collections.sort(value));
        List<Hand> hands_ordered = type_hands.entrySet().stream().flatMap(kv -> Stream.of(kv.getValue())).flatMap(Collection::stream).toList();
        return IntStream.range(0, hands_ordered.size()).reduce(0, (acc, v) -> acc + hands_ordered.get(v).getBidAmount() * (v+1));
    }

    public static void main(String[] args) {
        init_hands();
        Long result = calcule_total_winnings();
        System.out.println(result);

    }

}
