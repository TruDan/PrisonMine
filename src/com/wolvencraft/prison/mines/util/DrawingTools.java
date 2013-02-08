package com.wolvencraft.prison.mines.util;

public enum DrawingTools {
	CornerTopLeft("\u250F"),
	CornerTopRight("\u2513"),
	CornerBottomLeft("\u2517"),
	CornerBottomRight("\u251B"),
	LineHorizontal("\u2501"),
	LineVertical("\u2503"),
	WhiteSpace("\u0020");
	
	DrawingTools(String character) {
		this.character = character;
	}
	
	String character;
	
	@Override
	public String toString() { return character; }
	
	public static String getAllCharacters() {
		String all = "";
		for(DrawingTools tool : DrawingTools.values()) all += tool.toString();
		return all;
	}
	
	public static boolean isPresent(char ch) {
		for(DrawingTools tool : DrawingTools.values()) {
			if(tool.toString().equals(ch)) return true;
		}
		return false;
	}
}
