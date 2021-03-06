package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String[] str = br.readLine().split(" ");

        int n = Integer.parseInt(str[0]);
        int m = Integer.parseInt(str[1]);
        int v = Integer.parseInt(str[2]);

        boolean[] visited = new boolean[n + 1];

        ArrayList<ArrayList<Integer>> adList = new ArrayList<>(n+1);
        //ArrayList의 크기 변경이 없도록 initialCapacity를 줌

        for (int i = 0; i <= n; i++) {
            adList.add(new ArrayList<>());
        }
        for (int i = 0; i < m; i++) {
            str = br.readLine().split(" ");
            int v1 = Integer.parseInt(str[0]);
            int v2 = Integer.parseInt(str[1]);
            adList.get(v1).add(v2);
            adList.get(v2).add(v1);
        }
        for (int i = 0; i <= n; i++) {
            Collections.sort(adList.get(i)); //오름차순 방문을 위한 정렬
        }

        Queue<Integer> queue = new LinkedList<>();
        queue.add(v);
        visited[v] = true;
        sb.append(v);


        // 1. 큐에 들어있는 노드 poll하기(동시에 출력)
        // 2. poll 한 노드의 인접 노드들의 방문 여부 확인
        // 2-2. 인접 노드중에 방문하지 않은 노드가 있다면 큐에 add.
        // 3. 큐가 empty가 될 때까지 1번으로 반복

        while (queue.size() != 0) { //queue가 비어있지 않다면
            v = queue.poll(); // 뺀다
            
            for (int w : adList.get(v)) {
                if (!visited[w]) { // 아직 방문하지 않았다면
                    queue.add(w);
                    visited[w] = true; // 방문 기록
                    sb.append(" ").append(w);
                }
            }
        }
        System.out.println(sb);
    }
}
