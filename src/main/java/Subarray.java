import java.util.ArrayDeque;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.Queue;

public class Subarray {
    // 카데인 알고리즘
    /**
     * 연속된 부분 배열의 최대 합을 반환하는 메서드
     * @param nums 정수 배열
     * @return 최대 부분 배열의 합
     */
    public static int maxSubArray(int[] nums) {
        // 배열이 비어있거나 null인 경우 예외 처리
        if (nums == null || nums.length == 0) {
            throw new IllegalArgumentException("배열이 비어있거나 유효하지 않습니다.");
        }

        // 초기값 설정: 배열의 첫 번째 원소로 시작
        int currentSum = nums[0];
        int maxSum = nums[0];

        // 배열의 두 번째 원소부터 끝까지 순회
        for (int i = 1; i < nums.length; i++) {
            // 핵심 로직:
            // '현재 원소 자체'와 '이전까지의 연속된 합 + 현재 원소' 중 더 큰 값을 선택
            currentSum = Math.max(nums[i], currentSum + nums[i]);

            // 전체 최대값 갱신
            maxSum = Math.max(maxSum, currentSum);
        }

        return maxSum;
    }

    // 카데인 알고리즘 변형
    // 연속 펄스 부분 수열의 합
    public long continuousPulse(int[] sequence) {
        long answer = 0;

        long currA = 0;
        long maxA = 0;
        long valA = 0;
        long signA = 0;

        long currB = 0;
        long maxB = 0;
        long valB = 0;
        long signB = 0;

        // 카데인 알고리즘 + pulse별로 값 계산
        for (int i=0; i<sequence.length; i++) {
            signA = (i%2==0 ? 1 : -1);
            signB = (i%2==0 ? -1 : 1);

            valA = sequence[i] * signA;
            valB = sequence[i] * signB;

            currA = Math.max(valA, currA + valA);
            currB = Math.max(valB, currB + valB);

            maxA = Math.max(maxA, currA);
            maxB = Math.max(maxB, currB);
        }

        answer = Math.max(maxA, maxB);

        return answer;
    }

    // 길이가 '정확히 K'인 경우 (슬라이딩 윈도우)
    // 길이가 $K$로 고정되어 있다면, 카데인 알고리즘보다는 슬라이딩 윈도우(Sliding Window) 기법을 사용하는 것이 훨씬 직관적이고 정확합니다.
    // 크기가 $K$인 창문을 배열 위로 한 칸씩 밀면서 합을 구하는 방식입니다.
    /**
     * 길이가 정확히 K인 연속된 부분 배열의 최대 합을 반환합니다.
     * 시간 복잡도: O(N)
     */
    public static int maxSubArrayExactK(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) {
            throw new IllegalArgumentException("유효하지 않은 배열이거나 K값이 잘못되었습니다.");
        }

        int windowSum = 0;
        int maxSum = 0;

        // 1. 처음 K개의 원소로 초기 윈도우의 합을 구합니다.
        for (int i = 0; i < k; i++) {
            windowSum += nums[i];
        }
        maxSum = windowSum;

