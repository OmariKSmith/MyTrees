package com.btree.util;

import javafx.animation.FadeTransition;
import javafx.animation.Timeline;
import javafx.scene.Node;
import javafx.util.Duration;

public class Fades {

	static FadeTransition masterFade = new FadeTransition();
	
	   Node mediaNode;

	public Fades(Node mediaNode) {
		this.mediaNode = mediaNode;
		mediaNode.setOpacity(0);
	}
	
	
	public  void inAndOut() {
		mediaNode.setOnMouseEntered(e->{
			in();
		});
		
		mediaNode.setOnMouseExited(e->{
			out();
		});
	}
	public static FadeTransition getMasterFade() {
		return masterFade;
	}

	public static void setMasterFade(FadeTransition masterFade) {
		Fades.masterFade = masterFade;
	}

	public  Node getMediaNode() {
		return mediaNode;
	}

	public static void setMediaNode(Node mediaNode) {
		mediaNode = mediaNode;
	}

	public  FadeTransition in() {
		masterFade = new FadeTransition(Duration.millis(500),mediaNode);
		masterFade.setFromValue(0.1);
		masterFade.setToValue(1.0);
		masterFade.setAutoReverse(true);
		masterFade.play();
		return masterFade;
		
	}

	public  FadeTransition out() {
		masterFade = new FadeTransition(Duration.millis(500),mediaNode);
		masterFade.setFromValue(1.0);
		masterFade.setToValue(0);
		masterFade.setAutoReverse(true);
		masterFade.play();
		return masterFade;
		
	}
	
	public FadeTransition blink() {
		masterFade = new FadeTransition(Duration.millis(500),mediaNode);
		masterFade.setFromValue(1.0);
		masterFade.setToValue(0);
		masterFade.setCycleCount(Timeline.INDEFINITE);
		masterFade.setAutoReverse(true);
		masterFade.play();
		return masterFade;
	}
	
	public void stop() {
		masterFade.stop();
		out();
	}

}
