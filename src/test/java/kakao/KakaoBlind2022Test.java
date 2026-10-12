package kakao;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class KakaoBlind2022Test {

    private final KakaoBlind2022 kakaoBlind2022 = new KakaoBlind2022();

    @Test
    void countsMailForEachReporter() {
        String[] idList = {"muzi", "frodo", "apeach", "neo"};
        String[] reports = {
                "muzi frodo", "apeach frodo", "frodo neo",
                "muzi neo", "apeach muzi"
        };

        assertArrayEquals(new int[]{2, 1, 1, 0}, kakaoBlind2022.countReportNotifications(idList, reports, 2));
    }

    @Test
    void ignoresDuplicateReportsFromSameUser() {
        String[] idList = {"con", "ryan"};
        String[] reports = {"ryan con", "ryan con", "ryan con", "ryan con"};

        assertArrayEquals(new int[]{0, 0}, kakaoBlind2022.countReportNotifications(idList, reports, 3));
    }
}
