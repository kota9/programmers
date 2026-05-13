import java.util.Arrays;

public class Sort {
    public static void main(String[] args) {
        Sort sort = new Sort();

        // K번째수
        int[] array = {1, 5, 2, 6, 3, 7, 4};
        int[][] commands = new int[][]{{2, 5, 3}, {4, 4, 1}, {1, 7, 3}};
        System.out.println(Arrays.toString(sort.solution(array, commands)));

    }

    // K번째수
    public int[] solution(int[] array, int[][] commands) {
        int[] answer = new int[commands.length];

        for (int i = 0; i < commands.length; i++) {
            // 배열 자르기
            int[] subArray = Arrays.copyOfRange(array, commands[i][0]-1, commands[i][1]);

            // 정렬하기
            Arrays.sort(subArray);

            // k번째 수 가져와서 리턴값에 넣기
            answer[i] = subArray[commands[i][2]-1];
        }
        return answer;
    }

}
