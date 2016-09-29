package br.com.studies.algorithms;

public class HashEntry {
    private int key;
    private int value;

    public HashEntry(int key, int value) {
          this.key = key;
          this.value = value;
    }

    public int getValue() {
          return value;
    }

    public void setValue(int value) {
          this.value = value;
    }

    public int getKey() {
          return key;
    }
}