package ru.javarush.island.volokitin.entities.settings;

import com.fasterxml.jackson.databind.ObjectReader;
import com.fasterxml.jackson.dataformat.yaml.YAMLMapper;
import ru.javarush.island.volokitin.entities.organisms.OrganismsCommonSpecs;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class Settings {
    private static final String INIT_FILE = "volokitin/init.yml";
    private static volatile Settings SETTINGS;

    private int mapRows;
    private int mapCols;
    private int cycleDuration; // millis
    private Map<String, Integer> organismsInitialQuantity;
    private Boolean stopOnTimeout;
    private int gameDuration;
    private Map<String, Integer> organismsChildrenQuantity;
    private Map<String, Map<String, Integer>> chanceToGetEat;
    private Map<String, OrganismsCommonSpecs> organismsCommonSpecs;
    private List<String> organismsTypes;
    private int initialBirthPercent;
    private int unviableWeightPercent;
    private int animalGrowUpPercent;
    private int plantGrowUpPercent;

    private Settings() {
        try {
            URL resource = Settings.class.getClassLoader().getResource(INIT_FILE);
            ObjectReader objectReader = new YAMLMapper().readerForUpdating(this);
            if (Objects.nonNull(resource)) {
                objectReader.readValue(resource.openStream());
            }

            organismsTypes = new ArrayList<>(organismsCommonSpecs.keySet());
        } catch (IOException e) {
            System.out.printf("Ошибка при чтении файла настроек init.yml: %s", e);
        }
    }

    public int getMapRows() {
        return mapRows;
    }

    public int getMapCols() {
        return mapCols;
    }

    public Map<String, Integer> getOrganismsInitialQuantity() {
        return organismsInitialQuantity;
    }

    public int getCycleDuration() {
        return cycleDuration;
    }

    public Boolean getStopOnTimeout() {
        return stopOnTimeout;
    }

    public int getGameDuration() {
        return gameDuration;
    }

    public Map<String, Integer> getOrganismsChildrenQuantity() {
        return organismsChildrenQuantity;
    }

    public Map<String, Map<String, Integer>> getChanceToGetEat() {
        return chanceToGetEat;
    }

    public Map<String, OrganismsCommonSpecs> getOrganismsCommonSpecs() {
        return organismsCommonSpecs;
    }

    public List<String> getOrganismsTypes() {
        return organismsTypes;
    }

    public OrganismsCommonSpecs getOrganismCommonSpecsByType(String organismType) {
        return organismsCommonSpecs.get(organismType);
    }

    public int getInitialBirthPercent() {
        return initialBirthPercent;
    }

    public int getUnviableWeightPercent() {
        return unviableWeightPercent;
    }

    public int getAnimalGrowUpPercent() {
        return animalGrowUpPercent;
    }

    public int getPlantGrowUpPercent() {
        return plantGrowUpPercent;
    }

    public static Settings get() {
        Settings settings = SETTINGS;

        if (Objects.isNull(settings)) {
            synchronized (Settings.class) {
                if (Objects.isNull(settings = SETTINGS)) {
                    settings = SETTINGS = new Settings();
                }
            }
        }

        return settings;
    }
}
