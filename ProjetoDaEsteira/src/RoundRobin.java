
public interface RoundRobin<Produto> {

	public Produto select() throws RoundRobinException;

	public void add(Produto ref);

	public void remove(Produto ref);

	public void update(Produto ref);
}
