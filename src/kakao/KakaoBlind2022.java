package kakao;

import java.util.*;

// 2022 KAKAO BLIND RECRUITMENT
public class KakaoBlind2022 {
    public static void main(String[] args) {
        KakaoBlind2022 kakao = new KakaoBlind2022();

        // 신고 결과 받기
        String[] id_list = {"muzi", "frodo", "apeach", "neo"};
        String[] report = {"muzi frodo","apeach frodo","frodo neo","muzi neo","apeach muzi"};
        int k = 2;
//        String[] id_list = {"con", "ryan"};
//        String[] report = {"ryan con", "ryan con", "ryan con", "ryan con"};
//        int k = 3;
        System.out.println(Arrays.toString(kakao.solution(id_list, report, k)));

    }

    // 신고 결과 받기
    public int[] solution(String[] id_list, String[] report, int k) {
        // 정답을 담을 배열 (자바에서 int 배열은 자동으로 0으로 초기화됨)
        int[] answer = new int[id_list.length];

        // 1. 유저 ID별 인덱스를 저장하는 Map 생성 (배열에 바로 접근하기 위함)
        Map<String, Integer> idIdxMap = new HashMap<>();
        for (int i = 0; i < id_list.length; i++) {
            idIdxMap.put(id_list[i], i);
        }

        // 2. [신고당한 유저 -> 신고한 유저들의 Set] 만들기
        Map<String, Set<String>> reportMap = new HashMap<>();
        for (String r : report) {
            String[] str = r.split(" ");
            // str[1]: 신고당한 사람, str[0]: 신고한 사람
            reportMap.computeIfAbsent(str[1], key -> new HashSet<>()).add(str[0]);
        }

        // 3. 메일 발송 로직 처리
        for (Set<String> reporters : reportMap.values()) {
            if (reporters.size() >= k) {
                for (String reporter : reporters) {
                    answer[idIdxMap.get(reporter)]++;
                }
            }
        }

        return answer;
    }

}
