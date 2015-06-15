package edu.cs4730.recyclerviewdemo3;

import android.view.View;

/*
 * from http://www.vogella.de/articles/AndroidListView/article.html
 */

public class Model {

	private String name;
	private boolean selected;
	public View view;

	public Model(String name) {
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
