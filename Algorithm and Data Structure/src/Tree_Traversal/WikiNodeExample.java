package Tree_Traversal;

import java.io.IOException;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.List;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.Node;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

public class WikiNodeExample {
	
	public static void main(String[] args) throws IOException {
		String url = "https://en.wikipedia.org/wiki/Java_(programming_language)";
		
		// 문서를 다운로드 후 파싱한다.
		Connection conn = Jsoup.connect(url);
		Document doc = conn.get();
		
		// 내용을 선택한 뒤에 단락을 추출한다.
		Element content = doc.getElementById("mw-content-text");
				
		Elements paras = content.select("p");
		Element firstPara = paras.get(0);
		
		recursiveDFS(firstPara);
		System.out.println();

		iterativeDFS(firstPara);
		System.out.println();

		Iterable<Node> iter = new WikiNodeIterable(firstPara);
		for (Node node: iter) {
			if (node instanceof TextNode) {
				System.out.print(node);
			}
		}
	}

	private static void iterativeDFS(Node root) {
		Deque<Node> stack = new ArrayDeque<Node>();
		stack.push(root);

		// 만약 stack이 비었다면 연산할 필요가 없으므로 빈 경우가 아닐때만 연산을 진행한다.
		while (!stack.isEmpty()) {

			// 비지 않은 경우에는 스택의 다음 노드를 추출한다.
			Node node = stack.pop();
			if (node instanceof TextNode) {
				System.out.print(node);
			}

			// 자식 노드를 역순으로 스택에 추가한다.
			List<Node> nodes = new ArrayList<Node>(node.childNodes());
			Collections.reverse(nodes);
			
			for (Node child: nodes) {
				stack.push(child);
			}
		}
	}
	
	private static void recursiveDFS(Node node) {
		if (node instanceof TextNode) {
			System.out.print(node);
		}
		for (Node child: node.childNodes()) {
			recursiveDFS(child);
		}
	}
}
