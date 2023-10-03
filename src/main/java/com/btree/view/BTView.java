package com.btree.view;

import com.btree.model.BinaryTree;
import com.btree.util.Fades;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.util.Duration;

public class BTView extends Pane {
   private BinaryTree<Integer> tree = new BinaryTree<>();
   private double radius = 15;
   private double vGap = 50;

   private double duration = 500d;
    Timeline timeline;
   public BTView(BinaryTree<Integer> tree){
       this.tree = tree;
       setStatus("Tree is empty");

   }

    public void setStatus(String msg) {
       getChildren().add(new Text(20,20,msg));
    }

    public void displayTree(){
       this.getChildren().clear();
       if(tree.getRoot() != null){
           timeline = new Timeline(new KeyFrame(Duration.millis(duration),displayTree ->
               displayTree(tree.getRoot(), getWidth() / 2, vGap, getWidth() / 4)));
           timeline.play();
       }
    }

    public void displayTree(BinaryTree.TreeNode<Integer> root, double x, double y, double hGap){
       if(root.left != null){


           timeline = new Timeline(new KeyFrame(Duration.millis(2000),shrinkRight -> {
               displayTree(root.left, x - hGap, y + vGap, hGap / 2);
           }));
           timeline.play();

           timeline = new Timeline(new KeyFrame(Duration.millis(1000),shrinkRight -> {
               getChildren().add(new Line(x - hGap, y + vGap, x,y));

           }));
           timeline.play();
       }

       if(root.right != null) {


           timeline = new Timeline(new KeyFrame(Duration.millis(2000),shrinkRight -> {
               displayTree(root.right, x + hGap, y + vGap, hGap /2);
           }));

           timeline.play();

           timeline = new Timeline(new KeyFrame(Duration.millis(1000),shrinkRight -> {
               getChildren().add(new Line(x + hGap, y + vGap, x,y));

           }));
           timeline.play();

        }

       if (root.element >999){
           radius = 30;
       }
        Circle circle = new Circle(x,y, radius);

        Text text = new Text(x - 10, y + 4, root.element + "") ;
        text.setStroke(Color.WHITE);
        circle.setFill(Color.BLACK);
        circle.setStroke(Color.BLACK);
        new Fades(circle).in();
        new Fades(text).in();

            getChildren().addAll(circle,text);




    }
}
