package stplate.beans;

public class Tiles {
	
	   private String id;
	   private String x;
	   private String y;
	   private String comments;
	    	 
	   public Tiles() {
	        
	   }

	   public Tiles(String id, String x, String y, String comments) {
	       this.id = id;
	       this.x = x;
	       this.y = y;
	       this.comments = comments;
	   }	   
	   
	   public String getId() {
	       return id;
	   }
	 
	   public void setId(String id) {
	       this.id = id;
	   }
	 	    
	   public String getX() {
	       return x;
	   }
	 
	   public void setX(String x) {
	       this.x = x;
	   }
	 
	   public String getY() {
	       return y;
	   }
	 
	   public void setY(String y) {
		   this.y = y;;
	   }
	 
	   public String getComments() {
	       return comments;
	   }
	 
	   public void setComments(String comments) {
	       this.comments = comments;
	   }
	

}
