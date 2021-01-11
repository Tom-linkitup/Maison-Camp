package com.repair.model;

public class RepairVO implements java.io.Serializable{
		private String repair_id;
		private String room_id;
		private String emp_id;
		private String repair_info;
		private Integer status;
		private byte[] repair_photo;
		
		
		public Integer getStatus() {
			return status;
		}
		public void setStatus(Integer status) {
			this.status = status;
		}
		public String getRepair_id() {
			return repair_id;
		}
		public void setRepair_id(String repair_id) {
			this.repair_id = repair_id;
		}
		public String getRoom_id() {
			return room_id;
		}
		public void setRoom_id(String room_id) {
			this.room_id = room_id;
		}
		public String getEmp_id() {
			return emp_id;
		}
		public void setEmp_id(String emp_id) {
			this.emp_id = emp_id;
		}
		public String getRepair_info() {
			return repair_info;
		}
		public void setRepair_info(String repair_info) {
			this.repair_info = repair_info;
		}
		public byte[] getRepair_photo() {
			return repair_photo;
		}
		public void setRepair_photo(byte[] repair_photo) {
			this.repair_photo = repair_photo;
		}
}

		