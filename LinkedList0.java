import java.util.Scanner;
import java.util.*;
import java.util.ArrayList;
import java.io.*;
import java.io.PrintWriter;
/**
 * this is my Caesar Cipher
 *
 * @author Tomas Moshi
 * @version 1.0
 */
public class LinkedList0
{
    private class Node{
        private char letter;
        private Node next;
        public Node(char l, Node n)
        {
            letter = l;
            next = n;
        }

        public Node getNode()
        {
            return next;
        }

        public char getLetter()
        {
            return letter;
        }

        public void setNode(Node n)
        {
            next = n;
        }

        public void setLetter(char l)
        {
            letter = l;
        }

        public Node(char l)
        {
            letter = l;
            next = null;
        }

    }

    private Node first = null;
    public void setFirst(Node f)
    {
        first = f;
    }

    public Node getFirst()
    {
        return first;
    }

    public LinkedList0()
    {   
        char[] alphabet = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H',
                'I', 'J', 'K', 'L', 'M','N', 'O', 'P', 'Q', 'R', 'S',
                'T', 'U', 'V', 'W', 'X', 'Y', 'Z'};

        Node temp = null;
        for (int i = alphabet.length-1; i >= 0; i--) {
            temp = new Node(alphabet[i],temp);
        }
        first = temp;
    }

    public void resetAlphabet(ArrayList<Character> alphabet)
    {
        char letter = 'A';
        Node temp = first;
        alphabet.clear();
        for (int i = 0; i < 26; i++){
            alphabet.add(letter);
            temp.letter = letter;
            temp = temp.next;
            letter++;
        }

    }

    public boolean checkUsed(ArrayList<Character> alph,char c,int w)
    {

        for (int i = 0; i < w; i++) {
            if (alph.get(i) == c)// || temp.letter == c)
                return true;
        }
        return false;
    }

    public void shiftAlphabet(ArrayList<Character> alph, String word) {
        int used = 0;
        for (int i = 0; i < word.length(); i++){
            if(!checkUsed(alph,word.charAt(i),used))
            {
                int j = 0;
                for(j=used;alph.get(j)!=word.charAt(i);j++);
                for(;j>used;j--)
                {
                    alph.set(j,alph.get(j-1));
                }
                alph.set(j, word.charAt(i));
                used++;
            }
        }
    }

    public void print()
    {
       Node ref = first;
        while(ref!=null){
            System.out.print(ref.letter + " ");
            ref = ref.next;
        }
    }

    public void printList(ArrayList<Character> alph) 
    {
        for (char c : alph)
            System.out.print(c + " ");
        System.out.println();   
    }

    public static String encryptMessage(ArrayList<Character> alph, ArrayList<Character> alph2, String word)
    {
        String reply = "";
        
        for (int i = 0; i < word.length(); i++)
        {
            int charPosition = alph2.indexOf(word.charAt(i));
            System.out.println("CharPos is : " + charPosition);
            int keyVal = (charPosition % 26);
            reply += alph.get(keyVal);

        }
        
        return reply;
    }

    public String decryptMessage(ArrayList<Character> alph, ArrayList<Character> alph2, String word)
    {
        String reply = "";
        
        for(int i = 0; i<word.length();i++){
            int charPosition = alph.indexOf(word.charAt(i));
            System.out.println("CharPos is: " + charPosition);
            int keyVal = (charPosition % 26);
            reply += alph.get(keyVal);
        }
        
        return reply;
    }

    public static void main(String[] args) throws IOException{

        Scanner keyboard = new Scanner(System.in);
        String word;
        char repeat;
        String input = "y";
        do{

            LinkedList0 list1 = new LinkedList0();

            ArrayList<Character> list2 = new ArrayList<>(26);
            ArrayList<Character> alphabet = new ArrayList<>(26);

            System.out.print("1. Enter key: ");
            word = keyboard.next().toUpperCase();
            keyboard.nextLine();

            list1.resetAlphabet(alphabet);
            list1.resetAlphabet(list2);
            list1.printList(list2);
            list1.shiftAlphabet(list2, word);
            list1.printList(list2);

            System.out.print("2. Enter word to encrypt: ");
            String eWord = keyboard.next().toUpperCase();

            String temp = list1.encryptMessage(list2,alphabet,eWord);
            System.out.println("Encrypted word is " + temp);

            System.out.println("3. Decrypted word is: ");
            String decrypt = list1.decryptMessage(list2,alphabet,eWord);

            System.out.println("Decrypted message is: " + decrypt);

            Scanner yesno = new Scanner(System.in);
            System.out.println("Enter Y for yes or N for no to try another word: ");
            input = yesno.nextLine().toUpperCase();    
            repeat = (char)input.charAt(0);

        }while (repeat == 'Y');
    }
}