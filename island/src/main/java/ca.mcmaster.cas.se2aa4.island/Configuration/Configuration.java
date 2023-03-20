package ca.mcmaster.cas.se2aa4.island.Configuration;

import org.apache.commons.cli.*;

public class Configuration {

    public static final String OUTPUT = "o";
    public static final String INPUT = "i";
    public static final String MODE = "mode";
    public static final String SHAPE = "shape";

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
    /*public String export(String key) {
        return cli.getOptionValue(key);
    }*/
    public String mode() {
        return this.cli.getOptionValue(MODE);
    }
    public String shape() {
        return this.cli.getOptionValue(SHAPE);
    }
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

        return options;
    }

}

