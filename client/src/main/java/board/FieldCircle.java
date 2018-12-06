package board;

import game.board.field.Field;
import game.board.field.FieldColor;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class FieldCircle extends Circle {
	private Field field;
	
	public FieldCircle(Field field, double radius) {
		this.field = field;
		
		this.setColor();
		this.setRadius(radius);
		this.setCenterX(this.calculateX());
		this.setCenterY(this.calculateY());
	}
	
	private double calculateX() {
		double r = getRadius();
		double d = 10;
		return ((2*r +d)/2)*this.field.getRow() + 
				((2*r+d)*(Math.sqrt(3)) / 2)* this.field.getDiagonal();
	}
	
	private double calculateY() {
		double r = getRadius();
		double d = 5;
		return r+(2*r+d)*this.field.getRow();
	}
	
	private void setColor() {
		FieldColor color = this.field.getColor();
		
		if(color == FieldColor.BLUE)
			this.setFill(Color.LIGHTBLUE);
		else if(color == FieldColor.GREEN)
			this.setFill(Color.LIGHTGREEN);
		else if(color == FieldColor.ORANGE)
			this.setFill(Color.rgb(253, 221, 148));
		else if(color == FieldColor.PURPLE)
			this.setFill(Color.PLUM);
		else if(color == FieldColor.RED)
			this.setFill(Color.rgb(255, 76, 73));
		else if(color == FieldColor.YELLOW)
			this.setFill(Color.rgb(255, 253, 124));
		else if(color == FieldColor.NONE)
			this.setFill(Color.LIGHTGRAY);
	}
}
