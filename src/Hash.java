import java.util.*;

// 해시 문제
public class Hash {
    public static void main(String[] args) {
        Hash hash = new Hash();

        // 완주하지 못한 선수
//        String[] participant = new String[]{"leo", "kiki", "eden"};
//        String[] completion = new String[]{"kiki", "eden"};
//        String[] participant = new String[]{"mislav", "stanko", "mislav", "ana"};
//        String[] completion = new String[]{"stanko", "ana", "mislav"};
//        System.out.println(hash.solution(participant, completion));

        // 폰켓몬
//        int[] nums = new int[]{3,1,2,3};
//        int[] nums = new int[]{3,3,3,2,2,4};
        int[] nums = new int[]{3,3,3,2,2,2};
        System.out.println(hash.solution2(nums));


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

        /*// 내 풀이
        int maxCount = nums.length / 2;

        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }

        // Value 기준 오름차순 정렬
        List<Map.Entry<Integer, Integer>> sortedList = map.entrySet()
                .stream()
                .sorted(Map.Entry.comparingByValue())
                .collect(Collectors.toList());

        for (Map.Entry<Integer, Integer> entry : sortedList) {
            answer++;
            if (answer == maxCount) {
                break;
            }
        }*/

        // 추천 풀이
        HashSet<Integer> hs = new HashSet<>();

        for (int num : nums) {
            hs.add(num);
        }

        // 해시셋의 사이즈와 가져갈수 있는 최대 카운트를 비교해서 작은 값을 리턴한다.
        if (hs.size() > nums.length / 2) {
            answer = nums.length / 2;
        } else {
            answer = hs.size();
        }

        return answer;
    }


}
