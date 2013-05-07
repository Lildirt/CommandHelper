

package com.laytonsmith.abstraction.bukkit;

import com.laytonsmith.abstraction.AbstractionObject;
import com.laytonsmith.abstraction.AbstractionUtils;
import com.laytonsmith.abstraction.MCPlugin;
import com.laytonsmith.abstraction.MCPluginManager;
import com.laytonsmith.annotations.WrappedItem;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

/**
 *
 * @author layton
 */
public class BukkitMCPluginManager implements MCPluginManager {

    @WrappedItem PluginManager p;
    
    public PluginManager getHandle(){
        return p;
    }

    public MCPlugin getPlugin(String name) {
        if(p.getPlugin(name) == null){
            return null;
        }
        return AbstractionUtils.wrap(p.getPlugin(name));
    }
    
    public PluginManager __PluginManager(){
        return p;
    }

	@Override
	public String toString() {
		return p.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof BukkitMCPluginManager?p.equals(((BukkitMCPluginManager)obj).p):false);
	}

	@Override
	public int hashCode() {
		return p.hashCode();
	}

	public List<MCPlugin> getPlugins() {
		List<MCPlugin> retn = new ArrayList<MCPlugin>();
		Plugin[] plugs = p.getPlugins();
		
		for (Plugin plug : plugs) {
			retn.add((MCPlugin) AbstractionUtils.wrap(plug));
		}
		
		return retn;
	}
    
}
