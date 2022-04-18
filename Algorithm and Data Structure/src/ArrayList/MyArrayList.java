package ArrayList;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

// �ؿ��� MyArrayList<T>�� add(T element) ��� T�� �ǹ��ϴ� ����
// ���׸� Ÿ�����ν� Ŭ������ ������ ��, ��ü���� Ÿ���� �������� �ʰ� ���� ��
// ��ü���� Ÿ���� �����ϸ鼭 ����Ѵ�.
public class MyArrayList<T> implements List<T> {
	int size;                    // ����� ������ ������
	private T[] array;           // ��Ҹ� �����ϴ� �迭

	// @SuppressWarnings�� ������ ����� �����ϴ� ������̼��̴�.
	// �� ��, unchecked�� �������� ���� ������ ���� ����� �����Ѵ�.
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		// �� ��, array = new Object[10]; �� �Ұ����ϴ�.
		// �ֳ��ϸ� array�� ���´� T[] �� �Ǿ�� �Ǳ� �����̴�(12�� ��������)
		array = (T[]) new Object[10];
		size = 0;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		MyArrayList<Integer> mal = new MyArrayList<Integer>();
		mal.add(1);
		mal.add(2);
		mal.add(3);
		System.out.println(Arrays.toString(mal.toArray()) + " size = " + mal.size);

		mal.remove(new Integer(2));
		System.out.println(Arrays.toString(mal.toArray()) + " size = " + mal.size);
	}

	@Override
	public boolean add(T element) {
		if(size >= array.length) {
			// ���� �� �� ���� ��� �޽����� �����Ѵ�.
			@SuppressWarnings("unchecked")
			T[] bigger = (T[]) new Object[array.length * 2];
			// �Ʒ� ������ ���� �����ؼ� �迭�� ���̸� ���̴� ���� 1��, �����ϴ� ���� 1��
			// �� 2���� ��� ������ �ɸ��ٰ� �����ϸ� �ȴ�.
			System.arraycopy(array, 0, bigger, 0, array.length);
			array = bigger;
			array[size] = element;
			size++;
		}
		return true;
	} 

	@Override
	// �ݺ����� ����ǰ� �� ���� ��ҿ� �߰��ϴ� ���� �ƴϸ� �Ʒ� �޼���� ���� �ð��� �ɸ���.
	public void add(int index, T element) {
		if (index < 0 || index > size) {
			// IndexOutOfBoundsException()�� �迭�� ũ�⸦ ����ų� index���� 0���� ���� ���
			// �迭�� �Էµ� �� ���� �� ��Ÿ����.
			throw new IndexOutOfBoundsException();
		}
		// element�� �߰��Ѵ�.
		add(element);

		// ��ҵ��� ��ġ�� �ٲ۴�.
		for (int i=size-1; i>index; i--) {
			array[i] = array[i-1];
		}
		// ��ҵ��� ��ġ�� �ٲ� �ڿ� ���ϴ� ��ġ�� element�� �־��ش�.
		array[index] = element;
	}

	@Override
	public boolean addAll(Collection<? extends T> collection) {
		boolean flag = true;
		for (T element: collection) {
			flag &= add(element);
		}
		return flag;
	}

	@Override
	public boolean addAll(int index, Collection<? extends T> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	// �迭�� clear �ϴ� �޼���
	public void clear() {
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		// obj�� �����ϴ��� ���ϴ��� �˷��ִ� �޼���
		return indexOf(obj) != -1;
	}

	@Override
	public boolean containsAll(Collection<?> collection) {
		for (Object element: collection) {
			if (!contains(element)) {
				return false;
			}
		}
		return true;
	}
	// ����ð� == O(1)
	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
	// equal�޼��尡 ����ð��̸� for���� �迭�� ũ�⿡ ������ �����Ƿ� ���� �޼����̴�.(O(n))
	public int indexOf(Object target) {
		for(int i = 0 ; i < size ; i++) {
			if(equals(target, array[i])) {
				return 1;
			}
		}
		return -1;
	}

	/** Checks whether an element of the array is the target.
	 *
	 * Handles the special case that the target is null.
	 *
	 * @param target
	 * @param object
	 */
	// ����ð� �޼��� for���� ���� ������ ����� ���
	private boolean equals(Object target, Object element) {
		if (target == null) {
			return element == null;
		} else {
			return target.equals(element);
		}
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public Iterator<T> iterator() {
		// �迭�� ���纻�� �����.
		T[] copy = Arrays.copyOf(array, size);
		// ArraysŬ������ ����Ŭ���� ArrayList�� iterator�� ��ȯ�Ѵ�.
		return Arrays.asList(copy).iterator();
	}

	@Override
	public int lastIndexOf(Object target) {
		// ���ϴ� ������ �ε��� �� ���� ū���� ��ȯ�Ѵ�.
		for (int i = size-1; i>=0; i--) {
			if (equals(target, array[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<T> listIterator() {
		// �迭�� �����Ѵ�.
		T[] copy = Arrays.copyOf(array, size);
		// ArraysŬ������ ����Ŭ���� ArrayList�� listiterator�� ��ȯ�Ѵ�.
		return Arrays.asList(copy).listIterator();
	}

	@Override
	public ListIterator<T> listIterator(int index) {
		T[] copy = Arrays.copyOf(array, size);
		return Arrays.asList(copy).listIterator(index);
	}

	@Override
	public boolean remove(Object obj) {
		int index = indexOf(obj);
		if (index == -1) {
			return false;
		}
		remove(index);
		return true;
	}

	@Override
	// ������ index�� �ִ� ���� �̾Ƴ��� �޼���
	// for���� ����Ƚ���� n�� ����ϸ�, get�޼���� ����ð��̹Ƿ�
	// O(n)
	public T remove(int index) {
		T element = get(index);
		for(int i = index; i < size-1;i++) {
			array[i] = array[i+1];
		}
		size--;
		return element;
	}

	@Override
	public boolean removeAll(Collection<?> collection) {
		boolean flag = true;
		// remove �޼��尡 ���� �޼����̰�, �� �޼��尡 for�� �ȿ� �־� 2����� �����ϱ� ������,
		// for���� ����Ƚ���� obj�� ����Ѵ�. ���� obj�� ������ 1�� ��쿡��  ����, ������ ��� m�� ��쿡�� ����,
		// n�� ����ϴ� ���� 2���� �ȴ�. ���� �ܼ��� for���� ������ �߿��� ���� �ƴ϶�
		// ����� ũ�� ���� �߿��ϴ�.
		for (Object obj: collection) {
			// flag = flag & remove(obj)�� ����. ���⼭ &�� ��Ʈ������ �������� �ǹ��Ѵ�.
			flag &= remove(obj);
		}
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	// ����ð� �޼��� == O(1)
	// get �޼��嵵 ����ð��̰� �װ��� ������ �ڵ�鵵 �� ����ð��̹Ƿ�
	// index��ġ�� �ִ� ���� element�� �ٲ� �ڿ� ���� �ִ� ���� ��ȯ�Ѵ�.
	public T set(int index, T element) {
		T old = get(index);
		array[index] = element;
		return old;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	public List<T> subList(int fromIndex, int toIndex) {
		if (fromIndex < 0 || toIndex >= size || fromIndex > toIndex) {
			throw new IndexOutOfBoundsException();
		}
		T[] copy = Arrays.copyOfRange(array, fromIndex, toIndex);
		return Arrays.asList(copy);
	}

	@Override
	public Object[] toArray() {
		return Arrays.copyOf(array, size);
	}

	@Override
	public <U> U[] toArray(U[] array) {
		throw new UnsupportedOperationException();
	}
}