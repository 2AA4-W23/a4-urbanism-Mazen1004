package ca.mcmaster.cas.se2aa4.a2.visualizer.renderer.properties;

import ca.mcmaster.cas.se2aa4.a2.io.Structs;

import java.util.List;
import java.util.Optional;


public class isRiver implements PropertyAccess<String> {

    private static final String RIVERS = "rivers";
    @Override
    public Optional<String> extract(List<Structs.Property> props) {
        String value = new Reader(props).get(RIVERS);
        if (value == null)
            return Optional.empty();
        return Optional.of(value);
    }

}