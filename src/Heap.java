import java.util.PriorityQueue;

public class Heap {
    public static void main(String[] args) {
        Heap heap = new Heap();

        // 더 맵게
        int[] scoville = {1, 2, 3, 9, 10, 12};
        int K = 7;
        System.out.println(heap.solution(scoville, K));

    }

    // 더 맵게
    public int solution(int[] scoville, int K) {
        int answer = 0;

        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int s : scoville) {
            pq.offer(s);
        }

        while (pq.size() > 1 && pq.peek() < K) {
            int first = pq.poll();
            int second = pq.poll();
            int newSco = first + second*2;

            pq.offer(newSco);
            answer++;
        }

        if (pq.peek() < K) {
            return -1;
        }

        return answer;
    }
    
}
