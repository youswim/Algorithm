package main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main{
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader((System.in)));
        StringBuilder sb = new StringBuilder();

        String[] str;
        str = br.readLine().split(" ");

        int numberNode = Integer.parseInt(str[0]); // 노드 갯수 입력
        int numberLine = Integer.parseInt(str[1]); // 간선 갯수 입력
        int startNode = Integer.parseInt(str[2]); // 시작 노드 입력

        ArrayList<LinkedList<Integer>> nearList = new ArrayList<>(numberNode + 1);
        // ArrayList의 크기가 수정되는 경우를 방지하기 위해서 initialCapacity 인자 넘겨줌

        for (int i = 0; i < numberNode + 1; i++) {
            nearList.add(new LinkedList<>());
            // 삽입이 자유로운 LinkedList 할당
        }

        for (int i = 1; i < numberLine + 1; i++) {
            str = br.readLine().split(" ");
            int v1 = Integer.parseInt(str[0]);
            int v2 = Integer.parseInt(str[1]);
            // 간선 입력

            nearList.get(v1).add(v2);
            nearList.get(v2).add(v1);
            // 노드의 인접 리스트에 연결된 노드 추가
        }

        for (int i = 0; i < numberNode; i++) {
            Collections.sort(nearList.get(i));
            // 오름차순으로 방문하기 위해서 인접 리스트 정렬
        }

        boolean[] visit = new boolean[numberNode + 1];
        // 방문 기록을 하기 위한 boolean 배열

        Stack<Integer> stack = new Stack<>(); // bfs를 stack으로 구현할 것임.
        stack.push(startNode); // 시작 노드 먼저 삽입
        visit[startNode] = true; // 시작 노드 방문 체크
        sb.append(startNode); // 방문 순서 기록

        while (!stack.empty()) { // stack이 빌 때까지 반복함
            int topNode = stack.peek(); // stack의 top node 가져옴
            boolean chk = false; // 아래에 설명.

            for (int n : nearList.get(topNode)) {
                // topNode의 인접 노드를 하나씩 확인
                if(!visit[n]){ // 인접 노드중에 방문하지 않은 노드가 있다면,
                    stack.push(n); // 방문하지 않은 노드 stack에 push.
                    visit[n] = true; // 방문 체크
                    sb.append(" ").append(n); // 방문 기록
                    chk = true; // 방문할 인접 노드가 있다면 true로 수정
                    break;
                }
            }
            if (!chk) { // 방문할 인접 노드가 없었던 경우
                stack.pop(); // stack의 top 노드 pop
            }
        }
        System.out.println(sb);
    }
}