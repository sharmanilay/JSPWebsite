
public class ticket {
    
    private int tID;
    private int pID;
    private String theading;
    private String tdescription;
    private int userID;
    private int status;
		// get-set functions
    
    public int gettID(){
	return tID;
    }
    public int getpID(){
	return pID;
    }
    public String gettheading(){
	return theading;
    }
    public String gettdescription(){
	return tdescription;
    }
    public int getuserID(){
	return userID;
    }
    public int getstatus(){
	return status;
    }
    
    
    public void settID(int num){
	this.tID=num;
    }
    public void setpID(int num){
	this.pID=num;
    }
    public void settheading(String str){
	this.theading=str;
    }
    public void settdescription(String str){
	this.tdescription=str;
    }
    public void setuserID(int num){
	this.userID=num;
    }
    public void setstatus(int num){
	this.status=num;
    }
} // end of class ticket
