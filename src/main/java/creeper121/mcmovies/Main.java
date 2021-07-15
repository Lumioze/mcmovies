package creeper121.mcmovies;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.server.MapInitializeEvent;
import org.bukkit.map.MapCanvas;
import org.bukkit.map.MapRenderer;
import org.bukkit.map.MapView;
import org.bukkit.plugin.java.JavaPlugin;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.Properties;


public final class Main extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        getServer().getPluginManager().registerEvents(this, this);

        if(System.getProperty("sun.java2d.d3d").equalsIgnoreCase("true")) {
            Properties properties = new Properties();
            properties.setProperty("sun.java2d.transaccel", "True");
            properties.setProperty("sun.java2d.ddforcevram", "True");
            System.setProperties(properties);
        }
    }

    @Override
    public void onDisable() {

    }

    @EventHandler
    public void onMapInit(MapInitializeEvent e) {
        MapView view = e.getMap();
        view.getRenderers().clear();
        view.addRenderer(new MapRenderer() {
            @Override
            public void render(MapView map, MapCanvas canvas, Player player) {
                BufferedImage frame = null;
                try {
                    frame = ImageIO.read(new URL("http://127.0.0.1:5000"));
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    canvas.drawImage(0, 0, frame);
            }
        });
    }
}