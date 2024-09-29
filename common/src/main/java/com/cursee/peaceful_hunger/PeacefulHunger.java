package com.cursee.peaceful_hunger;

import com.cursee.peaceful_hunger.platform.Services;

import java.io.*;

public class PeacefulHunger {

    public static boolean debugCommon = false;

    public static boolean enabled = true;
    public static boolean disable_natural_regeneration = false;
    public static final String CONFIG_FILEPATH = Services.PLATFORM.getGameDirectory().toString() + File.separator + "config" + File.separator +  "peaceful_hunger.toml";

    public static void init() {

        File directory = new File("config");
        if (!directory.isDirectory()) {
            directory.mkdir();
        }

        File configFile = new File(CONFIG_FILEPATH);
        if (!configFile.isFile()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
                writer.write("enabled=true" + '\n');
//                writer.write("disable_natural_regeneration=false");
            } catch (IOException e) {
                Constants.LOG.info(e.getMessage());
            }
        }
        else {
            try (BufferedReader reader = new BufferedReader(new FileReader(CONFIG_FILEPATH))) {
                String line;
                int index = 1;
                while ((line = reader.readLine()) != null) {

                    if (index == 1 && (!line.contains("enabled=") || !line.contains("true") || !line.contains("false"))) {
                        Constants.LOG.info("bro I'm literally about to crash out");
                        Constants.LOG.info("User error; config file 'peaceful_hunger.toml' has become invalid. Defaulting to enabling the mod.");
                        Constants.LOG.info("enabled=true");
                        Constants.LOG.info("enabled=false");
                        Constants.LOG.info("^^ Those are the only options. You must restart the game.");
                        PeacefulHunger.enabled = Boolean.parseBoolean(line.replace("enabled=", ""));
                    }

//                    if (index == 2 && (!line.contains("disable_natural_regeneration=") || !line.contains("true") || !line.contains("false"))) {
//                        Constants.LOG.info("bro I'm literally about to crash out");
//                        Constants.LOG.info("User error; config file 'peaceful_hunger.toml' has become invalid. Defaulting disable_natural_regeneration to false.");
//                        Constants.LOG.info("disable_natural_regeneration=true");
//                        Constants.LOG.info("disable_natural_regeneration=false");
//                        Constants.LOG.info("^^ Those are the only options. You must restart the game.");
//                        PeacefulHunger.disable_natural_regeneration = Boolean.parseBoolean(line.replace("disable_natural_regeneration=", ""));
//                    }

                    index++;
                }
            }
            catch (IOException e) {
                Constants.LOG.info(e.getMessage());}
        }

    }
}