package com.github.crhcalculations.wordblitz;

import java.io.IOException;
import java.net.URI;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * TODO Comment here ... oh yes - really do this some day!!
 *
 * @author dafo
 * @since Date: Sep 1, 2014
 */
public class WordBlitz {
  private static int MAX = 16;

  private static int ELEMS = 4;

  private static Words wordlist = new Words();

  private static int value(final char c) {
    switch (c) {
      case 'a':
        return 1;
      case 'b':
        return 3;
      case 'c':
        return 2;
      case 'd':
        return 3;
      case 'e':
        return 1;
      case 'f':
        throw new IllegalStateException();
      case 'g':
        return 2;
      case 'h':
        return 2;
      case 'i':
        return 1;
      case 'j':
        throw new IllegalStateException();
      case 'k':
        return 3;
      case 'l':
        return 2;
      case 'm':
        return 3;
      case 'n':
        return 1;
      case 'o':
        return 2;
      case 'p':
        return 4;
      case 'q':
        return 10;
      case 'r':
        return 1;
      case 's':
        return 1;
      case 't':
        return 1;
      case 'u':
        return 2;
      case 'v':
        throw new IllegalStateException();
      case 'w':
        throw new IllegalStateException();
      case 'x':
        throw new IllegalStateException();
      case 'y':
        throw new IllegalStateException();
      case 'z':
        throw new IllegalStateException();
    }
    return 0;
  }

  public static void main(final String[] args) throws IOException {
    // TODO insert path to wordlist
    wordlist.fill(Files.readAllLines(Paths.get(URI.create("file:///PATHTOWORDLIST")), Charset.defaultCharset()));

    // TODO insert game data
    // field
    final String characters = "lbshkutahegnnnis";
    // fields with double character value
    final List<Integer> _2B = Arrays.asList(5, 9);
    // fields with tripple character value
    final List<Integer> _3B = Arrays.asList(4);
    // fields with double word value
    final List<Integer> _2W = Arrays.asList(1, 3);
    // fields with tripple word value
    final List<Integer> _3W = Arrays.asList(6);
    final char[] chars = characters.toCharArray();

    final List<List<Integer>> lists = new ArrayList<List<Integer>>();
    for (int i = 0; i < MAX; ++i) {
      final List<Integer> list = new ArrayList<Integer>(16);
      list.add(i);
      doit(lists, list, chars);
    }
    final Map<String, Result> results = new HashMap<String, Result>();
    for (final List<Integer> list : lists) {
      final String string = string(chars, list);
      final int value = value(chars, list, _2B, _3B, _2W, _3W);
      if (!results.containsKey(string) || results.get(string).value < value) {
        results.put(string, new Result(string, value, list));
      }
    }
    final List<Result> resultList = new ArrayList<Result>(results.values());
    Collections.sort(resultList);
    int i = 0;
    for (final Result result : resultList) {
      System.out.println(++i + ": " + result);
    }
  }

  private static class Result implements Comparable<Result> {

    private final String string;

    private final int value;

    private final List<Integer> list;

    public Result(final String string, final int value, final List<Integer> list) {
      this.string = string;
      this.value = value;
      this.list = list;
    }

    @Override
    public int compareTo(final Result o) {
      return this.value - o.value;
    }

    @Override
    public String toString() {
      return this.string + "(" + this.value + ") - " + this.list;
    }
  }

  private static int value(final char[] chars,
                           final List<Integer> list,
                           final List<Integer> _2b,
                           final List<Integer> _3b,
                           final List<Integer> _2w,
                           final List<Integer> _3w) {
    int normalValue = 0;
    for (int i = 0; i < list.size(); ++i) {
      int charValue = value(chars[list.get(i)]);
      if (_2b.contains(list.get(i))) {
        charValue *= 2;
      } else if (_3b.contains(list.get(i))) {
        charValue *= 3;
      }
      normalValue += charValue;
    }
    for (final Integer index : _2w) {
      if (list.contains(index)) {
        normalValue *= 2;
      }
    }
    for (final Integer index : _3w) {
      if (list.contains(index)) {
        normalValue *= 3;
      }
    }
    switch (list.size()) {
      case 2:
        normalValue += 3;
        break;
      case 3:
        normalValue += 4;
        break;
      case 4:
        normalValue += 6;
        break;
      case 5:
        normalValue += 9;
        break;
      case 6:
        normalValue += 11;
        break;
      case 7:
        normalValue += 14;
        break;
      case 8:
        normalValue += 17;
        break;
      case 9:
        normalValue += 19;
        break;
    }
    return normalValue;
  }

  private static void doit(final List<List<Integer>> lists, final List<Integer> arrayList, final char[] chars) {
    final int max = 16;
    if (arrayList.size() < max) {
      final int current = arrayList.get(arrayList.size() - 1);
      // go left
      if (current % ELEMS > 0) {
        checkAndDo(lists, arrayList, chars, current - ELEMS - 1);
        checkAndDo(lists, arrayList, chars, current - 1);
        checkAndDo(lists, arrayList, chars, current + ELEMS - 1);
      }
      // go up/down
      checkAndDo(lists, arrayList, chars, current - ELEMS);
      checkAndDo(lists, arrayList, chars, current + ELEMS);
      //go right
      if ((current + 1) % ELEMS > 0) {
        checkAndDo(lists, arrayList, chars, current - ELEMS + 1);
        checkAndDo(lists, arrayList, chars, current + 1);
        checkAndDo(lists, arrayList, chars, current + ELEMS + 1);
      }
    }
  }

  private static void checkAndDo(final List<List<Integer>> lists,
                                 final List<Integer> list,
                                 final char[] chars,
                                 final int index) {
    if (index >= 0 && index < MAX && !list.contains(index)) {
      final List<Integer> copy = new ArrayList<Integer>(list);
      copy.add(index);
      final String string = string(chars, copy);
      final int contains = wordlist.contains(string);
      if (contains > 0) {
        if (contains > 1) {
          lists.add(copy);
        }
        doit(lists, copy, chars);
      }
    }
  }

  private static String string(final char[] chars, final List<Integer> list) {
    final StringBuilder sb = new StringBuilder();
    for (final Integer i : list) {
      sb.append(chars[i]);
    }
    return sb.toString();
  }

  private static class Words {
    private final Node root = new Node('X');

    public void fill(final List<String> readAllLines) {
      for (final String line : readAllLines) {
        this.root.add(line);
      }
    }

    public int contains(final String begin) {
      return this.root.contains(begin);
    }
  }

  private static class Node {
    private final List<Node> children = new ArrayList<Node>(2);

    private final char identity;

    public boolean canStopHere = false;

    public Node(final char c) {
      this.identity = c;
    }

    public void add(final String line) {
      if (line.isEmpty()) {
        this.canStopHere = true;
        return;
      }
      final char c = line.charAt(0);
      Node childToAdd = null;
      for (final Node child : this.children) {
        if (child.identity == c) {
          childToAdd = child;
          break;
        }
      }
      if (childToAdd == null) {
        childToAdd = new Node(c);
        this.children.add(childToAdd);
      }
      childToAdd.add(line.substring(1));
    }

    public int contains(final String str) {
      if (str.isEmpty()) {
        if (this.canStopHere) {
          return 2;
        }
        return 1;
      }
      final char c = str.charAt(0);
      for (final Node child : this.children) {
        if (child.identity == c) {
          return child.contains(str.substring(1));
        }
      }
      return 0;
    }
  }
}
