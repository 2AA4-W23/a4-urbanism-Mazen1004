package ca.mcmaster.cas.se2aa4.pathfinder.configuration;

import org.apache.commons.cli.*;

public class Configuration {


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


    private Options options() {
        Options options = new Options();
        return options;
    }

}
