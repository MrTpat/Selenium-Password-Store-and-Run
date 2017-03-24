package authentication;

import java.io.*;
import java.util.Arrays;
import java.util.Random;
import java.util.UUID;

public class Authentication implements java.io.Serializable{
	
	private String userName, passWord;
	public Authentication(String userName){
	this.userName=userName;
	}
	public Authentication(){
		
	}
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassWord() {
		return passWord;
	}
	public String generate(String req){
		String pass =  UUID.randomUUID().toString().replaceAll("-", "");
		String[] passArray = pass.split("");
		final int[] ints = new Random().ints(0, pass.length()).distinct().limit(req.length()).toArray();
		for(int i =0;i<ints.length;i++){
			passArray[ints[i]]=req.substring(i,i+1);
		}
		 pass="";
		for(int i=0;i<passArray.length;i++){
			pass+=passArray[i];
		}
		return pass;
	}
	public void setPassWord(String passWord) {
		this.passWord = passWord;
	}
	public void save(){
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream(userName.toLowerCase()+".authentication");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(this);
	         out.close();
	         fileOut.close();
	      }catch(IOException i) {
	         i.printStackTrace();
	      }
	   }
	public Authentication retrieve(String name){
		Authentication e = null;
	      try {
	         FileInputStream fileIn = new FileInputStream(name.toLowerCase()+".authentication");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         e = (Authentication) in.readObject();
	         in.close();
	         fileIn.close();
	         
	      }catch(IOException i) {
	         i.printStackTrace();
	       
	      }catch(ClassNotFoundException c) {
	         System.out.println("Employee class not found");
	         c.printStackTrace();
	         
	      }
	      return e;
	}
}
