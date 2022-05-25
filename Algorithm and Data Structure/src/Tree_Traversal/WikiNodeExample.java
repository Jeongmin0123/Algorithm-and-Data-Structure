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
		
		// ������ �ٿ�ε� �� �Ľ��Ѵ�.
		Connection conn = Jsoup.connect(url);
		Document doc = conn.get();
		
		// ������ ������ �ڿ� �ܶ��� �����Ѵ�.
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

		// ���� stack�� ����ٸ� ������ �ʿ䰡 �����Ƿ� �� ��찡 �ƴҶ��� ������ �����Ѵ�.
		while (!stack.isEmpty()) {

			// ���� ���� ��쿡�� ������ ���� ��带 �����Ѵ�.
			Node node = stack.pop();
			if (node instanceof TextNode) {
				System.out.print(node);
			}

			// �ڽ� ��带 �������� ���ÿ� �߰��Ѵ�.
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
