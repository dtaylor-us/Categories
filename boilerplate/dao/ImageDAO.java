public interface ImageDAO {
    public Image getImageByID(int imageID);
    public List<Image> getImageList();

    public int insertImage(Image image);
    public boolean updateImage(Image image);
    public boolean deleteImage (int imageID)
}