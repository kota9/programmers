import java.util.Arrays;

public class Sort {
    // K번째수
    public int[] findKthNumbers(int[] array, int[][] commands) {
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
