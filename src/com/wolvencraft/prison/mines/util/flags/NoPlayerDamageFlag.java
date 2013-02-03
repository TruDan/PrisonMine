package com.wolvencraft.prison.mines.util.flags;

public class NoPlayerDamageFlag implements BaseFlag {
	
	private String option ="";
	
	@Override
	public String getName() { return "noplayerdamage"; }

	@Override
	public String getOption() { return option; }

	@Override
	public void setOption(String option) { this.option = option; }

}
