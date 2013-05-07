

package com.laytonsmith.abstraction.bukkit;

import com.laytonsmith.abstraction.AbstractionObject;
import com.laytonsmith.abstraction.MCMaterialData;
import com.laytonsmith.annotations.WrappedItem;
import org.bukkit.material.MaterialData;

/**
 *
 * @author layton
 */
public class BukkitMCMaterialData implements MCMaterialData{
    @WrappedItem MaterialData md;
    
    public Object getHandle(){
        return md;
    }

    public int getData() {
        return md.getData();
    }
    
	@Override
	public String toString() {
		return md.toString();
	}

	@Override
	public boolean equals(Object obj) {
		return (obj instanceof BukkitMCMaterialData?md.equals(((BukkitMCMaterialData)obj).md):false);
	}

	@Override
	public int hashCode() {
		return md.hashCode();
	}
    
}
