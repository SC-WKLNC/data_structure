package graph;

import java.util.LinkedList;
import java.util.Stack;

public class Graph {

    static class Node {
        int data;
        LinkedList<Node> adjacent;
        boolean marked;
        Node (int data) {
            this.data = data;
            this.marked = false;
            adjacent = new LinkedList<>();
        }
    }

    Node[] nodes;
    Graph(int size) {
        nodes = new Node[size];
        for (int i = 0; i < size; i++) {
            nodes[i] = new Node(i);
        }
    }
    // 두 노드의 관계를 저장하는 메서드
    void addEdge(int i1, int i2) {
        Node n1 = nodes[i1]; // 데이터가 인덱스가 같기 때문에 받은 숫자를 인덱스로 사용할 수 있다.
        Node n2 = nodes[i2];

        // 서로 상대방이 있는지 확인하고 없으면 추가한다.
        if(!n1.adjacent.contains(n2)) {
            n1.adjacent.add(n2);
        }
        if(n2.adjacent.contains(n1)) {
            n2.adjacent.add(n1);
        }
    }
    void dfs() {
        dfs(0); // 0번부터 시작
    }
    // 시작 index 를 받아서 dfs 순회 결과를 출력하는 메서드
    void dfs(int index) {
        Node root = nodes[index];           // 해당 인덱스로 노드를 하나 가져온다
        Stack<Node> stack = new Stack<>();  // 스택 생성
        stack.push(root);                   // 현재 노드를 스택에 추가
        root.marked = true;                 // 스택에 들어갔다고 마킹
        while(!stack.isEmpty()) {           // 스택에 데이터가 없을때 까지 반복
            Node r = stack.pop();           // 스택에서 데이터를 하나 가져온다
            for(Node n : r.adjacent) {      // 가져온 노드의 자식들을 스택에 추가
                if(!n.marked) {             // 이때 스택에 추가되지 않은 노드들만 추가
                    n.marked = true;
                    stack.push(n);
                }
            }
            visit(r); // 방문시 출력하는 메서드
        }
    }

    // 재귀 dfs
    void dfsR(Node r) {
        if(r == null) return; // 받은 노드가 Null 이면 리턴
        r.marked = true; // 호출이 되었다고 마킹
        visit(r); // 자식 노드들을 호출 전에 데이터를 출력
        for(Node n : r.adjacent) { // 호출이 되지 않은 자식들을 호출
            if(!n.marked) {
                dfsR(n);
            }
        }
    }

    void dfsR(int index) { // 시작노드를 다양하게 테스트 하기 위해 인덱스를 받는 형태로도 작성
        Node r = nodes[index];
        dfsR(r);
    }

    void dfsR() {
        dfsR(0);
    }

//    void bfs() {
//        bfs(0); //0번 부터 시작
//    }
//
//    void bfs(int index) {
//        Node root = nodes[index];
//        Queue<Node> queue = new Queue<>();
//
//    }

    void visit(Node n) {
        System.out.print(n.data + " ");
    }

    /* 그래프
    ------------------
      0
     /
    1 -- 3    7
    |  / | \ /
    | /  |  5
    2 -- 4   \
              6 - 8
    ------------------
    DFS(0)
    0 1 3 5 7 6 8 4 2
    BFS(0)
    0 1 2 3 4 5 6 7 8
    DFS(0) - Recursive
    0 1 2 4 3 5 6 8 7
    ------------------
    DFS(3)
    3 5 7 6 8 4 2 1 0
    BFS(3)
    3 1 2 4 5 0 6 7 8
    DFS(3) - Recursive
    3 1 0 2 4 5 6 8 7
    ------------------
    */
    public static class Test {
        public static void main(String[] args) {
            Graph g = new Graph(9);
            g.addEdge(0, 1);
            g.addEdge(1, 2);
            g.addEdge(1, 3);
            g.addEdge(2, 4);
            g.addEdge(2, 3);
            g.addEdge(3, 4);
            g.addEdge(3, 5);
            g.addEdge(5, 6);
            g.addEdge(5, 7);
            g.addEdge(6, 8);
            g.dfsR(3);
        }
    }
}
