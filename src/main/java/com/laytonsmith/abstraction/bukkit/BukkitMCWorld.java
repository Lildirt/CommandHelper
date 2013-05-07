

package com.laytonsmith.abstraction.bukkit;

import com.laytonsmith.abstraction.*;
import com.laytonsmith.abstraction.blocks.MCBlock;
import com.laytonsmith.abstraction.blocks.MCFallingBlock;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCBlock;
import com.laytonsmith.abstraction.bukkit.blocks.BukkitMCFallingBlock;
import com.laytonsmith.abstraction.entities.MCOcelot;
import com.laytonsmith.abstraction.entities.MCSheep;
import com.laytonsmith.abstraction.entities.MCWolf;
import com.laytonsmith.abstraction.enums.MCBiomeType;
import com.laytonsmith.abstraction.enums.MCCreeperType;
import com.laytonsmith.abstraction.enums.MCDyeColor;
import com.laytonsmith.abstraction.enums.MCEffect;
import com.laytonsmith.abstraction.enums.MCEntityType;
import com.laytonsmith.abstraction.enums.MCMobs;
import com.laytonsmith.abstraction.enums.MCOcelotType;
import com.laytonsmith.abstraction.enums.MCPigType;
import com.laytonsmith.abstraction.enums.MCProfession;
import com.laytonsmith.abstraction.enums.MCSkeletonType;
import com.laytonsmith.abstraction.enums.MCSound;
import com.laytonsmith.abstraction.enums.MCWolfType;
import com.laytonsmith.abstraction.enums.MCWorldEnvironment;
import com.laytonsmith.abstraction.enums.MCWorldType;
import com.laytonsmith.abstraction.enums.MCZombieType;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCBiomeType;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCDyeColor;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCEntityType;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCOcelotType;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCProfession;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCSkeletonType;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCSound;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCWorldEnvironment;
import com.laytonsmith.abstraction.enums.bukkit.BukkitMCWorldType;
import com.laytonsmith.annotations.WrappedItem;
import com.laytonsmith.core.constructs.*;
import com.laytonsmith.core.exceptions.ConfigRuntimeException;
import com.laytonsmith.core.functions.Exceptions.ExceptionType;
import java.util.ArrayList;
import java.util.List;
import org.bukkit.Effect;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.World;
import org.bukkit.block.Biome;
import org.bukkit.block.Block;
import org.bukkit.block.BlockFace;
import org.bukkit.entity.*;
import org.bukkit.material.MaterialData;

/**
 *
 * @author layton
 */
public class BukkitMCWorld implements MCWorld {

    @WrappedItem World w;
	
	@Override
	public boolean equals(Object o) {
		return o instanceof MCWorld ? this.w.equals(((BukkitMCWorld)o).w) : false;
	}

	@Override
	public int hashCode() {
		return this.w.hashCode();
	}
	
	@Override
	public String toString() {
		return this.w.toString();
	}
    
    public Object getHandle(){
        return w;
    }

	public List<MCEntity> getEntities() {
		if (w.getEntities() == null) {
			return null;
		}
		List<MCEntity> list = new ArrayList<MCEntity>();
		for (Entity e : w.getEntities()) {
			list.add((MCEntity)AbstractionUtils.wrap(e));
		}
		return list;
	}

    public List<MCLivingEntity> getLivingEntities() {
        if (w.getLivingEntities() == null) {
            return null;
        }
        List<MCLivingEntity> list = new ArrayList<MCLivingEntity>();
        for (LivingEntity e : w.getLivingEntities()) {
            list.add((MCLivingEntity)AbstractionUtils.wrap(e));
        }
        return list;
    }

    public String getName() {
        return w.getName();
    }

	public long getSeed() {
		return w.getSeed();
	}

	public MCWorldEnvironment getEnvironment() {
		return BukkitMCWorldEnvironment.getConvertor().getAbstractedEnum(w.getEnvironment());
	}

	public String getGenerator() {
		try {
			return w.getGenerator().toString();
		} catch (NullPointerException npe) {
			return "default";
		}
	}
	
	public MCWorldType getWorldType() {
		return BukkitMCWorldType.getConvertor().getAbstractedEnum(w.getWorldType());
	}

    public MCBlock getBlockAt(int x, int y, int z) {
        if (w.getBlockAt(x, y, z) == null) {
            return null;
        }
        return AbstractionUtils.wrap(w.getBlockAt(x, y, z));
    }

