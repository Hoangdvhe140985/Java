/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;

/**
 *
 * @author hoang
 */
public class View {

    Controller c = new Controller();

    public int[][] inputMatrix(int n) {
        System.out.print("Enter Row Matrix: ");
        int row = Validate.checkIntLimit(1, 100);
        System.out.print("Enter Colum Matrix: ");
        int col = Validate.checkIntLimit(1, 100);
        int[][] matrix = new int[row][col];
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("Enter Matrix" + n + "[" + i + "]" + "[" + j + "]:");
                matrix[i][j] = Validate.checkIntLimit(Integer.MIN_VALUE, Integer.MAX_VALUE);
            }
        }
        return matrix;
    }

    public void displayMatrix(int[][] matrix) {
        int row = matrix.length;
        int col = matrix[0].length;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                System.out.print("[" + matrix[i][j] + "]");
            }
            System.out.println();
        }
    }

    // cộng ma trận
    public void addition() {
        System.out.println("-------Addition-------");
        // nhập ma trận
        int[][] matrix1 = inputMatrix(1);
        int[][] matrix2 = inputMatrix(2);
        // hiển thị ma trận
        displayMatrix(matrix1);
        System.out.println("+");
        displayMatrix(matrix2);
        System.out.println("=");
        //nếu kêt quả khác null trả kq ra main
        if (c.additionMatrix(matrix1, matrix2) != null) {
            displayMatrix(c.additionMatrix(matrix1, matrix2));
        } else {
            System.out.println("Can't Addition");
        }
        System.out.println();
    }

    // trừ ma trận
    public void subtraction() {
        System.out.println("-------Subtraction-------");
        int[][] matrix1 = inputMatrix(1);
        int[][] matrix2 = inputMatrix(2);
        displayMatrix(matrix1);
        System.out.println("-");
        displayMatrix(matrix2);
        System.out.println("=");
        if (c.subtractionMatrix(matrix1, matrix2) != null) {
            displayMatrix(c.subtractionMatrix(matrix1, matrix2));
        } else {
            System.out.println("Can't Subtraction");
        }
        System.out.println();
    }

    // nhân ma trận
    public void Multiplication() {
        System.out.println("-------Multiplication-------");
        int[][] matrix1 = inputMatrix(1);
        int[][] matrix2 = inputMatrix(2);
        displayMatrix(matrix1);
        System.out.println("*");
        displayMatrix(matrix2);
        System.out.println("=");
        if (c.multiplicationMatrix(matrix1, matrix2) != null) {
            displayMatrix(c.multiplicationMatrix(matrix1, matrix2));
        } else {
            System.out.println("Can't Multiplication");
        }
        System.out.println();
    }

    private void display() {
        while (true) {
            System.out.println("1. Addition Matrix");
            System.out.println("2. Subtraction Matrix");
            System.out.println("3. Multiplication Matrix");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = Validate.checkIntLimit(1, 4);
            switch (choice) {
                case 1:
                    addition();
                    break;
                case 2:
                    subtraction();
                    break;
                case 3:
                    Multiplication();
                    break;
                case 4:
                    return;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        View v = new View();
        v.display();
    }
}
