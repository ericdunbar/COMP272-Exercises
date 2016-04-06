/**
 * Page 30. ArrayStack
 * 
 * An ArrayStack implements a list interface using a backing array.
 * 
 * See
 * http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/8u40-
 * b25/java/lang/Object.java#Object%5B%5D
 * 
 * @author erdun
 *
 */
public class ArrayStack {

	Object a[]; // backing array
	int n; // tracks current size of ArrayStack

	public int size() {
		return n;
	}

	/**
	 * Gets the current object. Check boundaries and throw...
	 * 
	 * @param index
	 * @return
	 */
	public Object get(int index) {
		rangeCheck(index); // seems redundant. Why check for error when Java
							// does that?
		return a[index];
	}

	/**
	 * Ensure that index is a valid index. Throws IndexOutOfBoundsException.
	 * 
	 * @param index
	 */
	private void rangeCheck(int index) {
		// http://grepcode.com/file/repository.grepcode.com/
		// java/root/jdk/openjdk/6-b27/java/util/ArrayList.java#ArrayList.rangeCheck%28int%29

		if (index >= this.n) {
			throw new IndexOutOfBoundsException("Index: " + index + ", Size: " + this.n);
		}
	}

	/**
	 * Sets the object passed as a parameter. Check boundaries and throw...
	 * 
	 * @param index
	 * @param T
	 * @return
	 */
	public Object set(int index, Object T) {
		rangeCheck(index); // seems redundant. Why check for errors when Java
							// does that?
		Object oldElement = a[index]; // the old item that was replaced
		a[index] = T;
		return oldElement;
	}
	
	public void add(int index, Object T){
		
	}
}
