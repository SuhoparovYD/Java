package stplate.beans;

public class Tile {
	    
	   private int id;
	   private int x;
	   private int y;
	   private String comments;
	    	 
	   public Tile() {
	        
	   }

	   public Tile(int id, int x, int y, String comments) {
	       this.id = id;
	       this.x = x;
	       this.y = y;
	       this.comments = comments;
	   }	   
	   
	   public int getId() {
	       return id;
	   }
	 
	   public void setId(int id) {
	       this.id = id;
	   }
	 	    
	   public int getX() {
	       return x;
	   }
	 
	   public void setX(int x) {
	       this.x = x;
	   }
	 
	   public int getY() {
	       return y;
	   }
	 
	   public void setY(int y) {
		   this.y = y;;
	   }
	 
	   public String getComments() {
	       return comments;
	   }
	 
	   public void setComments(String comments) {
	       this.comments = comments;
	   }

}

