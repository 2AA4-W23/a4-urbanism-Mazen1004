# Mesh Generator (Assignment #2 Walkthrough)

  - Author: Sébastien Mosser

## How to install?

```
mosser@azrael A2 % mvn install
```

It creates two jars:

  1. `generator/generator.jar` to generate meshes
  2. `visualizer/visualizer.jar` to visualize such meshes as SVG files

## Examples of execution

### Generating a mesh, grid or irregular
    
``` ensure for -k it's grid or irregular depending on the needs of the user 
mosser@azrael A2 % java -jar generator/generator.jar -k grid -h 1080 -w 1920 -p 1000 -s 20 -o img/grid.mesh 
mosser@azrael A2 % java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh
```

One can run the generator with `-help` as option to see the different command line arguments that are available

## Commands to run (need to fix later) 
```
java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/input.mesh
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh
java -jar visualizer/visualizer.jar -i img/lagoon.mesh -o img/irregular.svg
```


### Visualizing a mesh, (regular or debug mode)

```
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid.svg          
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/grid.mesh -o img/grid_debug.svg -x
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular.svg   
mosser@azrael A2 % java -jar visualizer/visualizer.jar -i img/irregular.mesh -o img/irregular_debug.svg -x
```

Note: PDF versions of the SVG files were created with `rsvg-convert`.

## Commands to run for STEP 1:
```
java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/input.mesh
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -mode lagoon
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -altitude mountain
java -jar visualizer/visualizer.jar -i img/lagoon.mesh -o img/irregular.svg
```