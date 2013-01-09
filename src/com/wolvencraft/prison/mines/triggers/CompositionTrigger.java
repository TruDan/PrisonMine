package com.wolvencraft.prison.mines.triggers;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.configuration.serialization.ConfigurationSerializable;
import org.bukkit.configuration.serialization.SerializableAs;

import com.wolvencraft.prison.PrisonSuite;
import com.wolvencraft.prison.mines.CommandHandler;
import com.wolvencraft.prison.mines.mine.Mine;
import com.wolvencraft.prison.mines.util.Message;
import com.wolvencraft.prison.mines.util.ResetTrigger;
@SerializableAs("CompositionTrigger")
public class CompositionTrigger implements BaseTrigger, ConfigurationSerializable {
	
	private double percent;
	private String mine;
	private boolean canceled;
	
	private int counter;
	
	public CompositionTrigger(Mine mineObj, double percent) {
		this.percent = percent;
		mine = mineObj.getId();
		canceled = false;
		
		counter = 0;
		
		PrisonSuite.addTask(this);
	}
	
	public CompositionTrigger(Map<String, Object> map) {
		percent = Double.parseDouble((String)map.get("percent"));
		mine = (String) map.get("mine");
		canceled = false;
		
		counter = 0;
		
		PrisonSuite.addTask(this);
	}
	
	public Map<String, Object> serialize() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("percent", Double.toString(percent));
		map.put("mine", mine);
		return map;
	}
	
	public void run() {
		if(counter != 60) { counter++; return; }
		counter = 0;
		
		Mine mineObj = Mine.get(mine);
		if(mineObj == null) return;
		if(percent == 0) return;
		
		if(((double) mineObj.getBlocksLeft() / (double) mineObj.getTotalBlocks()) < percent) {
			Message.debug("Resetting mine " + mineObj.getId() + " based on composition");
			Message.debug("(" + mineObj.getBlocksLeft() + " / " + mineObj.getTotalBlocks() + ") < " + percent);
			CommandHandler.RESET.run(mineObj.getId());
		}
	}

	public void cancel() { canceled = true; }
	
	public boolean getExpired() { return canceled; }
	public String getName() { return "PrisonMine:CompositionTrigger:" + mine; }
	public ResetTrigger getId() { return ResetTrigger.COMPOSITION; }
	
	public double getPercent() { return percent; }
	public void setPercent(double percent) { this.percent = percent; }
}
