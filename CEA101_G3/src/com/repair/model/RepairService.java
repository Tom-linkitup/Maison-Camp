package com.repair.model;

import java.util.List;

import com.roomphoto.model.RoomPhotoVO;

public class RepairService {

	private RepairDAO_interface dao;

	public RepairService() {
		dao = new RepairDAO();
	}

	public RepairVO addRepair(String room_id,String emp_id,String repair_info,Integer status,byte[] repair_photo) {

		RepairVO repairVO = new RepairVO();

		repairVO.setRoom_id(room_id);
		repairVO.setEmp_id(emp_id);
		repairVO.setRepair_info(repair_info);
		repairVO.setStatus(status);
		repairVO.setRepair_photo(repair_photo);

		dao.insert(repairVO);

		return repairVO;
	}

	public RepairVO updateRepair(String repair_id, String room_id,String emp_id,String repair_info,Integer status,byte[] repair_photo) {

		RepairVO repairVO = new RepairVO();

		repairVO.setRepair_id(repair_id);
		repairVO.setRoom_id(room_id);
		repairVO.setEmp_id(emp_id);
		repairVO.setRepair_info(repair_info);
		repairVO.setStatus(status);
		repairVO.setRepair_photo(repair_photo);

		dao.update(repairVO);

		return repairVO;
	}

	public void deleteRepair(String repair_id) {
		dao.delete(repair_id);
	}

	public RepairVO getOneRepairPhoto(String repair_id) {
		return dao.findByPrimaryKey(repair_id);
	}
	

	public List<RepairVO> getAll() {
		return dao.getAll();
	}
	
	public List<RepairVO> getStatus1() {
		return dao.getStatus1();
	}
	public List<RepairVO> getStatus0() {
		return dao.getStatus0();
	}

}
