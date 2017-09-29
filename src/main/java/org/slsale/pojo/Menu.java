package org.slsale.pojo;

import java.util.List;

/**
 * 存放主菜单与子菜单
 */
public class Menu {
	private Function mainMenu;//主菜单
	private List<Function> subMenus;//子菜单

	public Menu(Function mainMenu, List<Function> subMenus) {
		this.mainMenu = mainMenu;
		this.subMenus = subMenus;
	}

	public Menu() {
	}

	public Function getMainMenu() {
		return mainMenu;
	}
	public void setMainMenu(Function mainMenu) {
		this.mainMenu = mainMenu;
	}
	public List<Function> getSubMenus() {
		return subMenus;
	}
	public void setSubMenus(List<Function> subMenus) {
		this.subMenus = subMenus;
	}

	@Override
	public String toString() {
		return "Menu{" +
				"mainMenu=" + mainMenu +
				", subMenus=" + subMenus +
				'}';
	}
}
