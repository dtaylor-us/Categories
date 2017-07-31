public interface CategoryItemDAO {
    public CategoryItem getCategoryItemByID(int categoryItemID);
    public List<CategoryItem> getCategoryItemList();

    public int insertCategoryItem(CategoryItem categoryItem);
    public boolean updateCategoryItem(CategoryItem categoryItem);
    public boolean deleteCategoryItem (int categoryItemID)
}