package io.jstro.convertconfigs;

import de.themoep.idconverter.IdMappings;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConvertVouchers
{
    public static void main(String[] args) throws IOException, InvalidConfigurationException {
        String filename = "Config.yml";
        YamlConfiguration config = new YamlConfiguration();
        config.load(filename);
        for(String key :config.getKeys(true))
        {
            if(key.endsWith(".Item"))
            {
                config.set(key, IdMappings.getById(config.getString(key)).getFlatteningType().toUpperCase());
            }
            if(key.endsWith(".Items"))
            {
                List<String> oldList = config.getStringList(key);
                System.out.println(oldList);
                ArrayList<String> newList = new ArrayList<>();
                for(String line : oldList)
                {
                    String oldId = line.split(",")[0].split(":")[1];
                    String newId = IdMappings.getById(oldId).getFlatteningType().toUpperCase();
                    newList.add(line.replace("Item:" + oldId, "Item:" + newId));
                }
                config.set(key, newList);
            }
        }
        config.save(filename);
    }
}
