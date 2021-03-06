package pantry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Inventory {
	private static Inventory inventory = null;
	private final HashMap<String, ArrayList<Item>> stock = new HashMap<>();

	protected Inventory() {
	}

	public static Inventory getInstance()
	{
		if (inventory == null)
			inventory = new Inventory();

		return inventory;
	}

	public Boolean itemExists(Item item) 
	{
		return stock.get(item.getCode()) != null;
	}

	public void removeFromInventory(Item item) 
	{
		// stock.remove(item.getCode());
	}


	public void addToInventory(Item item) 
	{
		if (stock.containsKey(item.getCode())) 
		{
			ArrayList<Item> its = stock.get(item.getCode());
			its.add(item);
			ArrayList<Item> replace = stock.replace(item.getCode(), its);
		} else 
		{
			ArrayList<Item> its = new ArrayList<>();
			its.add(item);
			stock.put(item.getCode(), its);
		}
	}


	public void displayInventory()
	{
		System.out.println("Pantry Inventory");
		System.out.println();
		for(Map.Entry<String, ArrayList<Item>> entry:stock.entrySet()) {
			System.out.println("Item Key " + entry.getKey() + ":");
			for(Item itm : entry.getValue()) {
				itm.displayItem();
			}
		}
		System.out.println();
	}
	
	
	// reduces quantity when student puts item in the cart
	public void reduceQuantity(String code, double quantity) {
		for (Map.Entry<String, ArrayList<Item>> item : stock.entrySet()) {
			if (code.equals(item.getKey())) {
				for (Item itm : item.getValue()) {
					itm.setQty(itm.getQty() - quantity);
				}
			}
		}
	}
	
	public HashMap<String, ArrayList<Item>> getAvailableItems() {
		return stock;
	}
}