    public MCEntity spawn(MCLocation l, Class mobType) {
        return AbstractionUtils.wrap(w.spawn(((BukkitMCLocation) l).l, mobType));
    }

	public MCEntity spawn(MCLocation l, MCEntityType entType) {
		return AbstractionUtils.wrap(w.spawnEntity(
				((BukkitMCLocation) l).asLocation(),
				BukkitMCEntityType.getConvertor().getConcreteEnum(MCEntityType.valueOf(entType.name()))));
	}

    public void playEffect(MCLocation l, MCEffect mCEffect, int e, int data) {
        w.playEffect(((BukkitMCLocation) l).l, Effect.valueOf(mCEffect.name()), e, data);
    }

	public void playSound(MCLocation l, MCSound sound, int volume, int pitch) {
		w.playSound(((BukkitMCLocation) l).asLocation(), 
				BukkitMCSound.getConvertor().getConcreteEnum(sound), volume, pitch);
	}

    public MCItem dropItemNaturally(MCLocation l, MCItemStack is) {
        return AbstractionUtils.wrap(w.dropItemNaturally(((BukkitMCLocation) l).l, ((BukkitMCItemStack) is).is));
    }

    public MCItem dropItem(MCLocation l, MCItemStack is) {
        return AbstractionUtils.wrap(w.dropItem(((BukkitMCLocation) l).l, ((BukkitMCItemStack) is).is));
    }

	public MCLightningStrike strikeLightning(MCLocation GetLocation) {
		return AbstractionUtils.wrap(
				w.strikeLightning(((BukkitMCLocation) GetLocation).l));
	}

	public MCLightningStrike strikeLightningEffect(MCLocation GetLocation) {
		return AbstractionUtils.wrap(
				w.strikeLightningEffect(((BukkitMCLocation) GetLocation).l));
	}

    public void setStorm(boolean b) {
        w.setStorm(b);
    }

    public MCLocation getSpawnLocation() {
        return AbstractionUtils.wrap(w.getSpawnLocation());
    }

    public void refreshChunk(int x, int z) {
        w.refreshChunk(x, z);
    }

    public void setTime(long time) {
        w.setTime(time);
    }

    public long getTime() {
        return w.getTime();
    }

    public MCBiomeType getBiome(int x, int z) {
		return BukkitMCBiomeType.getConvertor().getAbstractedEnum(w.getBiome(x, z));
    }

    public void setBiome(int x, int z, MCBiomeType type) {
        w.setBiome(x, z, Biome.valueOf(type.name()));
    }

	public MCBlock getHighestBlockAt(int x, int z) {
		//Workaround for getHighestBlockAt, since it doesn't like transparent
		//blocks.
		Block b = w.getBlockAt(x, w.getMaxHeight() - 1, z);
		while(b.getType() == Material.AIR && b.getY() > 0){
			b = b.getRelative(BlockFace.DOWN);
		}
		return AbstractionUtils.wrap(b);
	}

    public void explosion(double x, double y, double z, float size, boolean safe) {
        w.createExplosion(x, y, z, size, !safe, !safe);
    }

    public void setSpawnLocation(int x, int y, int z) {
        w.setSpawnLocation(x, y, z);
    }

