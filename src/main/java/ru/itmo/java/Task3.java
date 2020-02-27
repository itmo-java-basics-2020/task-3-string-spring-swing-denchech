package ru.itmo.java;

import java.lang.Character;
import java.lang.Integer;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.UnsupportedOperationException;
import java.lang.reflect.Array;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Task3 {

    /**
     * Напишите функцию, которая принимает массив целых чисел и циклически сдвигает элементы этого массива вправо:
     * A[0] -> A[1], A[1] -> A[2] .. A[length - 1] -> A[0].
     * Если инпут равен null - вернуть пустой массив
     */
    int[] getShiftedArray(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0){
            return new int[0];
        }
        int[] answer = new int[inputArray.length];
        answer[0] = inputArray[inputArray.length - 1];
        for (int i = 1; i < inputArray.length; i++){
            answer[i] = inputArray[i - 1];
        }
        return answer;
    }

    /**
     * Напишите функцию, которая принимает массив целых чисел и возвращает максимальное значение произведения двух его элементов.
     * Если массив состоит из одного элемента, то функция возвращает этот элемент.
     *
     * Если входной массив пуст или равен null - вернуть 0
     *
     * Пример: 2 4 6 -> 24
     */
    int getMaxProduct(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0){
            return 0;
        }
        if (inputArray.length == 1){
            return inputArray[0];
        }
        if (inputArray.length == 2){
            return inputArray[0] * inputArray[1];
        }
        int max = Integer.MIN_VALUE;
