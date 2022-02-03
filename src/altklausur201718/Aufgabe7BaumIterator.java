package altklausur201718;

import java.util.NoSuchElementException;

public class Aufgabe7BaumIterator {
	public class TreeIterator implements java.util.Iterator<Integer>{
		
		private Aufgabe7BaumIterator current;// current speichert immer das nächste (noch
		// nicht durch next() zurückgegebene) Element
		
		public TreeIterator(Aufgabe7BaumIterator bst) {
			Aufgabe7BaumIterator leftmost = bst;
			while (leftmost.left != null) {
				leftmost = leftmost.left;
			}
			current = leftmost;
		}
		
		@Override
		public boolean hasNext() {
			if(current != null) {
				return true;
			}
			return false;
		}

		@Override
		public Integer next() {
			if(!hasNext()) {
				throw new NoSuchElementException();
			}
			Aufgabe7BaumIterator oldCurrent = current;
			if(current.right != null) {
				current = current.right;// rechter Nachfolger existiert
				while(current.left != null) {// fahre mit dessen linkestem Nachfolger fort:
					current = current.left;// hat linken Nachfolger
				}
			}else { // rechter Nachfolger existiert nicht

				Aufgabe7BaumIterator nextNode = current.parent;// Annahme: Elternknoten ist nächster
				// (Hinweis: nextNode speichert fortan immer den Elternknoten von current)
				 // Solange current aber der rechte Nachfolger ist, wurde nextNode bereits
				 // behandelt (weil kleiner) und wir gehen mit current und nextNode um eine
				 // Ebene nach oben. Dieses Vorgehen wiederholen wir, bis wir einen linken Pfad
				 // finden (d. h. current ist kein rechter Nachfolger) oder alle Elemente
				 // behandelt wurden, wir also bei der Wurzel landen (nextNode == null).
				while (nextNode != null && current == nextNode.right) {
					current = current.parent;
					nextNode = nextNode.parent;
				}
				current = nextNode;// übernimm nextNode als nächsten (current)
			}
			return oldCurrent.content;
		}
		
	}
	
	public TreeIterator getIterator() {
		return new TreeIterator(this);
	}
	
	private Integer content;
	private Aufgabe7BaumIterator right;
	private Aufgabe7BaumIterator left;
	private Aufgabe7BaumIterator parent;
	
	public Aufgabe7BaumIterator(Integer content, Aufgabe7BaumIterator left, Aufgabe7BaumIterator right, Aufgabe7BaumIterator parent) {
		this.content = content;
		this.left = left;
		this.right = right;
		this.parent = parent;
	}
	public void insert(Integer x) {
		
	}
}