    public CArray spawnMob(MCMobs name, String subClass, int qty, MCLocation l, Target t) {
        Class mobType = null;
        CArray ids = new CArray(Target.UNKNOWN);
        try {
            switch (name) {
                case CHICKEN:
                    mobType = Chicken.class;
                    break;
                case COW:
                    mobType = Cow.class;
                    break;
                case CREEPER:
                    mobType = Creeper.class;
                    break;
                case GHAST:
                    mobType = Ghast.class;
                    break;
                case PIG:
                    mobType = Pig.class;
                    break;
                case PIGZOMBIE:
                    mobType = PigZombie.class;
                    break;
                case SHEEP:
                    mobType = Sheep.class;
                    break;
                case SKELETON:
                    mobType = Skeleton.class;
                    break;
                case SLIME:
                    mobType = Slime.class;
                    break;
                case SPIDER:
                    mobType = Spider.class;
                    break;
                case SQUID:
                    mobType = Squid.class;
                    break;
                case WOLF:
                    mobType = Wolf.class;
                    break;
                case ZOMBIE:
                    mobType = Zombie.class;
                    break;
                case CAVESPIDER:
                    mobType = CaveSpider.class;
                    break;
                case ENDERMAN:
                    mobType = Enderman.class;
                    break;
                case SILVERFISH:
                    mobType = Silverfish.class;
                    break;
                case BLAZE:
                    mobType = Blaze.class;
                    break;
                case VILLAGER:
                    mobType = Villager.class;
                    break;
                case ENDERDRAGON:
                    mobType = EnderDragon.class;
                    break;
                case MAGMACUBE:
                    mobType = MagmaCube.class;
                    break;
                case MOOSHROOM:
                    mobType = MushroomCow.class;
                    break;
                case SPIDERJOCKEY:
                    mobType = Spider.class;
                    break;
                case GIANT:
					mobType = Giant.class;
                    break;
                case SNOWGOLEM:
                    mobType = Snowman.class;
                    break;
                case OCELOT:
                    mobType = Ocelot.class;
                    break;
                case IRONGOLEM:
                    mobType = IronGolem.class;
                    break;
				case BAT:
					mobType = Bat.class;
					break;
				case WITHER:
					mobType = Wither.class;
					break;
				case WITHER_SKULL:
					mobType = WitherSkull.class;
					break;
				case WITCH:
					mobType = Witch.class;
					break;
            }
        } catch (IllegalArgumentException e) {
            throw new ConfigRuntimeException("No mob of type " + name + " exists",
                    ExceptionType.FormatException, t);
        }
        for (int i = 0; i < qty; i++) {
            MCEntity e = l.getWorld().spawn(l, mobType);
            String[] subTypes = subClass.toUpperCase().split("-");
            if (name == MCMobs.SPIDERJOCKEY) {
                e.setPassenger(l.getWorld().spawn(l, Skeleton.class));
            }
			if (!subClass.equals("")) { //if subClass is blank, none of this needs to run at all 
				if (e instanceof MCSheep) {
					Sheep s = e.getHandle();
					MCDyeColor color = MCDyeColor.WHITE;
					for (String type : subTypes) {
						try {
							color = MCDyeColor.valueOf(type);
							s.setColor(BukkitMCDyeColor.getConvertor().getConcreteEnum(color));
						} catch (IllegalArgumentException ex) {
							throw new ConfigRuntimeException(type + " is not a valid color",
									ExceptionType.FormatException, t);
						}
					}
				}
				if(e instanceof MCOcelot){
					Ocelot o = e.getHandle();
					MCOcelotType otype = MCOcelotType.WILD_OCELOT;
					for (String type : subTypes) {
						try {
							o.setCatType(BukkitMCOcelotType.getConvertor().getConcreteEnum(otype));
							otype = MCOcelotType.valueOf(type);
						} catch (IllegalArgumentException ex){
							throw new ConfigRuntimeException(type + " is not an ocelot type",
									ExceptionType.FormatException, t);					
						}
					}
				}
				if(e.getHandle() instanceof Creeper){
					Creeper c = e.getHandle();
					for (String type : subTypes) {
						try {
							MCCreeperType ctype = MCCreeperType.valueOf(type);
							switch (ctype) {
							case POWERED:
								c.setPowered(true);
								break;
							default:
								break;
							}
						} catch (IllegalArgumentException ex){
							throw new ConfigRuntimeException(type + " is not a creeper state",
									ExceptionType.FormatException, t);
						}
					}
				}
				if(e instanceof MCWolf){
					Wolf w = e.getHandle();
					for (String type : subTypes) {
						try {
							MCWolfType wtype = MCWolfType.valueOf(type);
							switch (wtype) {
							case ANGRY:
								w.setAngry(true);
								break;
							case TAMED:
								w.setTamed(true);
								break;
							default:
								break;
							}
						} catch (IllegalArgumentException ex){
							throw new ConfigRuntimeException(type + " is not a wolf state",
									ExceptionType.FormatException, t);
						}
					}
				}
				if(e.getHandle() instanceof PigZombie){
					PigZombie pz = e.getHandle();
					for (String type : subTypes) {
						if(!"".equals(type)){
							try{
								pz.setAnger(Integer.parseInt(type));
							} catch (IllegalArgumentException ex){
								throw new ConfigRuntimeException(type + " is not a valid anger level",
										ExceptionType.FormatException, t);
							}
						}
					}
				}
				if (e.getHandle() instanceof Villager) {
					Villager v = e.getHandle();
					MCProfession job = MCProfession.FARMER;
					for (String type : subTypes){
						try {
							job = MCProfession.valueOf(type);
							v.setProfession(BukkitMCProfession.getConvertor().getConcreteEnum(job));
						} catch (IllegalArgumentException ex) {
							throw new ConfigRuntimeException(type + " is not a valid profession",
									ExceptionType.FormatException, t);
						}
					}
				}
				if (e.getHandle() instanceof Enderman) {
					Enderman en = e.getHandle();
					for (String type : subTypes){
						try {
							MaterialData held = new MaterialData(Material.valueOf(type));
							en.setCarriedMaterial(held);
						} catch (IllegalArgumentException ex) {
							throw new ConfigRuntimeException(type + " is not a valid material",
									ExceptionType.FormatException, t);
						}
					}
				}
				if(e.getHandle() instanceof Slime){
					Slime sl = e.getHandle();
					for (String type : subTypes) {
						if(!"".equals(type)){
							try{
								sl.setSize(Integer.parseInt(type));
							} catch (IllegalArgumentException ex){
								throw new ConfigRuntimeException(type + " is not a valid size",
										ExceptionType.FormatException, t);
							}
						}
					}
				}
				if(e.getHandle() instanceof Skeleton){
					Skeleton sk = e.getHandle();
					MCSkeletonType stype = MCSkeletonType.NORMAL;
					for (String type : subTypes) {
						try {
							stype = MCSkeletonType.valueOf(type);
							sk.setSkeletonType(BukkitMCSkeletonType.getConvertor().getConcreteEnum(stype));
						} catch (IllegalArgumentException ex){
							throw new ConfigRuntimeException(type + " is not a skeleton type",
									ExceptionType.FormatException, t);					
						}
					}
				}
				if(e.getHandle() instanceof Zombie && !(e.getHandle() instanceof PigZombie)){
					Zombie z = e.getHandle();
					for (String type : subTypes) {
						try {
							MCZombieType ztype = MCZombieType.valueOf(type);
							switch (ztype) {
							case BABY:
								z.setBaby(true);
								break;
							case VILLAGER:
								z.setVillager(true);
								break;
							default:
								break;
							}
						} catch (IllegalArgumentException ex){
							throw new ConfigRuntimeException(type + " is not a zombie state",
									ExceptionType.FormatException, t);
						}
					}
				}
				if(e.getHandle() instanceof Pig){
					Pig p = e.getHandle();
					for (String type : subTypes) {
						try {
							MCPigType ptype = MCPigType.valueOf(type);
							switch (ptype) {
							case SADDLED:
								p.setSaddle(true);
								break;
							default:
								break;
							}
						} catch (IllegalArgumentException ex){
							throw new ConfigRuntimeException(type + " is not a pig state",
									ExceptionType.FormatException, t);
						}
					}
				}
			}
            ids.push(new CInt(e.getEntityId(), t));
        }
        return ids;
    }

