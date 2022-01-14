package stplate.beans;

public class Noslab {

	   private int id;
	   private String slabno;
	   private int idfirst;
	   private int idlast;
	    	 
	   public Noslab() {
	        
	   }

	   public Noslab(int id, String slabno, int idfirst, int idlast) {
	       this.id = id;
	       this.slabno = slabno;
	       this.idfirst = idfirst;
	       this.idlast = idlast;
	   }	   
	   
	   public int getId() {
	       return id;
	   }
	 
	   public void setId(int id) {
	       this.id = id;
	   }
	 	    
	   public String getSlabno() {
	       return slabno;
	   }
	 
	   public void setSlabno(String slabno) {
	       this.slabno = slabno;
	   }
	 
	   public int  getIdfirst() {
	       return idfirst;
	   }
	 
	   public void setIdfirst(int idfirst) {
		   this.idfirst = idfirst;;
	   }
	 
	   public int  getIdlast() {
	       return idlast;
	   }
	 
	   public void setIdlast(int idlast) {
		   this.idlast = idlast;;;
	   }

}

