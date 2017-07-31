public interface UserCategoryDAO {
    public UserCategory getUserCategoryByID(int userCategoryID);
    public List<UserCategory> getUserCategoryList();

    public int insertUserCategory(UserCategory userCategory);
    public boolean updateUserCategory(UserCategory userCategory);
    public boolean deleteUserCategory (int userCategoryID)
}