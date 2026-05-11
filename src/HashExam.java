import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

// 해시 문제
public class HashExam {
    public static void main(String[] args) {
        HashExam he = new HashExam();

        // 완주하지 못한 선수
//        String[] participant = new String[]{"leo", "kiki", "eden"};
//        String[] completion = new String[]{"kiki", "eden"};
//        String[] participant = new String[]{"mislav", "stanko", "mislav", "ana"};
//        String[] completion = new String[]{"stanko", "ana", "mislav"};
//        System.out.println(he.solution(participant, completion));

        // 폰켓몬
        int[] nums = new int[]{3,1,2,3};
//        int[] nums = new int[]{3,3,3,2,2,4};
//        int[] nums = new int[]{3,3,3,2,2,2};
        System.out.println(he.solution2(nums));


    }

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

    // 폰켓몬
    public int solution2(int[] nums) {
        int answer = 0;
        int maxCount = nums.length / 2;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

//        System.out.println(map);
//        System.out.println(maxCount);

        // Value 기준 오름차순 정렬
        List<Map.Entry<Integer, Integer>> sortedList = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        for (Map.Entry<Integer, Integer> entry : sortedList) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
            answer++;
            if (answer == maxCount) {
                break;
            }
        }
//        System.out.println(answer);
//        System.out.println(sortedList);

        return answer;
    }


}
