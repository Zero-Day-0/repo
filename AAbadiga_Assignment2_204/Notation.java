public class Notation {
	
	private static double operation(String first, String second,char op) {
		double result=0,fir,sec;
		switch(op) {
			case '+':
				fir=Double.parseDouble(first);
				sec=Double.parseDouble(second);
				result=fir+sec;
			break;
		
			case '-':
				fir=Double.parseDouble(first);
				sec=Double.parseDouble(second);
				result=fir-sec;
			break;
		
			case '*':
				fir=Double.parseDouble(first);
				sec=Double.parseDouble(second);
				result=fir*sec;
			break;
		
			case '/':
				fir=Double.parseDouble(first);
				sec=Double.parseDouble(second);
				result=fir/sec;
			break;
		
			default:
				System.out.println("Unknown operator");
		}	
		return result;
	}
	
	public static String convertPostfixToInfix(String postfix) throws InvalidNotationFormatException {
		String infix;
		String top;
		MyStack<String> infixStack= new MyStack<String>();
		char[] temp=postfix.toCharArray();
		
		try {
			for(int i=0;i<temp.length;i++) {
				if(temp[i]==' ') {
					continue;
				}
				if(Character.isDigit(temp[i])) {
					infixStack.push(String.valueOf(temp[i]));
				}
				if(temp[i]=='+'||temp[i]=='-'||temp[i]=='/'||temp[i]=='*') {
					if(infixStack.size()<2) {
						throw new InvalidNotationFormatException();
					}
					else {
						top=infixStack.pop();
						infix="("+infixStack.pop()+temp[i]+top+")";
						infixStack.push(infix);
					}
				}
			}
			if(infixStack.size()>1) {
				throw new InvalidNotationFormatException();
			}
		}
		catch(StackOverflowException sv) {
			sv.printStackTrace();
		}
		catch(StackUnderflowException sd) {
			sd.printStackTrace();
		}
		return infixStack.toString();
	}
	
	/**
	 * @param
	 * @return
	 * @throws
	 */
	public static String convertInfixToPostfix(String infix) throws InvalidNotationFormatException {
		
		MyStack<String> postfixStack=new MyStack<String>();
		MyQueue<String> postfixQueue=new MyQueue<String>();
		char[] temp=infix.toCharArray();
		
		try {
			for(int i=0;i<temp.length;i++) {
				if(temp[i]==' ') {
					continue;
				}
				if(Character.isDigit(temp[i])) {
					postfixQueue.enqueue(String.valueOf(temp[i]));
				}
				if(temp[i]=='(') {
					
					postfixStack.push(String.valueOf(temp[i]));
				}
				
				if(temp[i]=='+') {
					if(!postfixStack.isEmpty()) {
						while(postfixStack.top().equals("+")||postfixStack.top().equals("-")||postfixStack.top().equals("*")||postfixStack.top().equals("/")) {
							postfixQueue.enqueue(postfixStack.pop());
						}
					}
					postfixStack.push(String.valueOf(temp[i]));
				}
				if(temp[i]=='-') {
					if(!postfixStack.isEmpty()) {
						while(postfixStack.top().equals("+")||postfixStack.top().equals("-")||postfixStack.top().equals("*")||postfixStack.top().equals("/")) {
							postfixQueue.enqueue(postfixStack.pop());
						}
					}
					postfixStack.push(String.valueOf(temp[i]));
				}
				if(temp[i]=='/') {
					if(!postfixStack.isEmpty()) {
						while(postfixStack.top().equals("*")||postfixStack.top().equals("/")) {
							postfixQueue.enqueue(postfixStack.pop());
						}
					}
					postfixStack.push(String.valueOf(temp[i]));
				}
				if(temp[i]=='*') {
					if(!postfixStack.isEmpty()) {
						while(postfixStack.top().equals("*")||postfixStack.top().equals("/")) {
							postfixQueue.enqueue(postfixStack.pop());
						}
					}
					postfixStack.push(String.valueOf(temp[i]));
				}
				if(temp[i]==')') {
					while(!postfixStack.isEmpty()&&!postfixStack.top().equals("(")) {
						postfixQueue.enqueue(postfixStack.pop());
					}
					if(postfixStack.isEmpty()||!postfixStack.top().equals("(")) {
						throw new InvalidNotationFormatException();
					}
					
					if(!postfixStack.isEmpty()&&postfixStack.top().equals("(")){
						postfixStack.pop();
					}
				}
			}
			while(!postfixStack.isEmpty()&&!postfixStack.top().equals("(")) {
				postfixQueue.enqueue(postfixStack.pop());
			}
		}
		catch(QueueOverflowException qv) {
			qv.printStackTrace();
		}
		catch(StackOverflowException sv) {
			sv.printStackTrace();
		}
		catch(StackUnderflowException sd) {
			sd.printStackTrace();
		}
		return postfixQueue.toString();
	}
	
	public static double evaluatePostfixExpression(String postfixExpr) throws InvalidNotationFormatException {
		String first, second;
		double result = 0;
		MyStack<String> postfixStack=new MyStack<String>();
		char[] temp = postfixExpr.toCharArray();
		
		try {
			for(int i = 0;i < temp.length;i++) {
				if(temp[i] == ' ') {
					continue;
				}
				if(Character.isDigit(temp[i])|| temp[i] == '(') {
					postfixStack.push(String.valueOf(temp[i]));
				}
				else {
					if(postfixStack.size()<2) {
						throw new InvalidNotationFormatException();
					}
					else {
						second=postfixStack.pop();
						first=postfixStack.pop();
						result=operation(first,second,temp[i]);
						postfixStack.push(Double.toString(result));
						
					}
				}
			}
			if(postfixStack.size()>1) {
				throw new InvalidNotationFormatException();
			}
		}
		catch(StackOverflowException d) {
			d.printStackTrace();
		}
		catch(StackUnderflowException s) {
			s.printStackTrace();
		}
		
					
		return result;
	}
	
	/**
	 * @param
	 * @return
	 * @throws
	 */
	public static double evaluateInfixExpression(String infixExpr) throws InvalidNotationFormatException {
		double result = 0;
		result = evaluatePostfixExpression(convertInfixToPostfix(infixExpr));
		
		return result;
	}
}