# Terrain Generator Team 52 (Assignment #3)

  - Author: Mazen Youssef, Kelvin Yu, Matthew Cheung

## Setup Commands
1. 'mvn clean'
2. 'mvn install'

## Generate Base Mesh
```
java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/irregular.mesh
```
## Examples of Executions for Island
1. TESTING STEP 1 (MVP)
'java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -mode lagoon'

2. TESTING STEP 2
   a) Example for shapes
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle
   
   b) Example for lakes/aquifers/rivers
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -lake 10
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -lake 10 -aquifers 15
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -lake 10 -aquifers 15 -rivers 2
   
   c) Example for elevations
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -altitude mountain
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -altitude valley
   
   d) Examples for biomes
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -biome tundra
   java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -biome desert

Example Island: (Square Island with 7 lakes, 10 aquifers, mountain shape, tundra biome)
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -lakes 7 -aquifers 10 -altitude mountain -biome tundra

## Visualizing Island
java -jar visualizer/visualizer.jar -i img/lagoon.mesh -o img/irregular.svg

Note: SVG file with island visualization stored in "img" folder
   
