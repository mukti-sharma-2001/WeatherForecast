package com.example.WeatherForeCast.entities;
public class Wind {
	private Double speed;
	private Integer deg;
	
	private Double gust;
	public Wind() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Wind(Double speed, Integer deg, Double gust) {
		super();
		this.speed = speed;
		this.deg=deg;
		this.gust=gust;
	}
	public Double getSpeed() {
		return speed;
	}
	public void setSpeed(Double speed) {
		this.speed = speed;
	}
	public Integer getDeg() {
		return deg;
	}
	public void setDeg(Integer deg) {
		this.deg = deg;
	}
	public Double getGust() {
		return gust;
	}
	public void setGust(Double gust) {
		this.gust = gust;
	}
	@Override
	public String toString() {
		return "Wind [speed=" + speed + ", deg=" + deg + ", gust=" + gust + "]";
	}
	
	
}
