/**
 * Description: An SLList (singly-linked list) is a sequence of Nodes. Each node
 * u stores a data value u.x and a reference u.next to the next node in the
 * sequence. For the last node w in the sequence, w.next = null
 * 
 * @Date 30/4/2016
 * @author Eric Dunbar
 * @param <E>
 *
 */

// http://grepcode.com/file/repository.grepcode.com/java/root/jdk/openjdk/8u40-b25/java/util/LinkedList.java#LinkedList.Node

// http://stackoverflow.com/questions/20255911/what-does-the-e-in-java-mean

// http://stackoverflow.com/questions/5526955/in-java-whats-the-difference-between-e-t

// http://www.coderanch.com/t/617025/java/java/compareTo-generic-objects

public class Node<T> {
	Comparable<T> elementData;
	Node<T> next;
}
