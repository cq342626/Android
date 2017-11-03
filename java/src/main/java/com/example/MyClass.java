package com.example;



import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

public class MyClass {


    public static void main(String[] args) throws ScriptException {
        one(new int[]{5, 2, 41});
        two(new Object[]{1, 2, 3, 4, 5}, new Object[]{"a", "b", "c", "d", "e"});
        three();
        four(new int[]{45, 54, 85, 446, 8911});
        five();
    }

    /**
     * 三种循环计算数列之间的总和
     *
     * @param a
     */
    private static void one(int[] a) {
        if (a != null && a.length > 1) {
            int all = 0;
            for (int i = 0; i < a.length; i++) {
                all += a[i];
            }
            System.out.println(all);
        }
        if (a != null && a.length > 1) {
            int i = 0;
            int all = 0;
            while (i < a.length) {
                all += a[i];
                i++;
            }
            System.out.println(all);
        }
        if (a != null && a.length > 1) {
            int i = 0;
            int all = 0;
            do {
                all += a[i];
                i++;
            } while (i < a.length);
            System.out.println(all);
        }
    }

    /**
     * @param a 1 2 3 4
     * @param b a b c d
     *          <p>
     *          两个数组对差合并
     */
    private static void two(Object[] a, Object[] b) {
        Object[] c = new Object[a.length + b.length];
        for (int i = 0; i < a.length + b.length; i++) {
            if (i % 2 == 0) { // 0 2 4
                c[i] = a[(i / 2)];
            } else {  // 1 3 5
                c[i] = b[(i - 1) / 2];
            }
            System.out.println(c[i]);
        }

    }

    /**
     * 前两个数字之和等于下一个数字结合
     */
    private static void three() {
        int[] x = new int[]{0, 0};
        int[] y = new int[]{0, 1};
        for (int i = 0; i < 98; i++) {
            if (x[0] == 0 && y[0] == 0) {
                // 补0，保持他们的长度一致
                if (x.length + 1 == y.length) {
                    int[] xx = new int[y.length];
                    xx[0] = 0;
                    for (int j = 1; j < y.length; j++) {
                        xx[j] = x[j - 1];
                    }
                    x = xx;
                }
                int[] z = new int[y.length];
                boolean isAdd = false;
                for (int j = z.length - 1; j >= 0; j--) {
                    int k = x[j] + y[j];
                    if (isAdd)
                        k++;
                    if (k >= 10) {
                        z[j] = k % 10;
                        isAdd = true;
                    } else {
                        z[j] = k;
                        isAdd = false;
                    }
                }
                // 这里可以算出来加过的和了，可以输出，但是下面需要判断z[0]是不是0，如果不是就补一个0；以方便换算
                String out = "";
                for (int j = 0; j < z.length; j++) {
                    out += z[j];
                }
                if (z[0] == 0) {
                    out = out.substring(1);
                }
                System.out.println((i + 3) + ":  " + out);
                if (z[0] != 0) {
                    int[] zz = new int[z.length + 1];
                    zz[0] = 0;
                    for (int j = 1; j < zz.length; j++) {
                        zz[j] = z[j - 1];
                    }
                    z = zz;
                }

                // 赋值，y赋值给x，z赋值给y
                x = y;
                y = z;

            } else {


            }
        }
    }

    /**
     * 一组数组中，将每个子项连接起来时，获取最大的值
     *
     * @param a
     */
    private static void four(int[] a) {
        int n = a.length;
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int two = a[j];
                if (isMax(a[i], a[j])) {
                    a[j] = a[i];
                    a[i] = two;
                }
            }
        }
        String all = "";
        for (int i = 0; i < n; i++) {
            all += a[i] + "";
            System.out.println(a[i]);
        }
        System.out.println(all);
    }

    private static boolean isMax(int a, int b) {
        String aa = String.valueOf(a);
        String bb = String.valueOf(b);
        return Integer.valueOf(aa + bb) < Integer.valueOf(bb + aa);
    }

    /**
     * 1到9之间，加上+-或者不算数据
     * @throws ScriptException
     */
    private static void five() throws ScriptException {
        String[] str = new String[]{"+", "-", ""};
        for (int a = 0; a < 3; a++) {
            for (int b = 0; b < 3; b++) {
                for (int c = 0; c < 3; c++) {
                    for (int d = 0; d < 3; d++) {
                        for (int e = 0; e < 3; e++) {
                            for (int f = 0; f < 3; f++) {
                                for (int g = 0; g < 3; g++) {
                                    for (int h = 0; h < 3; h++) {
                                        String result = 1 + str[a] + 2 + str[b] + 3 + str[c] + 4 + str[d] + 5 + str[e] +
                                                6 + str[f] + 7 + str[g] + 8 + str[h] + 9;
                                        if (getSum(result) == 100) {
                                            System.out.println("-----=: " + result);
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    public static int getSum(String result) throws ScriptException {
        ScriptEngine scriptEngine = new ScriptEngineManager().getEngineByName("JavaScript");
        Object d =  scriptEngine.eval(result);
        return (int) d;
    }
}
