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
        // 아이디별 신고 리스트
        Map<String, Set<String>> reportMap = new HashMap<>();
        Map<String, Set<String>> myReport = new HashMap<>();
        for (String r : report) {
            String[] str = r.split(" ");
            String reporter = str[0];
            String suspect = str[1];

            // 이 아이디를 신고한 사람 리스트
            reportMap.computeIfAbsent(suspect, key -> new HashSet<>()).add(reporter);

            // 내가 신고한 아이디 리스트
            myReport.computeIfAbsent(reporter, key -> new HashSet<>()).add(suspect);
        }

        // 정지된 아이디 리스트
        Set<String> blockSet = new HashSet<>();

        for (Map.Entry<String, Set<String>> entry : reportMap.entrySet()) {
            // 리스트의 크기가 k 이상인지 확인
            if (entry.getValue().size() >= k) {
                blockSet.add(entry.getKey());
            }
        }

        // 아이디별 메일을 받는 횟수
        Map<String, Integer> mailMap = new HashMap<>();
        for (String s : id_list) {
            // 먼저 이 유저(s)의 메일 횟수를 기본값 0으로 세팅
            // (아무도 신고하지 않았거나, 정지된 사람을 신고하지 않았을 때를 대비)
            mailMap.put(s, 0);

            for (String m : myReport.getOrDefault(s, Collections.emptySet())) {
                if (blockSet.contains(m)) {
                    mailMap.put(s, mailMap.getOrDefault(s, 0) + 1);
                }
            }
        }

        int[] answer = new int[id_list.length];
        for (int i = 0; i < id_list.length ; i++) {
            answer[i] = mailMap.get(id_list[i]);
        }

        return answer;
    }

}
