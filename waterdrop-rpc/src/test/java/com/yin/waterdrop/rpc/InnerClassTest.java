package com.yin.waterdrop.rpc;

public class InnerClassTest 
{
	public static void main(String[] args) 
	{
		 Object o =null;
		 AccountableAdapter a =(AccountableAdapter) o;
		 System.out.println(a);
	}
}


interface Accountable  
{  
    public double getBalance();  
    public void setBalance(double d);  
}  
  
class AccountableAdapter implements Accountable  
{  
    public double getBalance()  
    {  
        return 0;  
    }     
    public void setBalance(double d)  
    {  
    }  
}  
  