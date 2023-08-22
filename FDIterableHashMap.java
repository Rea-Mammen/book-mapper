import java.util.HashMap;
import java.util.Iterator;

public class FDIterableHashMap<K, V> extends HashMap<K, V> implements Iterable<V>{

	@Override
	public Iterator<V> iterator() {
		return this.values().iterator();
	}

}