	public boolean exists() {
		//I dunno how well this will work, but it's worth a shot.
		try{
			w.getName();
			return true;
		} catch(Exception e){
			return false;
		}
	}

	public MCFallingBlock spawnFallingBlock(MCLocation loc, int type, byte data) {
		Location mcloc = (Location)((BukkitMCLocation)loc).getHandle();
		return AbstractionUtils.wrap(w.spawnFallingBlock(mcloc, type, data));
	}

	public boolean regenerateChunk(int x, int z) {
		return w.regenerateChunk(x, z);
	}

	public MCChunk getChunkAt(int x, int z) {
		return AbstractionUtils.wrap(w.getChunkAt(x, z));
	}
	
	public MCChunk getChunkAt(MCBlock b) {
		return AbstractionUtils.wrap(w.getChunkAt(((BukkitMCBlock) b).__Block()));
	}
	
	public MCChunk getChunkAt(MCLocation l) {
		return AbstractionUtils.wrap(w.getChunkAt(((BukkitMCLocation) l).asLocation()));
	}

	public void setThundering(boolean b) {
		w.setThundering(b);
	}
	
	public void setWeatherDuration(int time) {
		w.setWeatherDuration(time);
	}

	public void setThunderDuration(int time) {
		w.setThunderDuration(time);
	}
	
	public boolean isStorming() {
		return w.hasStorm();
	}
	
	public boolean isThundering() {
		return w.isThundering();
	}
}
