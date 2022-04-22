import java.util.Queue;
import java.util.ArrayDeque;
import java.util.Random;

public class CarQueue {
   Random rand = new Random();
   private Queue<Integer> queue = new ArrayDeque<Integer>();
   private int out;
   
   public CarQueue() {
	   queue.add(rand.nextInt(4));
       queue.add(rand.nextInt(4));
       queue.add(rand.nextInt(4));
       queue.add(rand.nextInt(4));
       queue.add(rand.nextInt(4));
       queue.add(rand.nextInt(4));
       queue.add(rand.nextInt(4));
   }
   
   public void addToQueue() {
       class AnimationRunnable implements Runnable {
             public void run() {
            	 while (true) {
            		 try {
            			 queue.add(rand.nextInt(4));
            		     Thread.sleep(1000);
 					} catch (InterruptedException exception) {
 						break;
 					}
            	 }
             }
       }
       Runnable r = new AnimationRunnable();
       Thread t = new Thread(r);
       t.start();  
   }
   
   public int deleteQueue() {
       return queue.remove();
   }
}