package com.actPhoto.model;

import java.util.*;


public interface ActPhotoDAO_interface {
	public void insert(ActPhotoVO actPhotoVO);
	public void update(ActPhotoVO actPhotoVO);
	public void deleteByActPhotoId(String actPhotoId);
	public ActPhotoVO findByActPhotoId(String actPhotoId);
	public List<ActPhotoVO> getByActId(String actId);
	public List<ActPhotoVO> getAll();

}
