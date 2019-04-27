import java.util.ArrayList;
import java.util.List;
public class RoundRobinImpl<Produto> implements RoundRobin<Produto> {

	public final List<Produto> myResources;
	public int myCount = 0;

	public RoundRobinImpl() {
		myResources = new ArrayList<>();
	}

	public RoundRobinImpl(List<Produto> initialNodes) {
		myResources = new ArrayList<>(initialNodes);
	}

	@Override
	public synchronized Produto select() throws RoundRobinException {
		if (myResources.isEmpty()) {
			throw new RoundRobinException("There are no resource in the container");
		}

		int divisor = myResources.size();
		int index = ((myCount++ % divisor + divisor) % divisor);

		return myResources.get(index);
	}

	@Override
	public synchronized void add(Produto ref) {
		if (!myResources.contains(ref)) {
			myResources.add(ref);
		} else {
			System.out.println("The resource already exist!!!");
		}
	}

	@Override
	public synchronized void remove(Produto ref) {
		if (!myResources.remove(ref)) {
			System.out.println("There are no resource to remove!!!");
		}
	}

	@Override
	public synchronized void update(Produto ref) {
		int index = myResources.indexOf(ref);
		if (index != -1) {
			myResources.set(index, ref);
		} else {
			System.out.println("There are no resource to update!!!");
		}
	}

	public boolean isEmpty() {
		return myResources.isEmpty();
	}

	public boolean contains(Produto ref) {
		return myResources.contains(ref);
	}
}
