package submisson;

import java.util.*;

public class Expression_Tree {
    
    // A node in the binary expression tree
    static class Node {
        char val; // operator or variable
        Node left; // left child
        Node right; // right child
        
        public Node(char val) {
            this.val = val;
            this.left = null;
            this.right = null;
        }
    }
    
    // Main method
    public static void main(String[] args) {
        // Read in an arithmetic expression from the user
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter an arithmetic expression: ");
        String expr = sc.nextLine();
        
        // Convert the expression to a binary expression tree
        Node root = buildTree(expr);
        
        // Display the tree
        displayTree(root, 0);
        
        // Evaluate the expression and print the result
        int result = eval(root);
        System.out.println("Result = " + result);
    }
    
    // Convert a fully parenthesized arithmetic expression to a binary expression tree
    public static Node buildTree(String expr) {
        Stack<Node> stack = new Stack<>();
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(') {
                // Do nothing
            } else if (c == '+' || c == '-' || c == '*' || c == '/') {
                Node node = new Node(c);
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid expression: missing operand");
                }
                node.right = stack.pop();
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid expression: missing operand");
                }
                node.left = stack.pop();
                stack.push(node);
            } else if (Character.isAlphabetic(c)) {
                Node node = new Node(c);
                stack.push(node);
            } else if (Character.isDigit(c)) {
                int num = c - '0';
                while (i + 1 < expr.length() && Character.isDigit(expr.charAt(i + 1))) {
                    num = num * 10 + (expr.charAt(i + 1) - '0');
                    i++;
                }
                Node node = new Node((char) ('0' + num));
                stack.push(node);
            } else if (c == ')') {
                if (stack.isEmpty()) {
                    throw new IllegalArgumentException("Invalid expression: unmatched parenthesis");
                }
                stack.pop();
            } else {
                throw new IllegalArgumentException("Invalid character: " + c);
            }
        }
        if (stack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression: empty tree");
        }
        Node root = stack.pop();
        if (!stack.isEmpty()) {
            throw new IllegalArgumentException("Invalid expression: unmatched parenthesis");
        }
        return root;
    }
    
    // Display a binary expression tree in a human-readable format
    public static void displayTree(Node root, int indent) {
        if (root == null) {
            return;
        }
        displayTree(root.right, indent + 4);
        for (int i = 0; i < indent; i++) {
            System.out.print(" ");
        }
        System.out.println(root.val);
        displayTree(root.left, indent + 4);
    }
    
    // Evaluate a binary expression tree
    public static int eval(Node root) {
        if (root == null) {
            return 0;
        }
        if (Character.isDigit(root.val)) {
            return root.val - '0';
        }
        int leftVal = eval(root.left);
        int rightVal = eval(root.right);
        switch (root.val) {
            case '+':
                return leftVal + rightVal;
            case '-':
                return leftVal - rightVal;
            case '*':
                return leftVal * rightVal;
            case '/':
                return leftVal / rightVal;
            default:
                return 0;
        }
    }
}