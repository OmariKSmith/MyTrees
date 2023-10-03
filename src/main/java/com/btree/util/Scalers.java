package com.btree.util;

import javafx.animation.ScaleTransition;
import javafx.scene.Node;
import javafx.util.Duration;

import java.util.ArrayList;

/*	BASIC OPERATION
 * 1. Set zone to trigger scaling  via setZoneNode
 * 2. Add node to scale group via addToScaleGroup
 * 
 * */
public class Scalers {
	
	double xScaleFactor = 1;
	double yScaleFactor = 1;
	double scaleSpeed = 500;

	Node zoneNode;
	
	private ArrayList<Node> scaleGroup = new ArrayList<>();
	private ArrayList<ScaleTransition> growGroup = new ArrayList<>();
	private ArrayList<ScaleTransition> shrinkGroup = new ArrayList<>();

	public Scalers() {
		
	}
	
	public Scalers(double xScaleFactor, double yScaleFactor, double scaleSpeed) {
		super();
		this.xScaleFactor = xScaleFactor;
		this.yScaleFactor = yScaleFactor;
		this.scaleSpeed = scaleSpeed;
	}
	
	public void addToScaleGroup(Node... node) {
		for (Node n : node) {
			scaleGroup.add(n);
			growGroup.add(new ScaleTransition(Duration.millis(scaleSpeed),n));
			shrinkGroup.add(new ScaleTransition(Duration.millis(scaleSpeed),n));

			//get the last node added 
			int lastIn = scaleGroup.size() - 1;

			//applies transition to last node added
			ScaleTransition grow = growGroup.get(lastIn);
			ScaleTransition shrink = shrinkGroup.get(lastIn);

			//set minimum and max scale values for group growth
			setGrowth(grow);

			//set minimum and max scale values for group shrink
			setShrinkage(shrink);
		}
	}

	public void setShrinkage(ScaleTransition shrink) {
		shrink.setFromX(xScaleFactor);
		shrink.setToX(xScaleFactor-xScaleFactor);
		shrink.setFromY(yScaleFactor);
		shrink.setToY(yScaleFactor-yScaleFactor);
		shrink.setCycleCount(1);
	}

	public void setGrowth(ScaleTransition grow) {
		grow.setFromX(xScaleFactor-xScaleFactor);
		grow.setToX(xScaleFactor);
		grow.setToY(yScaleFactor-yScaleFactor);
		grow.setToY(yScaleFactor);
		grow.setCycleCount(1);
	}

	public void addToScaleGroup(Node node) {
		scaleGroup.add(node);
		growGroup.add(new ScaleTransition(Duration.millis(scaleSpeed),node));
		shrinkGroup.add(new ScaleTransition(Duration.millis(scaleSpeed),node));
		int lastIn = scaleGroup.size() - 1;
		ScaleTransition grow = growGroup.get(lastIn);
		ScaleTransition shrink = shrinkGroup.get(lastIn);

		grow.setFromX(xScaleFactor-xScaleFactor);
		grow.setToX(xScaleFactor);
		grow.setToY(yScaleFactor-yScaleFactor);
		grow.setToY(yScaleFactor);

		setShrinkage(shrink);

	}

	public  void  grow() {

		for (ScaleTransition scaleTransition : growGroup) {
			scaleTransition.play();	
		}
	}

	public  void shrink() {

		for (ScaleTransition scaleTransition : shrinkGroup) {
			scaleTransition.play();
		}		
	}
	
///////////////////////////////////GETTER AND SETTERS/////////////////////////////////////////////////
	public Node getZoneNode() {
		return zoneNode;
	}
	
	public void setZoneNode(Node zoneNode) {
		this.zoneNode = zoneNode;
	}

	public double getxScaleFactor() {
		return xScaleFactor;
	}

	public void setxScaleFactor(double xScaleFactor) {
		this.xScaleFactor = xScaleFactor;
	}

	public double getyScaleFactor() {
		return yScaleFactor;
	}

	public void setyScaleFactor(double yScaleFactor) {
		this.yScaleFactor = yScaleFactor;
	}

	public double getScaleSpeed() {
		return scaleSpeed;
	}

	public void setScaleSpeed(double scaleSpeed) {
		this.scaleSpeed = scaleSpeed;
	}

	public ArrayList<Node> getScaleGroup() {
		return scaleGroup;
	}

	public void setScaleGroup(ArrayList<Node> scaleGroup) {
		this.scaleGroup = scaleGroup;
	}

	public ArrayList<ScaleTransition> getGrowGroup() {
		return growGroup;
	}

	public void setGrowGroup(ArrayList<ScaleTransition> growGroup) {
		this.growGroup = growGroup;
	}

	public ArrayList<ScaleTransition> getShrinkGroup() {
		return shrinkGroup;
	}

	public void setShrinkGroup(ArrayList<ScaleTransition> shrinkGroup) {
		this.shrinkGroup = shrinkGroup;
	}
	
	
	

}
