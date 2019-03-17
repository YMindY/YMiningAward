package yxmingy.miningaward;
import java.util.ArrayList;
import java.util.Map;

import cn.nukkit.block.Block;
import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class Main extends PluginBase implements Listener{
	private Config conf;
	public void onEnable() {
		conf = new Config(getDataFolder()+"/Config.yml",Config.YAML);
		/*
		if(conf.getAll().isEmpty()) {
			conf.set(key, value);
			conf.save();
		}
		*/
		getServer().getPluginManager().registerEvents(this,this);
		getLogger().notice("YMiningAward in Enabled! Author: xMing.");
	}
	@EventHandler
	@SuppressWarnings("unchecked")
	public void onBlockBreak(BlockBreakEvent event)
	{
		int ran = (int)(Math.random()*10000),rank = 0,rate;
		Block block = event.getBlock();
		String idString = String.valueOf(block.getId()),
				   metaString = String.valueOf(block.getDamage()),
				   titleString = idString+","+metaString;
	  //event.getPlayer().sendMessage(titleString+String.valueOf(ran));
		//Map<String, Object> confMap = conf.getAll();
		if(conf.exists(titleString)) {
			ArrayList<Map<String, Object>> miningArrayList = (ArrayList<Map<String, Object>>)conf.get(titleString);
			for (Map<String, Object> map : miningArrayList) {
				rate = Integer.parseInt(String.valueOf(map.get("概率")));
				rank += rate;
				if(ran < rank) {
					ArrayList<String> cmdArrayList = (ArrayList<String>)map.get("运行的命令");
					for(String cmdString : cmdArrayList) {
						cmdString = cmdString.replaceAll("@p", event.getPlayer().getName());
						getServer().dispatchCommand(event.getPlayer(), cmdString);
					}
					event.getPlayer().sendMessage(String.valueOf(map.get("奖励提示")));
					break;
				}
			}
		}
	}
}
