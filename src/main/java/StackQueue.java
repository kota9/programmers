import java.util.*;

public class StackQueue {
    // 같은 숫자는 싫어
    public int[] removeAdjacentDuplicates(int[] arr) {
        /*
        스택으로 풀 경우 효율성에서 실패했다.
        Stack<Integer> st = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                st.push(arr[i]);
            } else {
                if (arr[i] != st.peek()) {
                    st.push(arr[i]);
                }
            }
        }

        int[] answer = new int[st.size()];
        for (int i = st.size() - 1; i >= 0; i--) {
            answer[i] = st.pop();
        }*/

        // ArrayList 방식으로 풀면 통과된다.
        ArrayList<Integer> list = new ArrayList<>();

        int count = 0;
        for (int i = 0; i < arr.length; i++) {
            if (i == 0) {
                list.add(arr[i]);
            }
            else {
                if (arr[i] != list.get(count)) {
                    list.add(arr[i]);
                    count++;
                }
            }
        }

        int[] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }

        return answer;
    }

    // 기능개발
    public int[] countFeatureDeployments(int[] progresses, int[] speeds) {
        /*// 내 풀이
        // 각 작업별 소요일 계산
        int[] remain = new int[progresses.length];
        for (int i = 0; i < progresses.length; i++) {
            remain[i] = (int) Math.ceil((double) (100 - progresses[i]) / speeds[i]);
        }

        // 배포작업끼리 묶기
        ArrayList<Integer> list = new ArrayList<>();
        int count = 1;
        int preNum = remain[0];
        for (int i = 1; i < remain.length; i++) {
            if (preNum >= remain[i]) {
                count++;
            } else {
                list.add(count);
                preNum = remain[i];
                count = 1;
            }
        }

        // 마지막 남은 작업을 추가해주어야 한다.
        list.add(count);

        int [] answer = new int[list.size()];
        for (int i = 0; i < answer.length; i++) {
            answer[i] = list.get(i);
        }*/

        // 추천풀이
        Queue<Integer> q = new LinkedList<>();
        List<Integer> answerList = new ArrayList<>();

        for (int i = 0; i < speeds.length; i++) {
            double remain = (100 - progresses[i]) / (double) speeds[i];
            int date = (int) Math.ceil(remain);

            if (!q.isEmpty() && q.peek() < date) {
                answerList.add(q.size());
                q.clear();
            }

            q.offer(date);
        }

        // 마지막 남은 작업을 추가해주어야 한다.
        answerList.add(q.size());

        int[] answer = new int[answerList.size()];

        for (int i = 0; i < answer.length; i++) {
            answer[i] = answerList.get(i);
        }

        return answer;
    }

    // 올바른 괄호
    public boolean hasValidParentheses(String s) {
        boolean answer = true;

        // 내 풀이
        char[] arr = s.toCharArray();

        if (arr[0] == ')') {
            answer = false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            if (arr[i]==('(')) {
                stack.push(arr[i]);
            } else {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    stack.pop();
                }
            }
        }

        if (!stack.isEmpty()) {
            answer = false;
        }

        return answer;
    }

    // 택배상자
    public int countDeliverableBoxes(int[] order) {
        /*order[i]가 나올때까지 스택에 데이터 쌓기
        i와 box가 일치하면 boxCount++
        일치하지 않는다면 스택에 데이터 쌓기
        스택에서 데이터를 꺼내서 i와 일치하면 boxCount++*/

        // 실은 택배 수
        int boxCount = 0;

        // 보조 컨테이너
        Deque<Integer> container = new ArrayDeque<>();

        // 돌아가는 컨테이너
        for (int box = 1; box <= order.length; box++) {
            if (box == order[boxCount]) {
                boxCount++;
            } else {
                container.push(box);
            }

            // 보조 컨테이너에서 박스 찾기
            while (!container.isEmpty() && order[boxCount] == container.peek()) {
                boxCount++;
                container.pop();
            }

        }

        return boxCount;
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

    // 프로세스
    public int findPrintOrder(int[] priorities, int location) {
        Queue<int[]> queue = new ArrayDeque<>();

        for (int i = 0; i < priorities.length; i++) {
            queue.offer(new int[] {i, priorities[i]});
        }

        int executionOrder = 0;

        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            boolean hasHigherPriority = false;

            for (int[] process : queue) {
                if (process[1] > current[1]) {
                    hasHigherPriority = true;
                    break;
                }
            }

            if (hasHigherPriority) {
                queue.offer(current);
            } else {
                executionOrder++;

                if (current[0] == location) {
                    return executionOrder;
                }
            }
        }

        return executionOrder;
    }

}
