package yxmingy.miningaward;
import java.util.ArrayList;

import cn.nukkit.event.EventHandler;
import cn.nukkit.event.Listener;
import cn.nukkit.event.block.BlockBreakEvent;
import cn.nukkit.plugin.PluginBase;
import cn.nukkit.utils.Config;

public class Main extends PluginBase implements Listener{
	private Config conf;
	public void onEnable() {
		conf = new Config(getDataFolder()+"/Config.yml",Config.YAML);
		if(conf.getAll().isEmpty()) {
			conf.set(key, value);
			conf.save();
		}
		getLogger().notice("YMiningAward in Enabled! Author: xMing.");
	}
	@EventHandler
	public void onBlockBreak(BlockBreakEvent event)
	{
		double ran = Math.random();
		
	}
}