        // 2. 윈도우를 한 칸씩 오른쪽으로 이동시키며 최대값을 갱신합니다.
        for (int i = k; i < nums.length; i++) {
            // 새로 들어온 원소를 더하고, 윈도우에서 벗어난 첫 원소를 뺍니다.
            windowSum = windowSum + nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, windowSum);
        }

        return maxSum;
    }

    // 길이가 'K 이상'인 경우 (카데인 알고리즘 + 슬라이딩 윈도우)
    // 길이가 최소 $K$개는 되어야 한다는 조건이라면, 기존 카데인 알고리즘의 누적합 개념과 슬라이딩 윈도우를 결합해야 합니다.
    // $K$개의 합을 구한 뒤, 그 직전까지의 '카데인 최대합'이 양수라면 이를 더해주는 방식입니다.
    /**
     * 길이가 K 이상인 연속된 부분 배열의 최대 합을 반환합니다.
     * 시간 복잡도: O(N), 공간 복잡도: O(N)
     */
    public static int maxSubArrayAtLeastK(int[] nums, int k) {
        if (nums == null || nums.length < k || k <= 0) {
            throw new IllegalArgumentException("유효하지 않은 배열이거나 K값이 잘못되었습니다.");
        }

        int n = nums.length;

        // 1. 각 인덱스에서 끝나는 최대 부분 배열의 합 (기본 카데인 알고리즘 로직)
        int[] kadaneMax = new int[n];
        kadaneMax[0] = nums[0];
        int currentSum = nums[0];

        for (int i = 1; i < n; i++) {
            currentSum = Math.max(nums[i], currentSum + nums[i]);
            kadaneMax[i] = currentSum;
        }

        // 2. 처음 K개의 원소의 합(슬라이딩 윈도우 초기값)을 계산합니다.
        int exactKSum = 0;
        for (int i = 0; i < k; i++) {
            exactKSum += nums[i];
        }

        int maxSum = exactKSum;

        // 3. 윈도우를 이동하며 '길이 K인 합'에 '이전까지의 최대 합'을 더할지 결정합니다.
        for (int i = k; i < n; i++) {
            // 윈도우 한 칸 이동 (길이 K 유지)
            exactKSum = exactKSum + nums[i] - nums[i - k];

            // 현재 길이가 정확히 K인 배열의 합과 기존 최대값 비교
            maxSum = Math.max(maxSum, exactKSum);

            // 직전 인덱스(i - k)까지의 카데인 최대합이 0보다 크다면,
            // 그것을 더해 길이를 K 이상으로 확장하는 것이 이득입니다.
            if (kadaneMax[i - k] > 0) {
                maxSum = Math.max(maxSum, exactKSum + kadaneMax[i - k]);
            }
        }

        return maxSum;
    }

    // 호텔 대실
    public int calculateMinimumRoomCount(String[][] book_time) {
        // 방 개수
        int roomCount = 0;

        // 예약 시간순으로 정렬
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));

        // 예약 시간을 큐에 저장
        Queue<int[]> queue = new ArrayDeque<>();
        for (String[] time : book_time) {
            // 예약 종료 시간에 청소시간(10분을 추가)
            // 예약시간을 분으로 변환
            String startTime = time[0];
            String endTime = time[1];

            String[] sTime = startTime.split(":");
            String[] eTime = endTime.split(":");
            int startMinute = Integer.parseInt(sTime[0]) * 60 + Integer.parseInt(sTime[1]);
            int endMinute = Integer.parseInt(eTime[0]) * 60 + Integer.parseInt(eTime[1]) + 10;

            int[] newTime = {startMinute, endMinute};
            queue.offer(newTime);
        }

        // 방에 첫번째 예약이면 방 개수를 +1
        // 방에 더 이상 예약할 수 없으면 방 개수를 추가
        while (!queue.isEmpty()) {
            int[] currentReservation = queue.poll();
            roomCount++;

            int queueSize = queue.size();
            for (int i = 0; i < queueSize; i++) {
                int[] nextReservation = queue.poll();
                if (currentReservation[1] <= nextReservation[0]) {
                    currentReservation = nextReservation;
                } else {
                    queue.offer(nextReservation);
                }
            }
        }

        return roomCount;
    }

    // 호텔 대실 by ChatGPT
    public int calculateMinimumRoomCountByChatGPT(String[][] book_time) {
        Arrays.sort(book_time, (a, b) -> a[0].compareTo(b[0]));

        // 객실별 사용 가능 시각을 저장하는 최소 힙
        PriorityQueue<Integer> endTimes = new PriorityQueue<>();

        int maxRooms = 0;

        for (String[] time : book_time) {
            int startMinute = toMinutes(time[0]);
            int endMinute = toMinutes(time[1]) + 10;

            // 현재 예약 시작 전에 사용 가능한 객실 제거
            while (!endTimes.isEmpty()
                    && endTimes.peek() <= startMinute) {
                endTimes.poll();
            }

            // 현재 예약을 객실에 배정
            endTimes.offer(endMinute);

            maxRooms = Math.max(maxRooms, endTimes.size());
        }

        return maxRooms;
    }

    private int toMinutes(String time) {
        String[] parts = time.split(":");

        return Integer.parseInt(parts[0]) * 60 + Integer.parseInt(parts[1]);
    }

}
