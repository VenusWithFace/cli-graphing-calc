public class sleep {
  public static void sleep(int t_sleep) {

    try {
      // Pause execution for 2 seconds (2000 milliseconds)
      Thread.sleep(t_sleep);

    } catch (InterruptedException e) {
      // Handle the case where another thread interrupts the sleeping thread
      System.err.println("Thread was interrupted while sleeping: " + e.getMessage());
      // Restore the interrupted status
      Thread.currentThread().interrupt(); 
    }
    
  }
}