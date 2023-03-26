# Terrain Generator Team 52 (Assignment #3)

  - Author #1 [yuk42@mcmaster.ca]
  - Author #2 [cheunm23@mcmaster.ca]
  - Author #3 [youssm19@mcmaster.ca]

## Setup Commands
```
mvn clean
mvn install
```
## Generate Base Mesh
```
java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh
```
## Examples of Executions for Island
1. TESTING STEP 1 (MVP)
```
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -mode lagoon
```

2. TESTING STEP 2
   a) Example for shapes
   ```
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle
   ```
   
   b) Example for lakes/aquifers/rivers
   ```
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -lakes 10
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -lakes 10 -aquifers 15
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -lakes 10 -aquifers 15 -rivers 2
   ```
   
   c) Example for elevations
   ```
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -lakes 5 -altitude mountain
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -altitude valley
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -aquifers 10 -altitude valley
   ```
   
   d) Examples for biomes
   ```
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -biomes tundra
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -biomes desert 
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -lakes 20 -biomes tundra
   ```
   e) Examples for soil
   ```
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -soil wetSoil
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -aquifers 10 -soil drySoil 
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -lakes 20 -soil wetSoil
   ```

Example Island: (Square Island with 7 lakes, 10 aquifers, mountain altitude, tundra biomes)
```
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -lakes 7 -aquifers 10 -altitude valley -biomes tundra -seed 0
```

## Visualizing Island
```
java -jar visualizer/visualizer.jar -i img/lagoon.mesh -o img/irregular.svg
```

Note: SVG file with island visualization stored in "img" folder

Humidity Assumptions:
// Base Humidity is 50, this is humidity for oceans
// Island Humidity is 20, includes anywhere on the island
// If lake generated humidity is set to 40
// Generating Aquifers increases humidity of a tile by +30
// E.g a tile on the island that is a lake and has an aquifer would have humidity 40+30=70
// Base Elevation is 0, if not altitude is defined
// If altitude is defined it is extracted from the altitude functions

Altitude (Elevation) Assumptions:
// Base Elevation is 0, if not altitude is defined base elevation is set to 0
// If altitude is defined it is extracted from the altitude functions
// Elevation is defined for the Mountain or Valley 

Biomes Assumptions:
// Biomes are calculated based on humidity and elevation values

Seed Assumptions:
// Seed 0 is the base seed (-seed 0)
// Anything other than seed 0 will produce a different seed
// however if generator is run those seeds will be local to that specific generator


   
