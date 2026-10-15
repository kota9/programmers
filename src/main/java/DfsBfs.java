import java.util.ArrayDeque;
import java.util.Queue;

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

}
