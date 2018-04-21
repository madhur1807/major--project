package minor;
import java.io.*;
import java.util.Stack;

public class comment {
	static int line = 0;
	static int flag = 0;
	
	public static void main(String[] args) throws IOException{
        String s;
        Stack  lineNumberSingleComment= new Stack();
        Stack  lineNumberMultiComment= new Stack();
        
        File file = new File("C:/Users/mp199/Documents/ServiceUtil.java");
       
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        if (!(file.isFile() && file.canRead())) {
            System.out.println(file.getName() + " cannot be read from.");
            return;
        }
        
        FileReader freader = new FileReader(file);
        BufferedReader in = new BufferedReader(freader);
        while ((s = in.readLine()) != null) {
        	line++;
        	if (s.matches("(.*)//(.*)")) {            	
        		flag=1;
        		lineNumberSingleComment.push(line);
            }
        	if(s.matches("(.*)/\\*(.*)")){
        		flag=1;
        		lineNumberMultiComment.push(line);
        	}
        }
        
        if (flag==1) { 
        	if(lineNumberSingleComment.size() > 1){
        		System.out.println("Single Line Comments Encountered at Line Numbers: "+ lineNumberSingleComment);
        	}else{
        		System.out.println("Single Line Comment Encountered at Line Number: "+ lineNumberSingleComment);
        	}
        	
        	if(lineNumberMultiComment.size() > 1){
        		System.out.println("Multi-Line Comments Encountered at Line Numbers: "+ lineNumberMultiComment);
        	}else{
        		System.out.println("Multi-Line Comment Encountered at Line Number: "+ lineNumberMultiComment);
        	}
        	System.out.println("");
        	System.out.println("*********************************************************************");
        	System.out.println("");
        	System.out.println("Ignore in following cases: \n1. When explaining why something is being implemented in a particular way. \n2. When explaining complex algorithms (when all other methods for simplifying the algorithm have been tried and come up short).");
        	System.out.println("");
        	System.out.println("*********************************************************************");
        }else{
        	System.out.println("No Comment Encountered");
        }
    }
}
