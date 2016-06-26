package com.yinrong.tools.util;

/**
 * @Project vfinance-open-adapter-socket
 * @Description 保留提前通知的线程同步器
 * @Company fujie
 * @Create 2015-4-27
 * @author linqingyou
 */
public class SynLock {

	private boolean notified;

	public synchronized void await(long timeout) throws InterruptedException {
		long curr = System.currentTimeMillis();
		while (!notified && System.currentTimeMillis() - curr < timeout) {
			this.wait(timeout);
		}
	}

	public synchronized void await() throws InterruptedException {
		while (!notified) {
			this.wait();
		}
	}

	public synchronized void signal() {
		if (!notified) {
			notified = true;
			this.notify();
		}
	}
}
