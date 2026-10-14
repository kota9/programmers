import java.util.*;

public class StackQueue {
    // 같은 숫자는 싫어
    public int[] removeAdjacentDuplicates(int[] arr) {
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
    public int[] countFeatureDeployments(int[] progresses, int[] speeds) {
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
    public boolean hasValidParentheses(String s) {
        boolean answer = true;

        // 내 풀이
        char[] arr = s.toCharArray();

        if (arr[0] == ')') {
            answer = false;
        }

        Stack<Character> stack = new Stack<>();

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

    // 택배상자
    public int countDeliverableBoxes(int[] order) {
        /*order[i]가 나올때까지 스택에 데이터 쌓기
        i와 box가 일치하면 boxCount++
        일치하지 않는다면 스택에 데이터 쌓기
        스택에서 데이터를 꺼내서 i와 일치하면 boxCount++*/

        // 실은 택배 수
        int boxCount = 0;

        // 보조 컨테이너
        Deque<Integer> container = new ArrayDeque<>();

        // 돌아가는 컨테이너
        for (int box = 1; box <= order.length; box++) {
            if (box == order[boxCount]) {
                boxCount++;
            } else {
                container.push(box);
            }

            // 보조 컨테이너에서 박스 찾기
            while (!container.isEmpty() && order[boxCount] == container.peek()) {
                boxCount++;
                container.pop();
            }

        }

        return boxCount;
    }

}
