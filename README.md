# Assignment A2: Mesh Generator

  - Author #1 [yuk42@mcmaster.ca]
  - Author #2 [cheunm23@mcmaster.ca]
  - Author #3 [youssm19@mcmaster.ca]

## How to run the product

_This section needs to be edited to reflect how the user can interact with thefeature released in your project_

### Installation instructions

This product is handled by Maven, as a multi-module project. We assume here that you have cloned the project in a directory named `A2`

To install the different tooling on your computer, simply run:

```
mosser@azrael A2 % mvn install
```

After installation, you'll find an application named `generator.jar` in the `generator` directory, and a file named `visualizer.jar` in the `visualizer` one. 

### Generator

To run the generator, go to the `generator` directory, and use `java -jar` to run the product. The product takes one single argument (so far), the name of the file where the generated mesh will be stored as binary.

```
mosser@azrael A2 % cd generator 
mosser@azrael generator % java -jar generator.jar sample.mesh
mosser@azrael generator % ls -lh sample.mesh
-rw-r--r--  1 mosser  staff    29K 29 Jan 10:52 sample.mesh
mosser@azrael generator % 
```

### Visualizer

To visualize an existing mesh, go to the `visualizer` directory, and use `java -jar` to run the product. The product take two arguments (so far): the file containing the mesh, and the name of the file to store the visualization (as an SVG image).

```
mosser@azrael A2 % cd visualizer 

DEBUG MODE ON:
mosser@azrael visualizer % java -jar visualizer.jar ../generator/sample.mesh sample.svg on

DEBUG MODE OFF:
mosser@azrael visualizer % java -jar visualizer.jar ../generator/sample.mesh sample.svg off

... (lots of debug information printed to stdout) ...

mosser@azrael visualizer % ls -lh sample.svg
-rw-r--r--  1 mosser  staff    56K 29 Jan 10:53 sample.svg
mosser@azrael visualizer %
```
To viualize the SVG file:

  - Open it with a web browser
  - Convert it into something else with tool slike `rsvg-convert`

## How to contribute to the project

When you develop features and enrich the product, remember that you have first to `package` (as in `mvn package`) it so that the `jar` file is re-generated by maven.

## Backlog


### Definition of Done
- Code is neatly written and properly documented
- Code has been reviewed, tested and employed on a testing environment
- Code passes all unit, functional and acceptance tests



### Product Backlog

| Id | Feature title | Who? | Start (DD/MM/YY) | End (DD/MM/YY) | Status |
|:--:|---------------|------|-------|-----|--------|
|  01  | Implement segments between vertices for 1 square | Kelvin  |  06/02/2023  | 09/02/2023 |  Done  |
|  02  | Create full grid of squares | Matthew  |  09/02/2023  | 13/02/2023 |  Done  |
|  03  | Colour vertices to random RGB value | Mazen  |  09/02/2023  | 13/02/2023 |  Done  |
|  04  | Colour segments to average between two points | Kelvin  |  09/02/2023  | 13/02/2023 |  Done  |
|  05  | Debug mode | Matthew  |  13/02/2023  | 14/02/2023 |  Done  |
|  06  | Unique vertices and segments | Mazen  |  17/02/2023  | 20/02/2023 |  Done  |
|  07  | Centroids in squares | Kelvin  |  17/02/2023  | 20/02/2023 |  Done  |
|  08  | Squares defined as polygons | Matthew  |  20/02/2023  | 20/02/2023 |  Done  |
|  09  | Centroids in squares | Mazen  |  20/02/2023  | 20/02/2023 |  Done  |
|  10  | Neighbouring indices for each polygon | Kelvin  |  20/02/2023  | 22/02/2023 |  Done  |
|  11  | MeshADT implementation | Matthew  |  27/02/2023  | 27/02/2023 |  Done  |
|  12  | Computed Voronoi Diagram  | Mazen  |  27/02/2023  | 27/02/2023 |  Done  |
|  13  | Apply Lloyd relaxation  | Kelvin  |  27/02/2023  | 27/02/2023 |  Done  |
|  14  | Crop Mesh to expected Size  | Matthew  |  27/02/2023  | 27/02/2023 |  Done  |
|  15  | Compute neighbourhood relationships using Delaunay’s triangulation  | Mazen  |  27/02/2023  | 27/02/2023 |  Done  |
|  16  | Reordered the segments for each irregular polygon  | Kelvin  |  27/02/2023  | 27/02/2023 |  Done  |

