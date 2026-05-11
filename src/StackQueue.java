import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

public class StackQueue {
    public static void main(String[] args) {
        StackQueue sq = new StackQueue();

        // 같은 숫자는 싫어
        int[] arr = {1, 1, 3, 3, 0, 1, 1};
        int[] ret = sq.solution(arr);
        System.out.println(Arrays.toString(ret));

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

}
