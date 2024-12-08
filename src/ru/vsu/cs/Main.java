package ru.vsu.cs;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        int[] a = solve(ArrayUtils.readIntArrayFromConsole());
        System.out.println("Индекс первого элемента и длинна самой большой подпоследовательности:");
        System.out.println(ArrayUtils.toString(a));
        // test();

    }
    public static int[] solve(int[] list) {
        int[] answer = {0, 3}; // самая первая подпоследовательность длинной <= 3 всегда подходит под условие

        if (list.length <= 3) {
            answer[1] = list.length; // в случае, если длина массива <= 3, то меняем начальный ответ
            return answer;
        }

        int l = 0;
        for (int r = 4; r <= list.length; r++) { // здесь r - правая граница подпосл-ти
            int[] copyList = Arrays.copyOfRange(list, l, r);
            if (checkCondition(copyList)) {
                // if (r - l > answer[1]) { // переписываем ответ только тогда, когда длина БОЛЬШЕ прошлой (закоментил, т к это необязательная проверка)
                answer[0] = l;
                answer[1] = r - l;
                // }
            } else {
                // если не прошло условие, то нам нет смысла проверять остальные длинны начиная с данного элемента,
                // поэтому перемещаем левую границу
                l++;
            }
        }
        return answer;
    }
    public static boolean checkCondition(int[] s) {
        Integer[] sub = ArrayUtils.toObject(s);
        Map<Integer, Integer> elements = new HashMap<>();
        for (Integer i : sub){ // преобразование подпоследовательности в ассоциативную коллекцию Map
            // elements.put(i, elements.get(i) + 1);
            if (elements.get(i) == null) {
                elements.put(i, 1);
            } else {
                elements.put(i, elements.get(i) + 1);
            }

            if (elements.size() > 3) {
                return false;
            }
        }
        // проверяем, если в подпоследовательности дольше двух элементов отличаются от "основного"(максимального)
        if (Collections.max(elements.values()) < s.length - 2) {
            return false;
        }

        return true;
    }

    public static void test(){
        int[] a1 = {4, 5, 3, 3, 7, 3, 3, 7, 6, 4, 6, 7, 7, 7, 7, 1}; // (1, 6)
        int[] a2 = {1, 2, 3, 2, 2, 1, 3, 3, 3}; // (0, 5)
        int[] a3 = {10, 10}; // (0, 2)
        int[] a4 = {}; // (0, 0)
        int[] a5 = {5, 4, 2, 2, 1, 1, 1, 1, 1, 2}; // (2, 7)
        int[] a6 = {0, 0, 0, 0, 0, 0, 0}; // (0, 7)
        int[] a7 = {9, 8, 7, 1, 1, 1, 1, 11, 1, 1, 9, 1, 1}; // (3, 10)
        int[] a8 = {1, 2, 2, 3, 3, 3, 4, 4, 5, 5}; // (1, 5)
        System.out.println(ArrayUtils.toString(solve(a1)));
        System.out.println(ArrayUtils.toString(solve(a2)));
        System.out.println(ArrayUtils.toString(solve(a3)));
        System.out.println(ArrayUtils.toString(solve(a4)));
        System.out.println(ArrayUtils.toString(solve(a5)));
        System.out.println(ArrayUtils.toString(solve(a6)));
        System.out.println(ArrayUtils.toString(solve(a7)));
        System.out.println(ArrayUtils.toString(solve(a8)));
    }
}