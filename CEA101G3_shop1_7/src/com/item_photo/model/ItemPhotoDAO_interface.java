package com.item_photo.model;

import java.util.List;

public interface ItemPhotoDAO_interface {
    public void insert(ItemPhotoVO ItemPhotoVO);
    public void update(ItemPhotoVO ItemPhotoVO);
    public void delete(String itemPhotoId);
    public ItemPhotoVO findByPrimaryKey(String itemPhotoId);
    public List<ItemPhotoVO> getAll();
  //萬用複合查詢(傳入參數型態Map)(回傳 List)
//  public List<ItemPhotoVO> getAll(Map<String, String[]> map); 

}
