package cn.hisdar.lib.test;

import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import cn.hisdar.lib.work.HTask;
import cn.hisdar.lib.work.HTaskFinishInterface;
import cn.hisdar.lib.work.HWork;
import cn.hisdar.lib.work.TaskResult;

public class TestHWorkNoCancel implements HTask, HTaskFinishInterface {

	private HWork testWork;
	
	public TestHWorkNoCancel() {
		
	}
	
	public void startTest() {
		testWork = new HWork();
		testWork.setTitle("��������");
		testWork.setTaskFinishInterface(this);
		testWork.startWork(this);
		System.out.println("startTest:finished");
	}

	@Override
	public int task(HWork work) {
		for (int i = 0; i < 10; i++) {
			System.out.println("Step:" + (i + 1));
			float progress = (i + 1) / 10f;
			testWork.setMessage("��������" + progress * 100 + "%");
			testWork.setProgressValue(progress);
			try {
				Thread.sleep(500);
			} catch (InterruptedException e) {}
		}
		
		return 0;
	}
	
	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		} catch (ClassNotFoundException | InstantiationException
				| IllegalAccessException | UnsupportedLookAndFeelException e) {
			e.printStackTrace();
		}
		
		new TestHWorkNoCancel().startTest();
	}

	@Override
	public void taskFinishEvent(HTask taskResult, int functionResult) {
		System.out.println("Task exec finished:taskResult=" + taskResult);
		System.out.println("Task exec finished:functionResult=" + functionResult);
	}
}