//        for (int i = 0; i < inputArray.length; i++){
//            for (int j = i + 1; j < inputArray.length; j++){
//                max = Integer.max(max, inputArray[i] * inputArray[j]);
//            }
//        }
        int maxNegative1 = Integer.MIN_VALUE;
        int indexMaxNegative = -1;
        for (int i = 0; i < inputArray.length; i++){
            if (inputArray[i] < 0 && inputArray[i] > maxNegative1 && i != indexMaxNegative){
                maxNegative1 = inputArray[i];
                indexMaxNegative = i;
            }
        }
        int maxNegative2 = Integer.MIN_VALUE;
        for (int i = 0; i < inputArray.length; i++){
            if (inputArray[i] < 0 && inputArray[i] > maxNegative2 && i != indexMaxNegative){
                maxNegative2 = inputArray[i];
            }
        }
        int maxPositive1 = -1;
        int indexMaxPositive = -1;
        for (int i = 0; i < inputArray.length; i++){
            if (inputArray[i] >= 0 && inputArray[i] > maxPositive1 && i != indexMaxPositive){
                maxPositive1 = inputArray[i];
                indexMaxPositive = i;
            }
        }
        int maxPositive2 = -1;
        for (int i = 0; i < inputArray.length; i++){
            if (inputArray[i] >= 0 && inputArray[i] > maxPositive2 && i != indexMaxPositive){
                maxPositive2 = inputArray[i];
            }
        }

        return Integer.max(maxNegative1 * maxNegative2, maxPositive1 * maxPositive2);
    }

    /**
     * Напишите функцию, которая вычисляет процент символов 'A' и 'B' (латиница) во входной строке.
     * Функция не должна быть чувствительна к регистру символов.
     * Результат округляйте путем отбрасывания дробной части.
     *
     * Пример: acbr -> 50
     */
    int getABpercentage(String input) {
        if (input == null || input.isEmpty()){
            return 0;
        }
        int count = 0;
        for (int i = 0; i < input.length(); i++){
            if (Character.toLowerCase(input.charAt(i)) == 'a' || Character.toLowerCase(input.charAt(i)) == 'b'){
                count++;
            }
        }
        return (count * 100 / input.length());
    }

    /**
     * Напишите функцию, которая определяет, является ли входная строка палиндромом
     */
    boolean isPalindrome(String input) {
        if (input == null){
            return false;
        }
        for (int i = 0; i < input.length() / 2; i++){
            if (input.charAt(i) != input.charAt(input.length() - 1 - i)){
                return false;
            }
        }
        return true;
    }

    /**
     * Напишите функцию, которая принимает строку вида "bbcaaaak" и кодирует ее в формат вида "b2c1a4k1",
     * где группы одинаковых символов заменены на один символ и кол-во этих символов идущих подряд в строке
     */
    String getEncodedString(String input) {
        if (input == null || input.isEmpty()){
            return "";
        }
        char symbol = input.charAt(0);
        int count = 0;
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < input.length(); i++){
            if (symbol != input.charAt(i)){
                sb.append(symbol);
                sb.append(count);
                symbol = input.charAt(i);
                count = 1;
            } else{
                count++;
            }
        }
        sb.append(symbol);
        sb.append(count);
        return sb.toString();
    }

    /**
     * Напишите функцию, которая принимает две строки, и возвращает true, если одна может быть перестановкой (пермутатсией) другой.
     * Строкой является последовательность символов длинной N, где N > 0
     * Примеры:
     * isPermutation("abc", "cba") == true;
     * isPermutation("abc", "Abc") == false;
     */
    boolean isPermutation(String one, String two) {
        if (one == null || two == null){
            return false;
        }
        if (one.isEmpty() || two.isEmpty()){
            return false;
        }
        Map<Character, Integer> oneSymbols = new TreeMap<Character, Integer>();
        Map<Character, Integer> twoSymbols = new TreeMap<Character, Integer>();
        fillMap(one, oneSymbols);
        fillMap(two, twoSymbols);
        return oneSymbols.equals(twoSymbols);
    }

    private void fillMap(String str, Map<Character, Integer> symbols) {
        for (int i = 0; i < str.length(); i++){
            symbols.merge(str.charAt(i), 1, Integer::sum);
        }
    }

    /**
     * Напишите функцию, которая принимает строку и возвращает true, если она состоит из уникальных символов.
     * Из дополнительной памяти (кроме примитивных переменных) можно использовать один массив.
     * Строкой является последовательность символов длинной N, где N > 0
     */
    boolean isUniqueString(String s) {
        if (s == null || s.isEmpty()){
            return false;
        }
        for (int i = 0; i < s.length(); i++){
            for (int j = i + 1; j < s.length(); j++){
                if (s.charAt(i) == s.charAt(j)){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Напишите функцию, которая транспонирует матрицу. Только квадратные могут быть на входе.
     *
     * Если входной массив == null - вернуть пустой массив
     */
    int[][] matrixTranspose(int[][] m) {
        if (m == null){
            return new int[][]{{}, {}};
        }
//        int[][] answer = new int[m.length][m.length];
        for (int i = 0; i < m.length; i++){
            for (int j = i; j < m[i].length; j++){
                int tmp = m[i][j];
                m[i][j] = m[j][i];
                m[j][i] = tmp;
            }
        }
        return m;
    }

    /**
     * Напиишите функцию, принимающую массив строк и символ-разделитель,
     * а возвращает одну строку состоящую из строк, разделенных сепаратором.
     *
     * Запрещается пользоваться функций join
     *
     * Если сепаратор == null - считайте, что он равен ' '
     * Если исходный массив == null -  вернуть пустую строку
     */
    String concatWithSeparator(String[] inputStrings, Character separator) {
        if (inputStrings == null || inputStrings.length == 0){
            return "";
        }
        if (separator == null){
            separator = ' ';
        }
        StringBuilder sb = new StringBuilder("");
        for (int i = 0; i < inputStrings.length - 1; i++){
            sb.append(inputStrings[i]);
            sb.append(separator);
        }
        sb.append(inputStrings[inputStrings.length - 1]);
        return sb.toString();
    }

    /**
     * Напишите функцию, принимающую массив строк и строку-перфикс и возвращающую кол-во строк массива с данным префиксом
     */
    int getStringsStartWithPrefix(String[] inputStrings, String prefix) {
        if (inputStrings == null || inputStrings.length == 0 || prefix == null){
            return 0;
        }
        int count = 0;
        for (int i = 0; i < inputStrings.length; i++){
            if (inputStrings[i].startsWith(prefix)){
                count++;
            }
        }
        return count;
    }
}
