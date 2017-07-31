public interface ItemDAO {
    public Item getItemByID(int itemID);
    public List<Item> getItemList();

    public int insertItem(Item item);
    public boolean updateItem(Item item);
    public boolean deleteItem (int itemID)
}