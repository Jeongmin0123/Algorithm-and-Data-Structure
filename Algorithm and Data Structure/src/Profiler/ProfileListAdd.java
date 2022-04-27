package Profiler;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.jfree.data.xy.XYSeries;

import Profiler.Profiler.Timeable;

public class ProfileListAdd {
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		//profileArrayListAddEnd();
		//profileArrayListAddBeginning();
		//profileLinkedListAddBeginning();
		profileLinkedListAddEnd();
	}

	// Estimated slope= 1.0705603942275972
	public static void profileArrayListAddEnd() {
		// Timeable 객체 생성
		// 이 때 , Timeable 객체는 setup,timeMe 메서드 가지고 있음
		Timeable timeable = new Timeable() {
			List<String> list;
			public void setup(int n) {
				list = new ArrayList<String>();
			}
			
			// n개의 요소 추가
			public void timeMe(int n) {
				for (int i=0; i<n; i++) {
					list.add("a string");
				}
			}
		};
		int startN = 12000;
		int endMillis = 1000;
		// 이 때, ArrayList의 맨 마지막에 요소를 추가하는 것은 상수 시간라면, N개를 추가하면 1차식과 같아야 한다
		// 따라서 , 밑의 식을 돌려서 나온 추정 기울기가 1.06정도이므로 1과 가까우므로 맨 뒤에 한번 추가하는 add메서드는 상수 함수임을 알 수 있다.
		runProfiler("ArrayList add end", timeable, startN, endMillis);
	}
	
	// Estimated slope= 1.9670940599149815
	public static void profileArrayListAddBeginning() {
		Timeable timeable = new Timeable() {
			List<String> list;
			public void setup(int n) {
				list = new ArrayList<String>();
			}
			
			// n개의 요소 추가
			public void timeMe(int n) {
				for (int i=0; i<n; i++) {
					list.add(0, "a string");
				}
			}
		};
		int startN = 4000;
		int endMillis = 10000;
		runProfiler("ArrayList add Beginning", timeable, startN, endMillis);
	}
	
	// Estimated slope= 1.399016459315778
	public static void profileLinkedListAddBeginning() {
		Timeable timeable = new Timeable() {
			List<String> list;
			public void setup(int n) {
				list = new LinkedList<String>();
			}
			
			// n개의 요소 추가
			public void timeMe(int n) {
				for (int i=0; i<n; i++) {
					list.add(0, "a string");
				}
			}
		};
		int startN = 128000;
		int endMillis = 2000;
		runProfiler("LinkedList add Beginning", timeable, startN, endMillis);
	}
	// Estimated slope= 1.3788498238351183
	// LinkedList의 addEnd 메서드가 선형이라고 예상해서 결과가 2에 가깝다고 생각했는데 다르게 나옴
	// 이유 : LinkedList는 이중 연결 리스트를 구현한 것인데, 이중 연결 리스트의 인덱스를 활용하는 모든 연산
	// index와 가까운 곳에서 시작한다. 따라서 값이 1과 가깝게 나온 것이다.
	public static void profileLinkedListAddEnd() {
		Timeable timeable = new Timeable() {
			List<String> list;
			public void setup(int n) {
				list = new LinkedList<String>();
			}
			
			// n개의 요소 추가
			public void timeMe(int n) {
				for (int i=0; i<n; i++) {
					list.add("a string");
				}
			}
		};
		int startN = 64000;
		int endMillis = 1000;
		runProfiler("LinkedList add End", timeable, startN, endMillis);
	}
	// 이를 통해 알 수 있는 것
	// ArrayList가 LinkedList보다 이점인 부분은 get 과 set 메서드이다.
	// 따라서, get set 메서드를 많이 사용한다면 ArrayList
	// 나머지 추가 혹은 제거가 많다면 LinkedList가 유리하다.
	// 하지만 이런 선택은 꼭 맞는것이 아니다.(저장공간, 소규모 작업량 등)

	
	 // 실행 후에 결과값을 그래프로 보여줌
	private static void runProfiler(String title, Timeable timeable, int startN, int endMillis) {
		Profiler profiler = new Profiler(title, timeable);
		XYSeries series = profiler.timingLoop(startN, endMillis);
		profiler.plotResults(series);
	}
}