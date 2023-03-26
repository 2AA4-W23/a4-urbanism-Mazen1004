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

Note: SVG file with island visualization stored in "img" folderrr

## General Assumptions

Humidity Assumptions:
- Base Humidity is 60, this is humidity for oceans
- Island Humidity is 30, includes anywhere in the island
- If lake generated humidity is set to 45
- Generating Aquifers increases humidity of a tile by +40
- Soil Absorption profiles adjust humidity by +/- 5
- E.g a tile on the island that is a lake and has an aquifer would have humidity 45+40=85

Altitude (Elevation) Assumptions:
- Base Elevation is 0, if not altitude is defined base elevation is set to 0
- If altitude is defined it is extracted from the altitude functions
- Elevation is defined for the Mountain or Valley 

Biomes Assumptions:
- Biomes are calculated based on humidity and elevation values

Seed Assumptions:
- Seed 0 is the base seed (-seed 0)
- Anything other than seed 0 will produce a different seed
- however if generator is run those seeds will be local to that specific generator


### Definition of Done
- Code is neatly written and properly documented
- Code has been reviewed, tested and employed on a testing environment
- Code passes all unit, functional and acceptance tests

### Product Backlog

| Id | Feature title | Who? | Start (DD/MM/YY) | End (DD/MM/YY) | Status |
|:--:|---------------|------|-------|-----|--------|
|  01  | Generate the lagoon shape (MVP Product) | Kelvin  |  11/03/2023  | 15/03/2023 |  Done  |
|  02  | Determine the shape of the island with 2-3 different shape options | Matthew  |  15/03/2023  | 21/03/2023 |  Done  |
|  03  | Determine the island elevation profile with 2-3 different options | Mazen  |  15/03/2023  | 21/03/2023 |  Done  |
|  04  | Generate lakes which have a unique humidity value | Kelvin  |  19/03/2023  | 21/03/2023 |  Done  |
|  05  | Generate rivers as segments | Matthew  |  21/03/2023  | 25/03/2023 |  Done  |
|  06  | Generate aquifers that affect humidity values | Mazen  |  21/03/2023  | 22/03/2023 |  Done  |
|  07  | Each Tile should have unique humidity value | Kelvin  |  21/03/2023  | 22/03/2023 |  Done  |
|  08  | Calculate Soil Absorption based on user inputted profile | Matthew  |  25/03/2023  | 25/03/2023 |  Done  |
|  09  | Each Tile should be assigned a biomes value based on humidity and elevation | Mazen  |  21/03/2023  | 24/03/2023 |  Done  |
|  10  | Colors assigned to each biomes | Kelvin  |  23/03/2023  | 25/03/2023 |  Done  |
|  11  | User selects biomes from Whittaker Diagram | Matthew  |  23/03/2023  | 25/03/2023 |  Done  |
|  12  | Every Mesh Generated should have a unique seed  | Mazen  |  25/03/2023  | 26/03/2023 | Done   |
|  13  | User should create an island from the seed value  | Mazen  |  25/03/2023  | 26/03/2023 |  Done |
|  14  | Unit Testing for all features  | Kelvin  |  26/03/2023  | 26/03/2023 |  Done  |