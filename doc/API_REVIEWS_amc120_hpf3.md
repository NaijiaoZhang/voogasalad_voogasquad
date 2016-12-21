
## Part 1
* data is flexible, game authoring accepts a lot of data
* The game objects are general, so a variety of games can be implemented
* The back end has no information about front end, front end only uses getters
* game authoring and game engine only communicate through an XML file with a defined structure
* The game engine is only connected to the game authoring through the XML
* User ability is restricted, no bad inputs allowed
* bad inputs can be read into the xml, the parser validates xml and throws errors if invalid
* API is well encapsulated and extensible

## Part 2
* excited to work on heads up display
* worried about saving data across multiple runs of the program
* I plan on implementing persistent data and allowing user to code with groovy
* The use cases are reasonably sized and cover each part of the project well
* We do not