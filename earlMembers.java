import java.io.*;
class earlMembers
{
private static final String DATA_FILE="member.data";
public static void main(String args[])
{
if(args.length==0)
{
System.out.println("Please specify an operation");
return;
}
String operation=args[0];
if(!isOperationValid(operation))
{
System.out.println("Invalid operation : "+operation);
System.out.println("Valid operations are : [add,update,remove,getAll,getByContactNumber,getByCourse]");
return;
}
if(operation.equalsIgnoreCase("add"))
{
add(args);
}else
if(operation.equalsIgnoreCase("update"))
{
update(args);
}else
if(operation.equalsIgnoreCase("remove"))
{
remove(args);
}else
if(operation.equalsIgnoreCase("getAll"))
{
getAll(args);
}else
if(operation.equalsIgnoreCase("getByContactNumber"))
{
getByContactNumber(args);
}else
if(operation.equalsIgnoreCase("getByCourse"))
{
getByCourse(args);
}

}//main ends

//operation functions
//add starts
private static void add(String [] data)
{
if(data.length!=5)
{
System.out.println("Not enough data to add");
return;
}
String mobileNumber=data[1];
String name=data[2];
String course=data[3];
if(!isCourseValid(course))
{
System.out.println("Invalid course : "+course);
System.out.println("Available Courses : [C,C++,Java,J2EE,Python]");
return;
}
int fee;
try
{
fee=Integer.parseInt(data[4]);
}catch(NumberFormatException numberFormatException)
{
System.out.println("Fee should be an integer type value");
return;
}
try
{
File file=new File(DATA_FILE);
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
String fMobileNumber;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
if(fMobileNumber.equalsIgnoreCase(mobileNumber))
{
randomAccessFile.close();
System.out.println(mobileNumber+"exists.");
return;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
randomAccessFile.writeBytes(mobileNumber);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(name);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(course);
randomAccessFile.writeBytes("\n");
randomAccessFile.writeBytes(String.valueOf(fee));
randomAccessFile.writeBytes("\n");
randomAccessFile.close();
System.out.println("Member added");
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
return;
}
}//add function ends


//update starts
private static void update(String [] data)
{
if(data.length!=5)
{
System.out.println("Wrong number of elements passed for update");
System.out.println("Usage : java earlMembers update mobile_number name course fee");
return;
}
String mobileNumber=data[1];
String name=data[2];
String course=data[3];
if(!isCourseValid(course))
{
System.out.println("Invalid course : "+course);
System.out.println("Available courses : [C/C++/Java/J2EE/Python]");
return;
}
int fee;
try
{
fee=Integer.parseInt(data[4]);
}catch(NumberFormatException numberFormatException)
{
System.out.println("Fee should be an integer type value");
return;
}
try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
System.out.println("Invalid contact number : "+mobileNumber);
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
System.out.println("Invalid contact Number : "+mobileNumber);
return;
}
boolean found=false;
String fMobileNumber="";
String fName="";
String fCourse="";
int fFee=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourse=randomAccessFile.readLine();
fFee=Integer.parseInt(randomAccessFile.readLine());
if(fMobileNumber.equalsIgnoreCase(mobileNumber))
{
found=true;
break;
}
}
if(found==false)
{
System.out.println("Invalid contact Number : "+mobileNumber);
randomAccessFile.close();
return;
}
System.out.println("Updating data of : "+mobileNumber);
randomAccessFile.seek(0);
System.out.println("Name of candidate is : "+fName);
File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.seek(0);
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourse=randomAccessFile.readLine();
fFee=Integer.parseInt(randomAccessFile.readLine());
if(fMobileNumber.equalsIgnoreCase(mobileNumber)==false)
{
tmpRandomAccessFile.writeBytes(fMobileNumber+"\n");
tmpRandomAccessFile.writeBytes(fName+"\n");
tmpRandomAccessFile.writeBytes(fCourse+"\n");
tmpRandomAccessFile.writeBytes(fFee+"\n");
}
else
{
tmpRandomAccessFile.writeBytes(mobileNumber+"\n");
tmpRandomAccessFile.writeBytes(name+"\n");
tmpRandomAccessFile.writeBytes(course+"\n");
tmpRandomAccessFile.writeBytes(fee+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
randomAccessFile.setLength(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
System.out.println("Data updated\n");
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}//update ends





//remove starts
private static void remove(String [] data)
{
if(data.length!=2)
{
System.out.println("Wrong number of elements passed for removal");
System.out.println("Usage : java earlMembers remove mobile_number");
return;
}
String mobileNumber=data[1];
int fee;
try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
System.out.println("Invalid contact number : "+mobileNumber);
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
System.out.println("Invalid contact Number : "+mobileNumber);
return;
}
boolean found=false;
String fMobileNumber="";
String fName="";
String fCourse="";
int fFee=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourse=randomAccessFile.readLine();
fFee=Integer.parseInt(randomAccessFile.readLine());
if(fMobileNumber.equalsIgnoreCase(mobileNumber))
{
found=true;
break;
}
}
if(found==false)
{
System.out.println("Invalid contact Number : "+mobileNumber);
randomAccessFile.close();
return;
}
System.out.println("Deleting data of : "+mobileNumber);
System.out.println("Name of candidate is : "+fName);
File tmpFile=new File("tmp.tmp");
RandomAccessFile tmpRandomAccessFile;
tmpRandomAccessFile=new RandomAccessFile(tmpFile,"rw");
tmpRandomAccessFile.setLength(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourse=randomAccessFile.readLine();
fFee=Integer.parseInt(randomAccessFile.readLine());
if(fMobileNumber.equalsIgnoreCase(mobileNumber)==false)
{
tmpRandomAccessFile.writeBytes(fMobileNumber+"\n");
tmpRandomAccessFile.writeBytes(fName+"\n");
tmpRandomAccessFile.writeBytes(fCourse+"\n");
tmpRandomAccessFile.writeBytes(fFee+"\n");
}
}
randomAccessFile.seek(0);
tmpRandomAccessFile.seek(0);
while(tmpRandomAccessFile.getFilePointer()<tmpRandomAccessFile.length())
{
randomAccessFile.writeBytes(tmpRandomAccessFile.readLine()+"\n");
}
randomAccessFile.setLength(tmpRandomAccessFile.length());
tmpRandomAccessFile.setLength(0);
randomAccessFile.close();
tmpRandomAccessFile.close();
System.out.println("Data deleted\n");
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}//remove ends



//getAll starts
private static void getAll(String [] data)
{
try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
System.out.println("No Members");
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
randomAccessFile.close();
System.out.println("No Members");
return;
}
String name;
String mobileNumber;
String course;
int fee;
int memberCount=0;
int totalFee=0;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
mobileNumber=randomAccessFile.readLine();
name=randomAccessFile.readLine();
course=randomAccessFile.readLine();
fee=Integer.parseInt(randomAccessFile.readLine());
System.out.printf("%s,%s,%s,%d\n",mobileNumber,course,name,fee);
totalFee+=fee;
memberCount++;
}
randomAccessFile.close();
System.out.println("Total registrations : "+memberCount);
System.out.println("Total fee Collected : "+totalFee);
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}//getAll ends




