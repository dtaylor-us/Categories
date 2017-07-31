public interface CategoryDAO {
    public Category getCategoryByID(int categoryID);
    public List<Category> getCategoryList();

    public int insertCategory(Category category);
    public boolean updateCategory(Category category);
    public boolean deleteCategory (int categoryID)
}