package io.jstro.convertconfigs;

import de.themoep.idconverter.IdMappings;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ConvertCrates {
    public static void main(String[] args) throws IOException, InvalidConfigurationException
    {
        File dir = new File("Crates");
        for (File file : dir.listFiles()) {
            YamlConfiguration config = new YamlConfiguration();
            config.load(file);
            for (String key : config.getKeys(true)) {
                if (key.endsWith(".Item") || key.endsWith(".DisplayItem")) {
                    config.set(key, IdMappings.getById(config.getString(key)).getFlatteningType().toUpperCase());
                }
            }
            config.save(file);
        }
    }
}
