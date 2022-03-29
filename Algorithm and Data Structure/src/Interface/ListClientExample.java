package Interface;

import java.util.LinkedList;
import java.util.List;

public class ListClientExample {
	@SuppressWarnings("rawtypes")
	// @SuppressWarnings�� ������ ��� �����ϴ� ������̼��̴�.
	// �� ��, rawtypes�� ���ʸ��� ����ϴ� Ŭ���� �� �Ű� ������ ��Ư���� ���� ��� �����Ѵ�.
	// (���ʸ� : ������ ���Ŀ� �������� �ʰ�, �ϳ��� ���� ���� �ٸ� ������ Ÿ�Ե��� ���� �� �ִ� ���� ���Ѵ�.
	//  ���� ���, ArrayList<Integer>, ArrayList<Object obj> ��� <>���� ������� ���Ѵ�.)
	private List list;

	@SuppressWarnings("rawtypes")
	public ListClientExample() {
		// list�� LinkedList Ŭ������ ��ü �����Ѵ�.
		list = new LinkedList();
	}

	@SuppressWarnings("rawtypes")
	public List getList() {
		return list;
	}

	public static void main(String[] args) {
		ListClientExample lce = new ListClientExample();
		@SuppressWarnings("rawtypes")
		List list = lce.getList();
		System.out.println(list);
	}
}
