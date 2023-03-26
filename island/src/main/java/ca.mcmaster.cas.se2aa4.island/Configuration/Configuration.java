package ca.mcmaster.cas.se2aa4.island.Configuration;

import org.apache.commons.cli.*;

public class Configuration {

    public static final String OUTPUT = "o";
    public static final String INPUT = "i";
    public static final String MODE = "mode";
    public static final String SHAPE = "shape";
    public static final String LAKES = "lakes";
    public static final String RIVERS = "rivers";
    public static final String AQUIFERS = "aquifers";
    public static final String ALTITUDE = "altitude";
    public static final String BIOMES = "biomes";
    public static final String SOIL = "soil";



    private CommandLine cli;
    public Configuration(String[] args) {
        try {
            this.cli = parser().parse(options(), args);
        } catch (ParseException pe) {
            throw new IllegalArgumentException(pe);
        }
    }

    private CommandLineParser parser() {
        return new DefaultParser();
    }
    public String export(String key) {
        return cli.getOptionValue(key);
    }
    public String mode() {
        return this.cli.getOptionValue(MODE,"none");
    }
    public String lakes() {
        return this.cli.getOptionValue(LAKES, String.valueOf(0));
    }
    public String aquifers() {
        return this.cli.getOptionValue(AQUIFERS, String.valueOf(0));
    }
    public String rivers() {
        return this.cli.getOptionValue(RIVERS, String.valueOf(0));
    }
    public String shape() {
        return this.cli.getOptionValue(SHAPE,"none");
    }
    public String altitude() {return this.cli.getOptionValue(ALTITUDE,"none");}
    public String biomes() {return this.cli.getOptionValue(BIOMES,"none");}
    public String soil() {return this.cli.getOptionValue(SOIL,"none");}
    public String input() {
        return this.cli.getOptionValue(INPUT);
    }
    public String output() {
        return this.cli.getOptionValue(OUTPUT, "output.svg");
    }

    private Options options() {
        Options options = new Options();
        options.addOption(new Option(INPUT, true, "Input file (SVG)"));
        options.addOption(new Option(OUTPUT, true, "Output file (MESH)"));
        options.addOption(new Option(MODE, true,"LAGOON MODE"));
        options.addOption(new Option(SHAPE, true,"SHAPE MODE"));
        options.addOption(new Option(LAKES, true,"Lake Count"));
        options.addOption(new Option(RIVERS, true,"Lake Count"));
        options.addOption(new Option(AQUIFERS, true,"Aquifer Count"));
        options.addOption(new Option(ALTITUDE, true,"Altitude Count"));
        options.addOption(new Option(BIOMES, true,"Biomes Type"));
        options.addOption(new Option(SOIL, true,"Soil Type"));

        return options;
    }

}

