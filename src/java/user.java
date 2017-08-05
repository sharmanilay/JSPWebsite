

public class user {
    
		private String firstname;
		private String lastname;
		private String email;
		private int userID;
		private String phonenumber;
		// get-set functions
		
		
public String getfirstname(){
	return firstname;
}

public String getlastname(){
	return lastname;
}

public String getemail(){
	return email;
}

public int getuserID(){
	return userID;
}

public String getphonenumber(){
	return phonenumber;
}

public void setfirstname(String str){
	this.firstname = str;
}
public void setlastname(String str){
	this.lastname = str;
}
public void setemail(String str){
	this.email = str;
}

public void setuserID(int num){
	this.userID = num;
}
public void setphonenumber(String str){
	this.phonenumber = str;
}

}//end of the class user