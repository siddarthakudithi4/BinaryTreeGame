import java.io.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
//from task2 import *;
class Node {
    String data;
    Node left;
    Node right;

    public Node(String data) {
        this.data = data;
        this.left = null;
        this.right = null;
    }
}

public class AnimalGuessingGame {

    private Node root;
    private BufferedWriter writer;

    public AnimalGuessingGame() {
        // Initialize the binary tree structure with questions and animal names
        root = new Node("Is it a mammal?");
        root.left = new Node("Can it fly?");
        root.right = new Node("Does it live in water?");

        root.left.left = new Node("Is it a domesticated animal?");
        root.left.right = new Node("Is it a bird?");
        root.right.left = new Node("Is it a fish?");
        root.right.right = new Node("Is it a reptile?");

        root.left.left.left = new Node("Is it a cat?");
        root.left.left.right = new Node("Is it a dog?");
        root.left.right.left = new Node("Is it a parrot?");
        root.left.right.right = new Node("Is it an eagle");

        root.right.left.left = new Node("Is it a goldfish?");
        root.right.left.right = new Node("Is it a shark?");

        root.right.right.left = new Node("Is it a snake?");
        root.right.right.right = new Node("Is it a turtle?");

        // Initialize the BufferedWriter for writing to the file
        try {
            writer = new BufferedWriter(new FileWriter("./animal_guessing_game_log.txt"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Node getRoot() {
        return root;
    }

    public void log(String message) {
        try {
            writer.write(message);
            writer.newLine();
            writer.flush(); // Added to flush the buffer and ensure data is written immediately
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void play(Node currentNode) {
        Scanner scanner = new Scanner(System.in);

        try {
            while (currentNode.left != null) {
                System.out.println("Node Address: " + currentNode.hashCode());
                System.out.println(currentNode.data);
                log("Node Address: " + currentNode.hashCode());
                log(currentNode.data);

                String answer = scanner.nextLine().toLowerCase();
                log(answer);

                if (answer.equals("yes")) {
                    currentNode = currentNode.left;
                } else if (answer.equals("no")) {
                    currentNode = currentNode.right;
                } else {
                    System.out.println("Invalid input. Please answer with 'Yes' or 'No'.");
                    writer.close();
                    return;
                }
            }

            System.out.println("Node Address: " + currentNode.hashCode());
            System.out.println("Is it the animal " + currentNode.data + "?");
            log("Node Address: " + currentNode.hashCode());
            log("Is it the animal " + currentNode.data + "?");

            String finalAnswer = scanner.nextLine().toLowerCase();
            log(finalAnswer);

            if (finalAnswer.equals("yes")) {
                System.out.println("Yay! I guessed the animal!");
            } else {
                System.out.println("Oh no! I need to improve my knowledge.");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        AnimalGuessingGame game = new AnimalGuessingGame();
        System.out.println("Welcome to the Animal Guessing Game!");
        System.out.println("Think of an animal, and I will try to guess it.");
        game.play(game.getRoot());
    }
}