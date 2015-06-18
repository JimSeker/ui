package edu.cs4730.ListDemo;

import android.view.View;

/*
 * from http://www.vogella.de/articles/AndroidListView/article.html
 */

public class InterActive_DataModel {

	private String name;
	private boolean selected;
	public View view;

	public InterActive_DataModel(String name) {
		this.name = name;
		selected = false;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isSelected() {
		return selected;
	}

	public void setSelected(boolean selected) {
		this.selected = selected;
	}

}
