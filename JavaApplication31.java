package javaapplication31;

import java.util.ArrayList;
import java.util.Scanner;
//ABAABABAABBBBBBBBBBBBA

class Tag {

    private int position;
    private int length;
    private char nextSymbol;

    public Tag(int position, int length, char nextSymbol) {
        this.position = position;
        this.length = length;
        this.nextSymbol = nextSymbol;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public char getNextSymbol() {
        return nextSymbol;
    }

    public void setNextSymbol(char nextSymbol) {
        this.nextSymbol = nextSymbol;
    }

    public void showTag() {

        System.out.println("<" + getPosition() + "," + getLength() + "," + getNextSymbol() + ">");

    }
}

public class JavaApplication31 {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        String input;
        int index = 0;
        String Dictionary = "";
        String comparing = "";
        int position = 0;
        int length = 0;
        ArrayList<Tag> Tags = new ArrayList();
        int countingTags = 0;
        System.out.println("enter the data you want to compress");
        input = scan.next();
        char nextChar = input.charAt(0);
        comparing = comparing + input.charAt(0);
        for (int i = 0; i < input.length(); i++) {
            if (Dictionary.contains(comparing) == false) {
                nextChar = input.charAt(i);
                Tag tag1 = new Tag(position, length, nextChar);
                Tags.add(tag1);
                position = 0;
                length = 0;
                Dictionary = Dictionary + comparing;
                comparing = "";
                if (i == input.length() - 1) {
                    comparing = comparing + " ";
                    nextChar = (char) 0;
                } else {
                    comparing = comparing + input.charAt(i + 1);
                }
                countingTags++;
                index = i + 1;
            } else {
                position = index - Dictionary.lastIndexOf(comparing);
                length = comparing.length();
                if (i == input.length() - 1) {
                    nextChar = (char) 0;
                    Tag tag1 = new Tag(position, length, nextChar);
                    Tags.add(tag1);
                    countingTags++;
                    comparing = comparing + " ";
                } else {
                    comparing = comparing + input.charAt(i + 1);

                }
            }

        }
        System.out.println("Tags after compression");
        System.out.println("no.of Tags" + countingTags);
        for (int i = 0; i < countingTags; i++) {
            Tags.get(i).showTag();
        }
        System.out.println("decompressioning the tags");
        String decompress = "";
        int start = 0, end = 0;
        for (int i = 0; i < countingTags; i++) {
            if (Tags.get(i).getPosition() == 0 && Tags.get(i).getLength() == 0) {
                decompress = decompress + Tags.get(i).getNextSymbol();
            } else {
                start = decompress.length() - Tags.get(i).getPosition();
                for (int j = 0; j < Tags.get(i).getLength(); j++) {
                    decompress = decompress + decompress.charAt(start);
                    start++;
                }
                decompress = decompress + Tags.get(i).getNextSymbol();
            }
        }
        System.out.println(decompress);
    }
}
