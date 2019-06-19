// All Rights Reserved, Copyright © Paysafe Holdings UK Limited 2017. For more information see LICENSE

package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PasswordCracker {

  private static final Map<Character, List<Character>> mappings = new HashMap<>();

  static {
    mappings.put('a', Collections.singletonList('@'));
    mappings.put('e', Collections.singletonList('3'));
    mappings.put('o', Collections.singletonList('0'));
    mappings.put('1', Collections.singletonList('!'));
    mappings.put('s', Collections.singletonList('$'));
  }

  public List<String> crack(String password) {

    // [[p, P], [a, A, @], [s, S, $], [s, S, $], [w, W], [o, O, 0], [r, R], [d, D]]
    List<List<Character>> charCombinations = new ArrayList<>();

    for (Character character : password.toCharArray()) {
      List<Character> possibleCharactedSubs = new ArrayList<>();
      if (Character.isLetter(character)) {
        possibleCharactedSubs.add(Character.toLowerCase(character));
        possibleCharactedSubs.add(Character.toUpperCase(character));
      } else { // for digits, symbols, etc.
        possibleCharactedSubs.add(character);
      }

      final char lower = Character.toLowerCase(character);
      final char upper = Character.toUpperCase(character);
      if (mappings.containsKey(lower)) {
        possibleCharactedSubs.addAll(mappings.get(lower));
      } else if (mappings.containsKey(upper)) {
        possibleCharactedSubs.addAll(mappings.get(upper));
      }

      charCombinations.add(possibleCharactedSubs);
    }

    ArrayList<StringBuilder> passCombinations = new ArrayList<>();
    boolean isFirst = true;
    for (List<Character> charCombination : charCombinations) {
      ArrayList<StringBuilder> newPassCombinations = new ArrayList<>();
      for (Character character : charCombination) {
        if (isFirst) {
          newPassCombinations.add(new StringBuilder().append(character));
        } else {
          List<StringBuilder> combinationsWithThisCharacter = new ArrayList<>();
          for (StringBuilder c : passCombinations) {
            combinationsWithThisCharacter.add(new StringBuilder(c));
          }
          combinationsWithThisCharacter.forEach(sb -> sb.append(character));

          newPassCombinations.addAll(combinationsWithThisCharacter);
        }
      }
      // we do this, because `passCombinations` includes combinations which we don't need anymore
      passCombinations = newPassCombinations;

      isFirst = false;
    }

    return passCombinations.stream()
        .map(StringBuilder::toString)
        .collect(Collectors.toList());
  }
}
