/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author hoang
 */
public class Controller {

    // cộng 2 ma trận
    public int[][] additionMatrix(int[][] matrix1, int[][] matrix2) {
        int row = matrix1.length;
        int col = matrix1[0].length;
        int row2 = matrix2.length;
        int col2 = matrix2[0].length;
        int[][] matrixResult = new int[row][col];
        //xét 2 ma trận đồng cấp
        if (col != col2 || row != row2) {
            return null;
        }
        //duyệt mảng để cộng 2 ma trận
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrixResult[i][j] = matrix1[i][j] + matrix2[i][j];
            }
        }
        return matrixResult;
    }

    // tru 2 ma trận
    public int[][] subtractionMatrix(int[][] matrix1, int[][] matrix2) {
        int row = matrix1.length;
        int col = matrix1[0].length;
        int row2 = matrix2.length;
        int col2 = matrix2[0].length;
        int[][] matrixResult = new int[row][col];
        //check 2 ma trận đồng cấp
        if (col != col2 || row != row2) {
            return null;
        }
        //duyêt mảng để trừ 2 ma trận
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < col; j++) {
                matrixResult[i][j] = matrix1[i][j] - matrix2[i][j];
            }
        }
        return matrixResult;
    }

    public int[][] multiplicationMatrix(int[][] matrix1, int[][] matrix2) {
        int row1 = matrix1.length;
        int col1 = matrix1[0].length;
        int row2 = matrix2.length;
        int col2 = matrix2[0].length;
        // số cột của ma trận 1 phải bằng số hàng của ma trận 2
        // set k bằng trả về null
        if (col1 != row2) {
            return null;
        }
        int[][] matrixResult = new int[row1][col1];
        //
        for (int i = 0; i < row1; i++) {
            for (int j = 0; j < col2; j++) {
                for (int k = 0; k < col1; k++) {
                    matrixResult[i][j] += matrix1[i][k] * matrix2[k][j];
                }
            }
        }
        return matrixResult;
    }
}
