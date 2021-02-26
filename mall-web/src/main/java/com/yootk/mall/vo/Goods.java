package com.yootk.mall.vo;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Goods implements Serializable {
	private Long gid ;
	private String name ;
	private Double price ;
	private String photo ;
	public Long getGid() {
		return gid;
	}
	public void setGid(Long gid) {
		this.gid = gid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public String getPhoto() {
		return photo;
	}
	public void setPhoto(String photo) {
		this.photo = photo;
	}

	@Override
	public String toString() {
		return "Goods{" +
				"gid=" + gid +
				", name='" + name + '\'' +
				", price=" + price +
				", photo='" + photo + '\'' +
				'}';
	}
}
