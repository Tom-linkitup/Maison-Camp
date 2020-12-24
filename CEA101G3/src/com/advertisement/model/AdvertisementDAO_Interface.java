package com.advertisement.model;

import java.util.List;

public interface AdvertisementDAO_Interface {
	public void addAdvertisement(AdvertisementVO advertisementVO);
	public void updateAdvertisement(AdvertisementVO advertisementVO);
	public void deleteAdvertisement(String adv_id);
	public AdvertisementVO findByAdvertisementId(String adv_id);
	public List<AdvertisementVO> getAllAdvertisement();
}
