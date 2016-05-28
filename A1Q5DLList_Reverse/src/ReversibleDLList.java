/**
 * 5. Exercise 3.12. Write a method, reverse(), that reverses the order of elements in a
 * DLList.
 * 
 * @author erdun
 *
 */
public class ReversibleDLList<T> extends DLList<T extends Comparable<T>> {

	public void reverse(){
		dummy.next = null;
	}
	
	public ReversibleDLList() {
		super();
		// TODO Auto-generated constructor stub
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
