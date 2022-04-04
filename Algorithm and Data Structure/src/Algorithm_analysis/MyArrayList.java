package Algorithm_analysis;

import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

// 밑에서 MyArrayList<T>나 add(T element) 등에서 T가 의미하는 것은
// 제네릭 타입으로써 클래스를 설계할 때, 구체적인 타입을 지정하지 않고 사용될 때
// 구체적인 타입을 지정하면서 사용한다.
public class MyArrayList<T> implements List<T> {
	int size;                    // 요소의 개수를 추적함
	private T[] array;           // 요소를 저장하는 배열

	// @SuppressWarnings은 컴파일 경고를 무시하는 어노테이션이다.
	// 이 때, unchecked는 검증되지 않은 연산자 관련 경고를 억제한다.
	@SuppressWarnings("unchecked")
	public MyArrayList() {
		// 이 떼, array = new Object[10]; 는 불가능하다.
		// 왜나하면 array의 형태는 T[] 가 되어야 되기 때문이다(12번 라인참고)
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
			// 제거 할 수 없는 경고 메시지를 제거한다.
			@SuppressWarnings("unchecked")
			T[] bigger = (T[]) new Object[array.length * 2];
			System.arraycopy(array, 0, bigger, 0, array.length);
			array = bigger;
			array[size] = element;
			size++;
		}
		return true;
	} 

	@Override
	public void add(int index, T element) {
		if (index < 0 || index > size) {
			// IndexOutOfBoundsException()는 배열의 크기를 벗어나거나 index값이 0보다 작은 경우
			// 배열에 입력될 수 없을 때 나타난다.
			throw new IndexOutOfBoundsException();
		}
		// element를 추가한다.
		add(element);

		// 요소들의 위치를 바꾼다.
		for (int i=size-1; i>index; i--) {
			array[i] = array[i-1];
		}
		// 요소들의 위치를 바꾼 뒤에 원하는 위치에 element를 넣어준다.
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
	// 배열을 clear 하는 메서드
	public void clear() {
		size = 0;
	}

	@Override
	public boolean contains(Object obj) {
		// obj를 포함하는지 안하는지 알려주는 메서드
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

	@Override
	public T get(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException();
		}
		return array[index];
	}

	@Override
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
		// 배열의 복사본은 만든다.
		T[] copy = Arrays.copyOf(array, size);
		// Arrays클래스의 내부클래스 ArrayList의 iterator를 반환한다.
		return Arrays.asList(copy).iterator();
	}

	@Override
	public int lastIndexOf(Object target) {
		// 원하는 숫자의 인덱스 중 가장 큰값을 반환한다.
		for (int i = size-1; i>=0; i--) {
			if (equals(target, array[i])) {
				return i;
			}
		}
		return -1;
	}

	@Override
	public ListIterator<T> listIterator() {
		// 배열을 복사한다.
		T[] copy = Arrays.copyOf(array, size);
		// Arrays클래스의 내부클래스 ArrayList의 listiterator를 반환한다.
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
	// 임의의 index에 있는 값을 뽑아내는 메서드
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
		for (Object obj: collection) {
			flag &= remove(obj);
		}
		return flag;
	}

	@Override
	public boolean retainAll(Collection<?> collection) {
		throw new UnsupportedOperationException();
	}

	@Override
	// index위치에 있는 값을 element로 바꾼 뒤에 원래 있던 값을 반환한다.
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
