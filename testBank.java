import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class testBank {

	public static final int antallKontoer = 10;
	public static final int grunnBeløp = 10000;

	public static void main(String[] args) {
		Bank b = new Bank(antallKontoer, grunnBeløp);
		for (int i = 0; i < antallKontoer; i++) {
			TransferThread t = new TransferThread(b, i, grunnBeløp);
			t.setPriority(Thread.NORM_PRIORITY + i % 2);
			t.start();
		}
	}
}

 class Bank {
	public static final int NTEST = 10000;
	private static int[] kontoer;
	public static long overføringer = 0;

	public Bank(int n, int initialBalance) {
		kontoer = new int[n];
		int i;
		for (i = 0; i < kontoer.length; i++)
			kontoer[i] = initialBalance;
		overføringer = 0;
	}


//	public static class TrekkeUt implements Runnable {
//		@Override
//		public void run() {
//			while (true) {
//				konto.trekkeUt( 1,(int) (Math.random() * 1000) + 1);
//			}
//		}
//	}

	public int size() {
		return kontoer.length;
	}
	
	public static class Konto {
		private static Lock lås = new ReentrantLock();
		private static Condition nyOverføring = lås.newCondition();
		private int overføringer = 0;
		int ntest = 500;

		public void trekkeUt(int fra, int mengde) {
			lås.lock();
			try {
				while (kontoer[fra] < mengde) {
					System.out.println("\t\t\tVenter på overføring");
					nyOverføring.await();
				}
				kontoer[fra] -= mengde;
				//System.out.println("Tar ut: " + mengde + " kr\t\tfra konto: " + fra + "\t\tPenger på konto " + fra + ": " + kontoer[fra]);
				this.overføringer++;
				if (overføringer % ntest == 0) test ();
			} 
			catch (InterruptedException ex) {
				ex.printStackTrace();
			} finally {
				lås.unlock();
			}
		}

		public void overfør(int til, int mengde) {
			lås.lock();
			try {
				kontoer[til] += mengde;
				//System.out.println("Overført: " + mengde + " kr\t\ttil konto: " + til + "\t\tPenger på konto " + til + ": " + kontoer[til]);
				nyOverføring.signalAll();
				this.overføringer++; 
				if (overføringer % ntest == 0) test (); 
			} finally {
				lås.unlock();
			}
		}
	}
	public static void test() {
		int sum = 0;
		for (int i = 0; i < kontoer.length; i++)
			sum += kontoer[i];
		System.out.println("\nAntall overføringer:" + overføringer + " Sum: " + sum + "\n");
	}
}

class TransferThread extends Thread {
	private Bank bank;
	private int fromAccount;
	private int maxAmount;
	private static final int REPS = 1000;
	private static Bank.Konto konto = new Bank.Konto();
	private int overføringer = 1;

	public TransferThread(Bank b, int from, int max) {
		bank = b;
		fromAccount = from;
		maxAmount = max;
	}

	@Override
	public void run() {
		try {
			while (!interrupted()) {
				for (int i = 0; i < REPS; i++) {
					int toAccount = (int) (bank.size() * Math.random());
					int amount = (int) (maxAmount * Math.random() / REPS);
					konto.trekkeUt(fromAccount, amount);
					konto.overfør (toAccount, amount);
					sleep(5);
				}
			}
		} catch (InterruptedException e) {
		}
	}

}