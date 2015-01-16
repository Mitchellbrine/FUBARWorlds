package mc.Mitchellbrine.radicalWorlds;

import cpw.mods.fml.common.Mod;
import cpw.mods.fml.common.event.FMLPreInitializationEvent;
import mc.Mitchellbrine.radicalWorlds.utils.References;
import net.minecraft.world.WorldSettings;
import net.minecraftforge.common.MinecraftForge;

/**
 * Created by Mitchellbrine on 2015.
 */
@Mod(modid= References.MODID,name=References.NAME,version=References.VERSION)
public class RadicalWorlds {

    @Mod.EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        MinecraftForge.TERRAIN_GEN_BUS.register(new EventStuff());
        MinecraftForge.EVENT_BUS.register(new EventStuff());
    }

}