//getByContactNumber starts
private static void getByContactNumber(String [] data)
{
if(data.length!=2)
{
System.out.println("Invalid number of data elements passed");
System.out.println("Usage : java MemberManager getByContactNumber XXXXXXXXXX");
}
String mobileNumber=data[1];
try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
System.out.println("Invalid mobile number : "+mobileNumber);
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
System.out.println("Invalid mobile Number : "+mobileNumber);
randomAccessFile.close();
return;
}
String fMobileNumber="";
String fName="";
String fCourse="";
int fFee=0;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
if(fMobileNumber.equalsIgnoreCase(mobileNumber)==true)
{
fName=randomAccessFile.readLine();
fCourse=randomAccessFile.readLine();
fFee=Integer.parseInt(randomAccessFile.readLine());
found=true;
break;
}
randomAccessFile.readLine();
randomAccessFile.readLine();
randomAccessFile.readLine();
}
randomAccessFile.close();
if(found==false)
{
System.out.println("Invalid mobile Number : "+mobileNumber);
return;
}
System.out.println("Mobile number : "+mobileNumber);
System.out.println("Name : "+fName);
System.out.println("Course : "+fCourse);
System.out.println("Fee : "+fFee);
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}//getByContactNumber ends



	
//getByCourse starts
private static void getByCourse(String [] data)
{
if(data.length!=2)
{
System.out.println("Invalid number of data elements passed");
System.out.println("Usage : java earlMembers getByCourse C/C++/Java/Python/J2EE");
}
String course=data[1];
if(isCourseValid(course)==false)
{
System.out.println("Invalid course : "+course);
return;
}
try
{
File file=new File(DATA_FILE);
if(file.exists()==false)
{
System.out.println("No registration against course : "+course);
return;
}
RandomAccessFile randomAccessFile;
randomAccessFile=new RandomAccessFile(file,"rw");
if(randomAccessFile.length()==0)
{
System.out.println("No registration against course : "+course);
randomAccessFile.close();
return;
}
String fMobileNumber="";
String fName="";
String fCourse="";
int fFee=0;
boolean found=false;
while(randomAccessFile.getFilePointer()<randomAccessFile.length())
{
fMobileNumber=randomAccessFile.readLine();
fName=randomAccessFile.readLine();
fCourse=randomAccessFile.readLine();
fFee=Integer.parseInt(randomAccessFile.readLine());
if(course.equalsIgnoreCase(fCourse))
{
System.out.println("Mobile number : "+fMobileNumber);
System.out.println("Name : "+fName);
System.out.println("Course : "+fCourse);
System.out.println("Fee : "+fFee);
found=true;
}
}
randomAccessFile.close();
if(found==false)
{
System.out.println("No registration against course : "+course);
return;
}
}catch(IOException ioException)
{
System.out.println(ioException.getMessage());
}
}//getByCourse ends



//helper functions
private static boolean isOperationValid(String operation)
{
operation=operation.trim();
String operations[]={"add","update","remove","getAll","getByContactNumber","getByCourse"};
for(int e=0;e<operations.length;e++)
{
if(operations[e].equalsIgnoreCase(operation)) return true;
}
return false;
}

private static boolean isCourseValid(String course)
{
course=course.trim();
String courses[]={"c","c++","java","python","j2ee"};
for(int e=0;e<courses.length;e++)
{
if(courses[e].equalsIgnoreCase(course)) return true;
}
return false;
}
}