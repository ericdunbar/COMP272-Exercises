import java.util.AbstractSet;
import java.util.Iterator;

/*
 * 3. (20 marks) Exercise 1.5. Using a USet, implement a Bag. A Bag is like a USet—it
 * supports the add(x), remove(x), and find(x) methods—but it allows duplicate elements to
 * be stored. The find(x) operation in a Bag returns some element (if any) that is equal
 * to x. In addition, a Bag supports the findAll(x) operation that returns a list of all
 * elements in the Bag that are equal to x.
 */

/**
 * Class: USet
 * 
 * Purpose: Use a USet to implement a Bag which supports add(x), remove(x) and find(x),
 * but it allows duplicate elements. findAll(x) returns a list of all elements in the Bag
 * equal to x.
 * 
 * Source: ODS by Pat Morin
 * 
 * @author Eric Dunbar
 * @date 29/5/2016
 */
public class USetImplemented<T> extends AbstractSet<T> {


	@Override
	public Iterator<T> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

}
