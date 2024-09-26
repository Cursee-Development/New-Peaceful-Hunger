package com.cursee.peaceful_hunger;

import com.cursee.peaceful_hunger.platform.Services;

import java.io.*;

public class PeacefulHunger {

    public static boolean debugCommon = false;

    public static boolean enabled = true;
    public static final String CONFIG_FILEPATH = Services.PLATFORM.getGameDirectory().toString() + File.separator + "config" + File.separator +  "peaceful_hunger.toml";

    public static void init() {

        File directory = new File("config");
        if (!directory.isDirectory()) {
            directory.mkdir();
        }

        File configFile = new File(CONFIG_FILEPATH);
        if (!configFile.isFile()) {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(configFile))) {
                writer.write("enabled=true");
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
                    }

                    PeacefulHunger.enabled = Boolean.parseBoolean(line.replace("enabled=", ""));
                    index++;
                }
            }
            catch (IOException e) {
                Constants.LOG.info(e.getMessage());}
        }

    }
}