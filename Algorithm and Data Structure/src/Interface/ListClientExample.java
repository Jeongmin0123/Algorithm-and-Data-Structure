package Interface;

import java.util.LinkedList;
import java.util.List;

public class ListClientExample {
	@SuppressWarnings("rawtypes")
	// @SuppressWarnings은 컴파일 경고를 무시하는 어노테이션이다.
	// 이 때, rawtypes는 제너릭을 사용하는 클래스 의 매개 변수가 불특정일 때의 경고를 억제한다.
	// (제너릭 : 데이터 형식에 의존하지 않고, 하나의 값이 여러 다른 데이터 타입들을 가질 수 있는 것을 말한다.
	//  예를 들어, ArrayList<Integer>, ArrayList<Object obj> 등에서 <>안의 내용들을 말한다.)
	private List list;

	@SuppressWarnings("rawtypes")
	public ListClientExample() {
		// list를 LinkedList 클래스로 객체 생성한다.
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
