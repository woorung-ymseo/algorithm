# 분수의 덧셈
##### https://school.programmers.co.kr/learn/courses/30/lessons/120808

## 문제 설명
> 첫 번째 분수의 분자와 분모를 뜻하는 numer1, denom1, 두 번째 분수의 분자와 분모를 뜻하는 numer2, denom2가 매개변수로 주어집니다. <br>
> 두 분수를 더한 값을 기약 분수로 나타냈을 때 분자와 분모를 순서대로 담은 배열을 return 하도록 solution 함수를 완성해보세요. <br> 
> 제한사항 ``0 < numer1, denom1, numer2, denom2 < 1,000``
---
### 최종 풀이 - TRY 3
~~~java
class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = {
            numer1 * denom2 + numer2 * denom1, 
            denom1 * denom2
        };
        
        int divi = calcDivisor(answer[0], answer[1]);
        
        answer[0] /= divi;
        answer[1] /= divi;
        
        return answer;
    }
    
    private int calcDivisor(int a, int b) {
        int maxDivisors = 1;
        
        for (int i = a; i > 1; i--) {
            if (a % i == 0 && b % i == 0 && maxDivisors % i > 0) {
                maxDivisors *= i;
            }        
        }
        
        return maxDivisors;
    }
}
~~~
---
### TRY 1
- 15 case 중 2건 실패
~~~java
class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int mother = denom1 * denom2;
        int son = 1;
        int maxDivisors = calcDivisor(false, denom1, denom2);
        
        mother = mother/maxDivisors;
        son = mother/denom1 * numer1 + mother/denom2 * numer2;
        maxDivisors = calcDivisor(true, mother, son);
        
        int[] answer = {son/maxDivisors, mother/maxDivisors};
        return answer;
    }
    
    private int calcDivisor(boolean isMin, int a, int b) {
        int maxDivisors = 1;
        
        for (int i = 2; i < a; i++) {
            if (a % i == 0 && b % i == 0) {
                if (isMin && maxDivisors/i > 0) {
                    maxDivisors = i;
                } else {
                    maxDivisors = i;
                }
            }        
        }
        
        return maxDivisors;
    }
}
~~~
---
### TRY 2
- 성공
~~~java
class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int mother = denom1 * denom2;
        int son = 1;
        int maxDivisors = calcDivisor(false, denom1, denom2);

        mother = mother/maxDivisors;
        son = mother/denom1 * numer1 + mother/denom2 * numer2;
        maxDivisors = calcDivisor(true, mother, son);

        int[] answer = {son/maxDivisors, mother/maxDivisors};
        return answer;
    }

    private int calcDivisor(boolean isMin, int a, int b) {
        int maxDivisors = 1;

        for (int i = 2; i <= a; i++) {
            if (a % i == 0 && b % i == 0) {
                if (isMin && maxDivisors/i > 0) {
                    maxDivisors = i;
                } else {
                    maxDivisors = i;
                }
            }        
        }

        return maxDivisors;
    }
}
~~~
### TRY 3
- 성공
> 주어진 숫자로 먼저 분수의 합을 구한 후 약수로 나누는 방법으로 진행
~~~java
class Solution {
    public int[] solution(int numer1, int denom1, int numer2, int denom2) {
        int[] answer = {
            numer1 * denom2 + numer2 * denom1, 
            denom1 * denom2
        };
        
        int divi = calcDivisor(answer[0], answer[1]);
        
        answer[0] /= divi;
        answer[1] /= divi;
        
        return answer;
    }
    
    private int calcDivisor(int a, int b) {
        int maxDivisors = 1;
        
        for (int i = a; i > 1; i--) {
            if (a % i == 0 && b % i == 0 && maxDivisors % i > 0) {
                maxDivisors *= i;
            }        
        }
        
        return maxDivisors;
    }
}
~~~
