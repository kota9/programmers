public class Greedy {

    // 조이스틱
    public int joystick(String name) {
        int answer = 0;
        int len = name.length();

        // 상하 이동
        for (int i = 0; i < len; i++) {
            char c = name.charAt(i);
            answer += Math.min(c - 'A', 'Z' - c + 1);
        }

        // 좌우 이동
        int move = len - 1;
        for (int i = 0; i < len; i++) {
            // i 다음 위치부터 연속된 A구간을 건너뛴다.
            int next = i + 1;

            // 연속된 A 개수 계산
            while (next < len && name.charAt(next) == 'A') {
                next++;
            }

            // 오른쪽으로 이동한 뒤 되돌아가서 왼쪽 이동
            move = Math.min(move, i * 2 + len - next);

            // 왼쪽으로 이동한 뒤 되돌아가서 오른쪽 이동
            move = Math.min(move, (len - next) * 2 + i);
        }

        answer += move;

        return answer;
    }

}
