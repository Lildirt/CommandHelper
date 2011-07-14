/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.laytonsmith.aliasengine.functions;

import com.laytonsmith.aliasengine.functions.exceptions.ConfigRuntimeException;
import com.laytonsmith.aliasengine.Constructs.CVoid;
import com.laytonsmith.aliasengine.Constructs.Construct;
import com.laytonsmith.aliasengine.Static;
import com.laytonsmith.aliasengine.functions.Exceptions.ExceptionType;
import com.laytonsmith.aliasengine.functions.exceptions.FunctionReturnException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.SortedSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.bukkit.command.Command;
import org.bukkit.command.PluginCommand;
import org.bukkit.entity.Player;
import org.bukkit.event.Event;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.RegisteredListener;
import org.bukkit.plugin.SimplePluginManager;

/**
 * This class is for functions that are experimental. They don't actually get added
 * to the documentation, and are subject to removal at any point in time, nor are
 * likely to have good documentation.
 * @author Layton
 */
public class Sandbox {
    @api public static class plugin_cmd implements Function{

        public String getName() {
            return "__plugin_cmd__";
        }

        public Integer[] numArgs() {
            return new Integer[]{2};
        }

        public String docs() {
            return "void {plugin, cmd} ";
        }

        public ExceptionType[] thrown() {
            return null;
        }

        public boolean isRestricted() {
            return true;
        }

        public void varList(IVariableList varList) {}

        public boolean preResolveVariables() {
            return true;
        }

        public String since() {
            return "0.0.0";
        }

        public Boolean runAsync() {
            return false;
        }

        public Construct exec(int line_num, Player p, Construct... args) throws ConfigRuntimeException {
            Object o = Static.getAliasCore().parent.getServer().getPluginManager();
            if(o instanceof SimplePluginManager){
                SimplePluginManager spm = (SimplePluginManager)o;
                try {
                    Method m = spm.getClass().getDeclaredMethod("getEventListeners", Event.Type.class);
                    m.setAccessible(true);
                    SortedSet<RegisteredListener> ss = (SortedSet<RegisteredListener>) m.invoke(spm, Event.Type.PLAYER_COMMAND_PREPROCESS);
                    for(RegisteredListener l : ss){
                        if(l.getPlugin().getDescription().getName().equals(args[0].val())){
                            PluginCommand.class.getDeclaredMethods();
                            Constructor c = PluginCommand.class.getDeclaredConstructor(String.class, Plugin.class);
                            c.setAccessible(true);
                            Command com = (Command)c.newInstance(l.getPlugin().getDescription().getName(), l.getPlugin()); 
                            List<String> argList = Arrays.asList(args[1].val().split(" "));
//                            com.execute(p, argList.get(0).substring(1), argList.subList(1, argList.size()).toArray(new String[]{}));
//                            l.callEvent(new Event() {});
//                            break;
                            l.getPlugin().onCommand(p, com, argList.get(0).substring(1), argList.subList(1, argList.size()).toArray(new String[]{}));
                        }
                    }
                } catch (InstantiationException ex) {
                    Logger.getLogger(Sandbox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalAccessException ex) {
                    Logger.getLogger(Sandbox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IllegalArgumentException ex) {
                    Logger.getLogger(Sandbox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (InvocationTargetException ex) {
                    Logger.getLogger(Sandbox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (NoSuchMethodException ex) {
                    Logger.getLogger(Sandbox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (SecurityException ex) {
                    Logger.getLogger(Sandbox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            return new CVoid(line_num);
        }
        
    }
    
    @api public static class _return implements Function{

        public String getName() {
            return "return";
        }

        public Integer[] numArgs() {
            return new Integer[]{1};
        }

        public String docs() {
            return "nothing {mixed} Returns the specified value from this function. It cannot be called outside a function.";
        }

        public ExceptionType[] thrown() {
            return null;
        }

        public boolean isRestricted() {
            return false;
        }

        public void varList(IVariableList varList) {}

        public boolean preResolveVariables() {
            return true;
        }

        public String since() {
            return "0.0.0";
        }

        public Boolean runAsync() {
            return null;
        }

        public Construct exec(int line_num, Player p, Construct... args) throws ConfigRuntimeException {
            throw new FunctionReturnException(args[0]);
        }
        
    }
}
