package edu.cs4730.mvcdemo;

import android.graphics.Color;


/*
 * A simple data class of colors for this example.
 * Normally this would be more complex with adders/setters as well and get by color name
 * but that is too complex for this example.
 * 
 * This would be the model of the MVC 
 * 
 */
public class ColorList {

	int num;
	ColorData[] colors;
	
	ColorList() {
	  num = 0; //set to first color in list
	  colors = new ColorData[] {
		new ColorData("Red", Color.RED),
		new ColorData("Blue", Color.BLUE),
		new ColorData("Cyan", Color.CYAN),
		new ColorData("Orange", Color.rgb(255, 165, 0)),
		//Android's green is lime. 
		//new ColorData("Green", Color.GREEN),
		new ColorData("Green", Color.rgb(0, 128, 0)),
		new ColorData("Magenta", Color.MAGENTA),
		new ColorData("Pink", Color.rgb(255, 192, 203)),
		new ColorData("Yellow", Color.YELLOW),
		new ColorData("Gray", Color.GRAY),
		new ColorData("Light Gray", Color.LTGRAY),
		new ColorData("Dark Gray", Color.DKGRAY),
		new ColorData("Lime", Color.rgb(0, 255, 0) ),
		new ColorData("Olive", Color.rgb(128, 128, 0)),
		new ColorData("Purple", Color.rgb(128, 0, 128)),
		new ColorData("Teal", Color.rgb(0, 128, 128)),
		new ColorData("Navy", Color.rgb(0, 0, 128)),
		new ColorData("Golden Rod", Color.rgb(218, 165, 32)),
		new ColorData("Dark Olive Green", Color.rgb(85, 107, 47)),
		new ColorData("Khaki", Color.rgb(240, 230, 140)),
		new ColorData("Steel Blue", Color.rgb(70, 130, 180)),
		new ColorData("Dark Orchid", Color.rgb(153, 50, 204)),
		new ColorData("White", Color.WHITE),
		new ColorData()  //black.
	  };
	  
	}
	
	/*
	 * Returns the color based on the index parameter.  
	 * returns black as default.
	 */
	int getColorbyIndex(int i) {
	  if (i >=0 && i < colors.length) {
		  return colors[i].ColorNum;
	  } else {  //return black.
		  return Color.BLACK;
	  }
	}
	
	/*
	 * sets the num to next, if there is a next.
	 */
	void next() {
		num++;
		if (num >= colors.length) {
			num = colors.length-1;
		}
	}
	/*
	 * sets the num to previous, assuming there is a previous
	 */
	void prev() {
		num --;
		if (num <0) {
			num = 0;
		}
	}
	
	/*
	 * returns the current color name.
	 */
	String getName() {
		return colors[num].Name;
	}
	/*
	 * return the current color number
	 */
	int getNum() {
		return colors[num].ColorNum;
	}
	
	Boolean isFirst() {
		return num == 0;
	}

	Boolean isLast() {
		return num == colors.length-1;
	}

	/*
	 * simple class to hold the color name and associated color number.
	 */
	private class ColorData {
	  String Name;
	  int ColorNum;
	  
		ColorData() {
			Name = "Black";
			ColorNum = Color.BLACK;
		}
		ColorData(String pName, int pColorNum) {
			Name = pName;
			ColorNum= pColorNum;
		}
	}
}
