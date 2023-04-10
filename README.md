# Urbanism (Assignment #4)
  - Author [youssm19@mcmaster.ca]

## Setup Commands
```
mkdir img (create an image folder in same level as island/generator/visualizer)
mvn clean
mvn install
```
## Generate Base Mesh
```
java -jar generator/generator.jar -k irregular -h 1080 -w 1920 -p 1000 -s 20 -o img/input.mesh
```
## Examples of Executions for Island with Different Properties
```
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -cities 15
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -cities 10
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape square -lakes 10 -cities 20
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -lakes 30 -aquifers 15 -cities 20
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -biomes tundra -cities 25
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -lakes 15 -aquifers 15 -cities 30 -biomes desert
java -jar island/island.jar -i img/input.mesh -o img/lagoon.mesh -shape circle -soil wetSoil -lakes 5 -cities 40 -seed 0
```


## Visualizing Island
```
java -jar visualizer/visualizer.jar -i img/lagoon.mesh -o img/irregular.svg
```
## Dependency Issue Note
Note: Sometimes when running the island generator command a dependency issue occurs preventing the island from generating, if this happens(unlikely to happen) rerunning mvn clean and mvn install once/twice will fix the issue and island will be generated.

## General Assumptions

Altitude (Elevation) Assumptions:
- Cities cannot be generated in valleys or mountains, hence generating cities with elevation attribute will result in no cities being generated.

Lakes Assumptions
- Cities cannot be generated on lakes and roads avoid lake tiles all together going around them

Biomes Assumptions
- Cities and roads can be generated over different biomes, refer to example islands to generate such cities

Rivers Note
- Rivers feature is technical debt from A3, hence adding rivers will not show in the island mesh and therefore is ignored

### Definition of Done
- Code is neatly written and properly documented
- Code has been reviewed, tested and employed on a testing environment
- Code passes all unit, functional and acceptance tests

### Product Backlog
Refer to the KanBan Board
