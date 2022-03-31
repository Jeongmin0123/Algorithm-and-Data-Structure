package Interface;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

public class ListClientExampleTest {

	/**
	 * Test method for {@link ListClientExample}.
	 */
	@Test
	public void testListClientExample() {
		ListClientExample lce = new ListClientExample();
		@SuppressWarnings("rawtypes")
		List list = lce.getList();
		// 이 때 ,assertThat(list, instanceOf(ArrayList.class));
		// 메서드에서 첫 번째 파라미터는 비교값, 두 번째 파라미터는 비교 로직을 의미한다.
		// 따라서 밑의 함수는 list가 ArrayList.class 인지 판단하는 메서드이다.
		assertThat(list, instanceOf(ArrayList.class) );
		
		// 맨 마지막 질문에서 ArrayList객체를 List 인터페이스로 교체하면 오류가 발생한다.
		// 이 때, 그 이유는
		// getlist 메서드는 list를 return 하고 이 때 리스트의 생성자는 list를 LinkedList 형태로
		// 만들기 때문에 오류가 발생한다.
	}

}
