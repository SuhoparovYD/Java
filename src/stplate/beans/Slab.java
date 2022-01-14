package stplate.beans;

public class Slab {
	
	
	   private int id;
	   private int xl;
	   private int xr;
	    	 
	   public Slab() {
	        
	   }

	   public Slab(int id, int xl, int xr) {
	       this.id = id;
	       this.xl = xl;
	       this.xr = xr;
	   }	   
	   
	   public int getId() {
	       return id;
	   }
	 
	   public void setId(int id) {
	       this.id = id;
	   }
	 	    
	   public int getXl() {
	       return xl;
	   }
	 
	   public void setXl(int xl) {
	       this.xl = xl;
	   }
	 
	   public int getXr() {
	       return xr;
	   }
	 
	   public void setXr(int xr) {
		   this.xr = xr;;
	   }

}
