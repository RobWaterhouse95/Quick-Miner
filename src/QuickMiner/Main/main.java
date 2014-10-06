package QuickMiner.Main;


import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.command.Command;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class main extends JavaPlugin implements Listener{

	
	@Override
	public void onEnable(){
		getLogger().info("Quick Miner has been enabled!");
		getServer().getPluginManager().registerEvents(this, this);
	}
	
	@Override
	public void onDisable(){
		getLogger().info("Quick Miner has been disabled");
		
	}
	
	
	@EventHandler
	public void msgOnLogin(PlayerJoinEvent e){		
		e.getPlayer().sendMessage("Type /QuickMiner for help");		
	}
	
	
	@Override
	public boolean onCommand(CommandSender csender, Command cmd, String label, String[] args) {
			Bukkit.getServer().broadcastMessage("command");
		if (cmd.getName().equalsIgnoreCase("QuickMiner")) { // If the player typed /basic then do the following...
			Player sender = (Player) csender;
			sender.sendMessage("To use QuickMiner, Right click a block with a Golden Hoe");
			sender.sendMessage("Some surrounding blocks around the one you chosen will disappear");
			sender.sendMessage("Only the blocks that are the same as the chosen block will be mined");
			return true;
		} //If this has happened the function will return true. 
	        // If this hasn't happened the value of false will be returned.
		return false; 
	}
	
	@EventHandler
	public void getBlockName(PlayerInteractEvent e){		
		if(e.getAction().equals(Action.RIGHT_CLICK_BLOCK)){
			int x = e.getClickedBlock().getX();
			int y = e.getClickedBlock().getY();
			int z = e.getClickedBlock().getZ();
			Player p = e.getPlayer();
			String Direction = getCardinalDirection(p);
						
			Material selBlock = e.getClickedBlock().getType();
			int xs;
			int ys;
			int zs;
			int xp;
			int yp;
			int zp;
			
			
			xs = e.getClickedBlock().getX();
			ys = e.getClickedBlock().getY();
			zs = e.getClickedBlock().getZ();
			xp = p.getLocation().getBlockX();
			yp = p.getLocation().getBlockY();
			zp = p.getLocation().getBlockZ();
			
			if(xs == xp && zs == zp){
				if(ys == yp - 1){
					p.sendMessage("Bottom Block");
				}
			}
			else if(p.getItemInHand().getType() == Material.GOLD_HOE){				
				if(Direction.equals("N")){				
					y=y+1;
					x=x-1;
					for(int count1=1; count1<4; count1++){
						for(int count2=1; count2<4; count2++){
							for(int i = 0; i < 3; i++){				
								if(Bukkit.getWorld("world").getBlockAt(x,y,z-i).getType().equals(selBlock)){
									Bukkit.getWorld("world").getBlockAt(x,y,z-i).breakNaturally();
								}
							}	
							y=y-1;
						}	
						y=y+3;
						x=x+1;
					}				
				}			
				else if(Direction.equals("E")){
					y=y+1;
					z=z-1;
					for(int count1=1; count1<4; count1++){
						for(int count2=1; count2<4; count2++){
							for(int i = 0; i < 3; i++){				
								if(Bukkit.getWorld("world").getBlockAt(x+i,y,z).getType().equals(selBlock)){
								Bukkit.getWorld("world").getBlockAt(x+i,y,z).breakNaturally();
								}							
							}	
							y=y-1;
						}	
						y=y+3;
						z=z+1;
					}						
				}	
				else if(Direction.equals("S")){
					y=y+1;
					x=x+1;
					for(int count1=1; count1<4; count1++){
						for(int count2=1; count2<4; count2++){
							for(int i = 0; i < 3; i++){		
								if(Bukkit.getWorld("world").getBlockAt(x,y,z+i).getType().equals(selBlock)){
									Bukkit.getWorld("world").getBlockAt(x,y,z+i).breakNaturally();
								}
								
							}	
							y=y-1;
						}	
						y=y+3;
						x=x-1;
					}
				}
				else if(Direction.equals("W")){
					y=y+1;
					z=z+1;
					for(int count1=1; count1<4; count1++){
						for(int count2=1; count2<4; count2++){
							for(int i = 0; i < 3; i++){				
								if(Bukkit.getWorld("world").getBlockAt(x-i,y,z).getType().equals(selBlock)){
									Bukkit.getWorld("world").getBlockAt(x-i,y,z).breakNaturally();
								}
								
							}	
							y=y-1;
						}	
						y=y+3;
						z=z-1;
					}	
				}
				
			}
			
			}
		}		
	
	public static String getCardinalDirection(Player player) {
        double rotation = (player.getLocation().getYaw() - 90) % 360;
        if (rotation < 0) {
            rotation += 360.0;
        }
         if (0 <= rotation && rotation < 67.5) {
            return "W";
        } else if (67.5 <= rotation && rotation < 157.5) {
            return "N";
        } else if (157.5 <= rotation && rotation < 247.5) {
            return "E";
        } else if (247.5 <= rotation && rotation < 337.5) {
            return "S";
        } else if (337.5 <= rotation && rotation < 360.0) {
            return "W";
        } else {
            return null;
        }
    }
}
