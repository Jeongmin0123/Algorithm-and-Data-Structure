/**
 * 
 */
package Algorithm_analysis;

import java.util.Arrays;

// ���� ����
public class SelectionSort {

	
	// �� ��ġ�� �ִ� ���� �ٲٴ� �޼���
	// ��Ҹ� �аų� ���� ���� ��� �ð������̴�.
	// ���� ���� �޼���� ��� �ð� �����̴�.
	public static void swapElements(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	// start���� �����Ͽ� ���� ���� ����� �ε��� ���� ��ȯ�ϴ� �޼���
	// start �� 0�̰� array.length �� n�̶�� �����ϸ� ó���� �� ����Ƚ���� n��
	// �� ������ start�� 1�� �� (n-1)�� , start�� 2�� ��, (n-2)�� ,... ,2 ,1 �� ������ �ϰԵȴ�.
	// ��, �޼����� �� Ƚ���� n-k�� ���·� ��Ÿ�� �� �ִ�
	// ���� �� �޼���� ������ �ȴ�.
	public static int indexLowest(int[] array, int start) {
		int lowIndex = start;
		for (int i = start; i < array.length; i++) {
			if (array[i] < array[lowIndex]) {
				lowIndex = i;
			}
		}
		return lowIndex;
	}
	
	// �Ʒ� �޼���� �迭�� �����ϴ� �޼����̴�.
	// for������ ������ n��, �׸��� �� �ݺ��� �ȿ��� ���� �˰���� ��� �˰����� �����ϹǷ�
	// ��� ǥ����� ���ؼ� �ݺ��� �ȿ����� ������ O(n)�� ���Ѵ�.
	// ���� �Ʒ� �޼����  O(n)*n �̹Ƿ� O(n^2)�� ���Ѵ�.
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int j = indexLowest(array, i);
			swapElements(array, i, j);
		}
	}

	
	// �� ��� �޼��带 ����ϴ� ���� ����
	public static void main(String[] args) {
		int[] array = {2, 5, 6, 1, 3};
		selectionSort(array);
		System.out.println(Arrays.toString(array));
	}

}
