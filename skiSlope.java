package part1;
import java.util.Random;
import java.util.concurrent.BlockingQueue; 
import java.util.concurrent.LinkedBlockingQueue;

public class skiSlope {
	
	public static void main(String[] args){
		BlockingQueue<String> lift = new LinkedBlockingQueue<String>(10);
		System.out.println("On Lift (0): [EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY, EMPTY]");
		for(int i = 0; i < 10; i++){
			String no_skiier = "EMPTY";
			try{
				lift.put(no_skiier);
				}
			catch (InterruptedException e){
				e.printStackTrace();
				}
			}
		skiSlope test = new skiSlope();
		Thread waitingLineThread = test.new waitingLineThread(lift);
		waitingLineThread.start();
		}

	class waitingLineThread extends Thread{
		public BlockingQueue<String> list_of_people;
		public waitingLineThread(BlockingQueue<String> list_of_people){
			this.list_of_people = list_of_people;
			}
		
		public void run(){
			BlockingQueue<String> waitingQueue = new LinkedBlockingQueue<String>(30);
			for(int i = 1; i < 31; i++){
				try{
					waitingQueue.put("" + i + "");
					}
				catch (InterruptedException e){
					e.printStackTrace();
					}
				}
			System.out.println("In Queue ("+ waitingQueue.size()+ "): "+ waitingQueue + "\n");
			   
			try{
				for(int j = 0; j < 20; j++){
					String skiier_bottom_slope = waitingQueue.take();
					String skiier_top_slope = list_of_people.take();
					if (skiier_top_slope != "EMPTY"){
						Random r = new Random();
						Random t = new Random();
						int int_skiier_top_slope = Integer.parseInt(skiier_top_slope);
						if(int_skiier_top_slope > 1){
							int skiier_done = (r.nextInt(int_skiier_top_slope - 0) + 1);
							int ski_time = (t.nextInt(12001 - 2000) + 2000);
							if(!(waitingQueue.contains("" + skiier_done))){
								Thread.sleep(ski_time);
								String string_skiier_done = Integer.toString(skiier_done);
								waitingQueue.put(string_skiier_done);
							}
						}
					}
					if(j == 1 || j == 12 || j == 23){
						Random num = new Random();
						int breaks = (num.nextInt(8001 - 1000) + 1000);
						System.out.println("List stops temporarily (for " + breaks + " milliseconds).");
						Thread.sleep(breaks);
						System.out.println("Lift continues operation." + "\n");
					}
					list_of_people.put(skiier_bottom_slope);
					System.out.println("On lift: " + list_of_people);
					System.out.println("In Queue (" + waitingQueue.size() + "): " + waitingQueue + "\n");
					}
				
			}
			 catch (InterruptedException e) {
				 e.printStackTrace();
				 }
		}
	}	
}