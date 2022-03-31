/**
 * 
 */
package Algorithm_analysis;

import java.util.Arrays;

// 선택 정렬
public class SelectionSort {

	
	// 두 위치에 있는 값을 바꾸는 메서드
	// 요소를 읽거나 쓰는 것은 상수 시간연산이다.
	// 따라서 밑의 메서드는 상수 시간 연산이다.
	public static void swapElements(int[] array, int i, int j) {
		int temp = array[i];
		array[i] = array[j];
		array[j] = temp;
	}

	// start에서 시작하여 가장 작은 요소의 인덱스 값을 반환하는 메서드
	// start 가 0이고 array.length 가 n이라고 가정하면 처음에 비교 연산횟수는 n번
	// 그 다음은 start가 1일 때 (n-1)번 , start가 2일 때, (n-2)번 ,... ,2 ,1 비교 연산을 하게된다.
	// 즉, 메서드의 비교 횟수는 n-k의 형태로 나타낼 수 있다
	// 따라서 이 메서드는 선형이 된다.
	public static int indexLowest(int[] array, int start) {
		int lowIndex = start;
		for (int i = start; i < array.length; i++) {
			if (array[i] < array[lowIndex]) {
				lowIndex = i;
			}
		}
		return lowIndex;
	}
	
	// 아래 메서드는 배열을 정렬하는 메서드이다.
	// for문에서 연산이 n번, 그리고 그 반복문 안에서 선형 알고리즘과 상수 알고리즘이 존재하므로
	// 빅오 표기법에 의해서 반복문 안에서의 연산은 O(n)에 속한다.
	// 따라서 아래 메서드는  O(n)*n 이므로 O(n^2)에 속한다.
	public static void selectionSort(int[] array) {
		for (int i = 0; i < array.length; i++) {
			int j = indexLowest(array, i);
			swapElements(array, i, j);
		}
	}

	
	// 위 모든 메서드를 사용하는 예시 문제
	public static void main(String[] args) {
		int[] array = {2, 5, 6, 1, 3};
		selectionSort(array);
		System.out.println(Arrays.toString(array));
	}

}
