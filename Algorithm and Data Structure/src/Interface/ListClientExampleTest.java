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
		// �� �� ,assertThat(list, instanceOf(ArrayList.class));
		// �޼��忡�� ù ��° �Ķ���ʹ� �񱳰�, �� ��° �Ķ���ʹ� �� ������ �ǹ��Ѵ�.
		// ���� ���� �Լ��� list�� ArrayList.class ���� �Ǵ��ϴ� �޼����̴�.
		assertThat(list, instanceOf(ArrayList.class) );
		
		// �� ������ �������� ArrayList��ü�� List �������̽��� ��ü�ϸ� ������ �߻��Ѵ�.
		// �� ��, �� ������
		// getlist �޼���� list�� return �ϰ� �� �� ����Ʈ�� �����ڴ� list�� LinkedList ���·�
		// ����� ������ ������ �߻��Ѵ�.
	}

}
