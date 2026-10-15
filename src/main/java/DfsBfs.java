import java.util.*;

// DFS: Depth-First Search, 깊이 우선 탐색
// BFS: Breadth-First Search, 너비 우선 탐색
public class DfsBfs {

    // 게임 맵 최단거리. BFS.
    public int findShortestDistanceInGameMap(int[][] maps) {
        // 맵 길이
        int n = maps.length;
        int m = maps[0].length;

        // 거리 기록 배열 생성
        int[][] dist = new int[n][m];

        // 상, 하, 좌, 우
        int[] dr = {-1, 1, 0, 0};
        int[] dc = {0, 0, -1, 1};

        // 큐 생성
        Queue<int[]> queue = new ArrayDeque<>();

        // 시작 좌표 넣고 거리 1 기록하기
        queue.offer(new int[]{0, 0});
        dist[0][0] = 1;

        // 탐색하기
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int row = current[0];
            int col = current[1];

            // 도착점이면 거리 반환
            if (row == n - 1 && col == m - 1) {
                return dist[row][col];
            }

            // 상하좌우 탐색
            for (int d = 0; d < 4; d++) {
                int nr = row + dr[d];
                int nc = col + dc[d];

                // 맵 범위를 벗어나면 건너뛰기
                if (nr < 0 || nr >= n ||
                    nc < 0 || nc >= m) {
                    continue;
                }

                // 벽이거나 방문한 칸이면 건너뛰기
                if (maps[nr][nc] == 0 ||
                    dist[nr][nc] != 0) {
                    continue;
                }

                // 거리 기록 후 큐에 등록
                dist[nr][nc] = dist[row][col] + 1;
                queue.offer(new int[]{nr, nc});
            }
        }

        // 도착할 수 없는 경우
        return -1;
    }

    // 네트워크. DFS
    public int countConnectedNetworks(int n, int[][] computers) {
        boolean[] visited = new boolean[n];
        int networkCount = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                networkCount++;
                exploreNetworkDepthFirst(i, computers, visited, n);
            }
        }

        return networkCount;
    }

    private void exploreNetworkDepthFirst(int current, int[][] computers, boolean[] visited, int n) {
        visited[current] = true;

        for (int next = 0; next < n; next++) {
            if (computers[current][next] == 1 && !visited[next]) {
                exploreNetworkDepthFirst(next, computers, visited, n);
            }
        }
    }

    public int findMaximumDungeonCount(int k, int[][] dungeons) {
        return searchDungeonPathsDepthFirst(k, dungeons, new boolean[dungeons.length]);
    }

    private int searchDungeonPathsDepthFirst(int fatigue, int[][] dungeons, boolean[] visited) {
        int maxCount = 0;

        for (int i = 0; i < dungeons.length; i++) {
            // 이미 방문한 던전은 제외
            if (visited[i]) {
                continue;
            }

            if (fatigue < dungeons[i][0]) {
                continue;
            }

            // 현재 던전 방문
            visited[i] = true;

            // 던전 탐험 후 다음 던전 탐색
            int exploredCount = 1 + searchDungeonPathsDepthFirst(
                    fatigue - dungeons[i][1], dungeons, visited);
            maxCount = Math.max(maxCount, exploredCount);

            // 다른 방문 순서를 탐색하기 위해 방문 상태 복구
            visited[i] = false;
        }

        return maxCount;
    }

    // 전력망을 둘로 나누기
    public int findMinimumTowerDifference(int n, int[][] wires) {
        // 송전탑 간 연결 관계를 저장하는 인접 리스트
        List<Integer>[] graph = new ArrayList[n + 1];

        for (int i = 1; i <= n; i++) {
            graph[i] = new ArrayList<>();
        }

        // 양방향으로 연결 관계 저장
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            graph[a].add(b);
            graph[b].add(a);
        }

        int answer = n;

        // 전선을 하나씩 끊어 보는 경우
        for (int[] wire : wires) {
            int a = wire[0];
            int b = wire[1];

            boolean[] visited = new boolean[n + 1];

            // 해당 전선을 제외하고 한쪽 네트워크의 송전탑 수 계산
            int count = countConnectedTowersWithoutWire(a, a, b, graph, visited);

            // 반대쪽 네트워크의 송전탑 수
            int otherCount = n - count;

            int difference = Math.abs(count-otherCount);

            answer = Math.min(answer, difference);
        }

        return answer;
    }

    public int countConnectedTowersWithoutWire(int current, int cutA, int cutB, List<Integer>[] graph, boolean[] visited) {
        // 1. 현재 송전탑 방문 처리
        visited[current] = true;

        // 2. 현재 송전탑 자신을 1개로 계산
        int count = 1;

        // 3. 현재 송전탑과 연결된 송전탑 확인
        for (int next : graph[current]) {

            // 4. 끊은 전선이면 건너뛰기
            if ((current == cutA && next == cutB)
                    || (current == cutB && next == cutA)) {
                continue;
            }

            // 5. 방문하지 않은 송전탑으로 이동하고 개수 누적
            if (!visited[next]) {
                count += countConnectedTowersWithoutWire(next, cutA, cutB, graph, visited);
            }
        }

        // 6. 현재 송전탑에서 도달 가능한 송전탑 수 반환
        return count;
    }

}
