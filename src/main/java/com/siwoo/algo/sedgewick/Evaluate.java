package com.siwoo.algo.sedgewick;

import com.siwoo.algo.sedgewick.collection.ArrayStack;
import com.siwoo.algo.sedgewick.collection.Stack;

import java.util.function.BiFunction;

/**
 * E.W. dijkstra 의 2 중 스택 산술표현식 연산 알고리즘
 *
 *  피연산자 p 을 위한 스택을 ps, 연산자 o 를 위한 스택을 os 라 정의하자.
 *  
 *  산술표현식 s 에 대해 아래를 수행한다.
 *  
 *  1. p 을 만나면 ps 에 넣는다.
 *  2. o 을 만나면 os 에 넣는다.
 *  3. 열린 괄호 '(' 을 만나면 무시한다.
 *  4. 닫힌 괄호 ')' 을 만나면 o.top 과 p.top, p.top.top 을 꺼내 계산한 후 ps 에 넣는다.
 *  
 */
public class Evaluate {
    private final double result;
    private final String[] op = {"+", "-", "*", "/", "sqrt"};
    private final BiFunction<Double, Double, Double>[] opf = 
            new BiFunction[]{
                    (e1, e2) -> (double) e1 + (double) e2,
                    (e1, e2) -> (double) e1 - (double) e2,
                    (e1, e2) -> (double) e1 * (double) e2,
                    (e1, e2) -> (double) e1 / (double) e2,
                    (e1, e2) -> Math.sqrt((double) e1),
            };
    
    public Evaluate(String s) {
        String exp[] = s.split("\\s+");
        Stack<String> os = new ArrayStack<>();
        Stack<Double> ps = new ArrayStack<>();
        for (String e: exp) {
            if (getOperator(e) != -1)
                os.push(e);
            else if (isNumber(e))
                ps.push(Double.parseDouble(e));
            else if (")".equals(e)) {
                String o = os.pop();
                Double v1 = null, v2 = null;
                if (o.equals("sqrt"))
                    v1 = ps.pop();
                else {
                    v2 = ps.pop();
                    v1 = ps.pop();
                }
                for (int i=0; i<op.length; i++) {
                    if (o.equals(op[i])) {
                        ps.push(opf[i].apply(v1, v2));
                        break;
                    }
                }
            }
        }
        result = ps.pop();
    }

    private boolean isNumber(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private int getOperator(String s) {
        for (int i=0; i<op.length; i++) {
            if (s.equals(op[i]))
                return i;
        }
        return -1;
    }

    public static void main(String[] args) {
        String s = "( 1 + ( ( 2 + 3 ) * ( 4 * 5 ) ) )";
        Evaluate evaluate = new Evaluate(s);
        
        System.out.println(evaluate.result);
        s = "( ( 1 + sqrt ( 5.0 ) ) / 2.0 )";
        evaluate = new Evaluate(s);
        System.out.println(evaluate.result);
    }
}
