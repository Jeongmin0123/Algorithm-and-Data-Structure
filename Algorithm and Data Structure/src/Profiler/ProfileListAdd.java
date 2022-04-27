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
		// Timeable ��ü ����
		// �� �� , Timeable ��ü�� setup,timeMe �޼��� ������ ����
		Timeable timeable = new Timeable() {
			List<String> list;
			public void setup(int n) {
				list = new ArrayList<String>();
			}
			
			// n���� ��� �߰�
			public void timeMe(int n) {
				for (int i=0; i<n; i++) {
					list.add("a string");
				}
			}
		};
		int startN = 12000;
		int endMillis = 1000;
		// �� ��, ArrayList�� �� �������� ��Ҹ� �߰��ϴ� ���� ��� �ð����, N���� �߰��ϸ� 1���İ� ���ƾ� �Ѵ�
		// ���� , ���� ���� ������ ���� ���� ���Ⱑ 1.06�����̹Ƿ� 1�� �����Ƿ� �� �ڿ� �ѹ� �߰��ϴ� add�޼���� ��� �Լ����� �� �� �ִ�.
		runProfiler("ArrayList add end", timeable, startN, endMillis);
	}
	
	// Estimated slope= 1.9670940599149815
	public static void profileArrayListAddBeginning() {
		Timeable timeable = new Timeable() {
			List<String> list;
			public void setup(int n) {
				list = new ArrayList<String>();
			}
			
			// n���� ��� �߰�
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
			
			// n���� ��� �߰�
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
	// LinkedList�� addEnd �޼��尡 �����̶�� �����ؼ� ����� 2�� �����ٰ� �����ߴµ� �ٸ��� ����
	// ���� : LinkedList�� ���� ���� ����Ʈ�� ������ ���ε�, ���� ���� ����Ʈ�� �ε����� Ȱ���ϴ� ��� ����
	// index�� ����� ������ �����Ѵ�. ���� ���� 1�� ������ ���� ���̴�.
	public static void profileLinkedListAddEnd() {
		Timeable timeable = new Timeable() {
			List<String> list;
			public void setup(int n) {
				list = new LinkedList<String>();
			}
			
			// n���� ��� �߰�
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
	// �̸� ���� �� �� �ִ� ��
	// ArrayList�� LinkedList���� ������ �κ��� get �� set �޼����̴�.
	// ����, get set �޼��带 ���� ����Ѵٸ� ArrayList
	// ������ �߰� Ȥ�� ���Ű� ���ٸ� LinkedList�� �����ϴ�.
	// ������ �̷� ������ �� �´°��� �ƴϴ�.(�������, �ұԸ� �۾��� ��)

	
	 // ���� �Ŀ� ������� �׷����� ������
	private static void runProfiler(String title, Timeable timeable, int startN, int endMillis) {
		Profiler profiler = new Profiler(title, timeable);
		XYSeries series = profiler.timingLoop(startN, endMillis);
		profiler.plotResults(series);
	}
}