public class CategoryDAOImpl extends MySQL implements CategoryDAO {
    @Override
    public Category getCategoryById(int categoryID){
        Connect();
    }
}