import java.util.*;

public class StackQueue {
    public static void main(String[] args) {
        StackQueue sq = new StackQueue();

        // 같은 숫자는 싫어
//        int[] arr = {1, 1, 3, 3, 0, 1, 1};
//        int[] ret = sq.solution(arr);
//        System.out.println(Arrays.toString(ret));

        // 기능개발
//        int[] progresses = {93, 30, 55};
//        int[] speeds = {1, 30, 5};
//        int[] progresses = {95, 90, 99, 99, 80, 99};
//        int[] speeds = {1, 1, 1, 1, 1, 1};
//        int[] ret = sq.solution2(progresses, speeds);
//        System.out.println(Arrays.toString(ret));

        // 올바른 괄호
        String s = "()()";
//        String s = "(())()";
//        String s = ")()(";
//        String s = "(()(";
        System.out.println(sq.solution3(s));

    }

    // 같은 숫자는 싫어
    public int[] solution(int[] arr) {
        /*
        스택으로 풀 경우 효율성에서 실패했다.
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                st.push(arr[i]);
            } else {
                if (arr[i] != st.peek()) {
                    st.push(arr[i]);
                }
            }
        }

        int[] answer = new int[st.size()];
        for (int i = st.size() - 1; i >= 0; i--) {
            answer[i] = st.pop();
        }*/

        // ArrayList 방식으로 풀면 통과된다.
        ArrayList<Integer> list = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                list.add(arr[i]);
            }
            else {
                if (arr[i] != list.get(count)) {
                    list.add(arr[i]);
                    count++;
                }
            }
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    // 기능개발
    public int[] solution2(int[] progresses, int[] speeds) {
        /*// 내 풀이
        // 각 작업별 소요일 계산
        int[] remain = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            remain[i] = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
        }

        // 배포작업끼리 묶기
        ArrayList<Integer> list = new ArrayList<>();
        int count = 1;
        int preNum = remain[0];
        for (int i = 1; i < remain.length; i++) {
            if (preNum >= remain[i]) {
                count++;
            } else {
                list.add(count);
                preNum = remain[i];
                count = 1;
            }
        }

        // 마지막 남은 작업을 추가해주어야 한다.
        list.add(count);

        int [] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }*/

        // 추천풀이
        Queue<Integer> q = new LinkedList<>();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < speeds.length; i++) {
            double remain = (100 - progresses[i]) / (double) speeds[i];
            int date = (int) Math.ceil(remain);

            if (!q.isEmpty() && q.peek() < date) {
                answerList.add(q.size());
                q.clear();
            }

            q.offer(date);
        }

        // 마지막 남은 작업을 추가해주어야 한다.
        answerList.add(q.size());

        int[] answer = new int[answerList.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    // 올바른 괄호
    public boolean solution3(String s) {
        boolean answer = true;

        // 내 풀이
        char[] arr = s.toCharArray();

        if (arr[0] == ')') {
            answer = false;
        }

        Stack stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==('(')) {
                stack.push(arr[i]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        if (!stack.isEmpty()) {
            answer = false;
        }

        return answer;
    }

}
