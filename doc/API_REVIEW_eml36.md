What about your API/design is intended to be flexible?

Austin

The data framework and inheritance structure allows for the user to easily create any type of terrain or character. The sprite inheritance structure let’s you choose different entities, and the implementation of the character interface allows for changing the characteristics of the same type of object.

Ezra

We have all of our different entities extend from a generic abstract class for towers, enemies, and weapons. This structure removes duplication and also makes it more flexible. 
	
Brian

We are using Bounds properties to determine image collision rather than a specific type of Node. This allows us to use Images, shapes, etc. to display the game instead of locking ourselves into using ImageViews. We also are letting the engine handle all KeyInputs by just passing keyCodes back to the back-end to be processed later.


How is your API/design encapsulating your implementation decisions?

Ezra

The backend of the game authoring is completely hidden from the front end and also is does not know how everything is implemented. We use controllers that set up and call all the getters and setters in the model and then use an observer pattern to signal to the front end something has changed in the model.

Austin

Everything is hidden. The data needs almost nothing from front end, and the front end just needs to know the types of sprites.

Brian

The back-end doesn’t need to know what the actual images used to display the objects, and the Front-End of the engine does not need to know any of the actual actions that are enabled by the game engine.

How is your part linked to other parts of the project?

We provide the means of loading and saving to game engine and the authoring environment. Mainly we pass info to the game engine.

We work closely with game authoring and are updating our the model of all the parts are being created in the front end. We also create a xml file output for the gameplayer to use to create the game.

We receive the locations of the objects from the engine and display the proper images needed to see the game in the Player. We also standardize data readers and writers to make the process of saving and loading games go smoothly.

What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?

Improper data might be loaded, so we check to see if it’s formatted before loading

When the authoring inputs a bad value in the front end. We will either throw them an error saying thats not a valid value or correct it for them.

Possible errors include incorrectly serialized XML files or objects that do not exist in the game-engine. These errors will be handled by skipping over the incorrect data and continuing to parse the file.

Why do you think your API/design is good (also define what your measure of good is)?

It is good because it restricts functionality of classes to one purpose, and the code is extremely easy to read and extend. That is you can easily add characteristics, change sprites, and pass info.

Our design is simple in that the models that hold data only have getters and setters. As a result, it is well encapsulated and also pretty flexible.

Our API/design is encapsulated nicely because the Player, Authoring Environment and Engine are not giving out more data than needed.


What feature/design problem are you most excited to work on?

Collisions

Working on what happens when different entities interact with each other

Finalizing the data-file format so that we can start building games

What feature/design problem are you most worried about working on?

Collisions

Providing more flexibility than the predetermined options we give them

Adding and removing objects from the game.

What is do you plan to implement this weekend?

Collisions

Linking up the game authoring to our part and returning an xml file

Finalizing the data-file format so that we can start building games

Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?

Some use cases include a basic collision with a static block, a collision where you die, a collision where you break an object, an instance where you only break the object if you land on top but you go through the bottom. They are generally small, which is good, but they encompass a wide array of situations.
We have created use cases for user setting basic properties for any entity such as weapon tower, and enemy. Basic properties include health, name, damage, etc

Our use cases, while ambitious, seem to be guiding us on the right path towards success for this project.

Do you have use cases for errors that might occur?

Yes. There is a use case for what happens when things that should not interact manage to hit each other. 

We have use cases for inputting an invalid parameter for an entity. We also have use case for creating an incomplete game xml data.

We do not currently have many use-cases for errors that might occur, and we are working on adding some to our initial design goals.


