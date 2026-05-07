import java.util.HashMap;
import java.util.Map;

public class Solution {

    //// 해시 ////
    // 완주하지 못한 선수
    public String solution(String[] participant, String[] completion) {
        String answer = "";

        HashMap<String, Integer> map = new HashMap<>();

        // 1. 모든 참여자를 해시맵에 넣기 (이름, 등장 횟수)
        for (String p : participant) {
            map.put(p, map.getOrDefault(p, 0) + 1);
        }

        // 2. 완주한 사람의 횟수를 1씩 차감
        for (String c : completion) {
            map.put(c, map.get(c) - 1);
        }

        // 3. 남은 횟수가 1인 사람이 정답
        for (String key : map.keySet()) {
            if (map.get(key) != 0) {
                answer = key;
                break;
            }
        }

        // 참고. keySet 말고 entrySet을 사용할 경우
        /*for (Map.Entry<String, Integer> entry : map.entrySet()) {
            if (entry.getValue() != 0) {
                answer = entry.getKey();
                break;
            }
        }*/

        return answer;
    }
}
