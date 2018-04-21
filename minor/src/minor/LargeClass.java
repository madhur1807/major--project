package minor;

import java.io.*;
import java.util.Stack;

public class LargeClass {
	static long lastposition = 00;
	static int noOflargeClass = 0;
	static int line = 0;
	static int catchNumber = 0;
	public static void countingLine(File file, long lastpos, int line ) {
    	catchNumber++;
    	int lineCount = 0;
        int count = 0;
//        System.out.println("function");
        Stack st = new Stack();
        st.push("Asd");
        try {
        	FileInputStream fis = new FileInputStream(file);
    		BufferedReader br = new BufferedReader(new FileReader(file));
            fis.skip(lastpos - 4);
            char current;
            do{
            	current = (char) fis.read();
            	if(current == '{') {
                    st.push(current);
                    count++;
                }
            	if(current == '}') {
                    st.pop();
                    count--;
                    if(count == 0){
//                        lastposition = (char)fis.getChannel().position();
                        st.pop();
                    }
                }
                if((current == '\n') && (st.peek() != "Asd")) {
                    lineCount++;
                }            
            }while(!st.empty());
            if(lineCount > 200){
            	System.out.println("Large Class found");
            	System.out.println("Class number : " + catchNumber + " in the file");
            	noOflargeClass++;
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
	public static void main(String[] args) throws IOException{
        String s;
        int classNo=0;
        //File file = new File("D:/User/Desktop/javatry.text");
        File file = new File("D:/User/Desktop/ServiceUtil.java");
        if (!file.exists()) {
            System.out.println("File does not exist.");
            return;
        }
        if (!(file.isFile() && file.canRead())) {
            System.out.println(file.getName() + " cannot be read from.");
            return;
        }

        FileReader freader = new FileReader(file);
        BufferedReader br = new BufferedReader(freader);
        while ((s = br.readLine()) != null) {
        	line++;
            if (s.matches("(.*) class (.*)")) {            	
            	
            	FileInputStream fis = new FileInputStream(file);
        		int numberOfLine = 0;
        		int i = 0;
            	while(numberOfLine < line ){
            		if(fis.read() == '\n'){
            			numberOfLine++;
            		}
            		i++;
            	}
            	lastposition = i;
            	countingLine(file,lastposition, line);
            }
        }
        System.out.println("Total number of large class = " + noOflargeClass);
    }
}
