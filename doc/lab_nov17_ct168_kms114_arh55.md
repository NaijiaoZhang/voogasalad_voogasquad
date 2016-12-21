### Part 1 : Authoring
1. Quin: Controller instantiates the game data. The API: the author model has the ability to get list of preset sprites and ability to add sprite to that preset list
and add sprite to current level. Controller API: both model and view have access to the controller API, allows everyone to get the current game. A lot of observables
for back-end objects. It is flexible in that the back-end doesn't need to know anything about the front-end to update the front-end (without explicit method call because
you can set a listener).
Addison: Weaves in with API. API for data is the constructors for what makes a game. A game has levels, levels have sprites, etc. (this is how the game is built). This
can be serialized to run the game and be run in the game engine.
Kayla: Our API is split into two: the API for the UI/view and the API for what is passed to the game engine. The UI/view is our internal API. The view sees all of the
components that build the physical view. Our external API is contained in the interfaces that are passed through the game engine. Our game engine is able to see
the interfaces which contain only what the back-end should know.
2. The listener on the observable type only finds out that the back-end has changed when it needs to. Game data isn't hiding anything. The player won't be able to 
see what is going on with game data, but game engine is the intermediary, but basically quite open.
3. You can see the answer to this question in #1 and #2.
4. Things that can go wrong in reflection.
5. Our measure of good is having flexible code that allows for extension without modification. The design is good because the different components only communicate
as necessary and don't know much about each other.


### Part 2
1. Defining sprite interactions dynamically through building a command tree. Addison: Most excited to work on fitting everything in one game that exists together as simply
as possible. Kayla: working on the tabs in the side toolbar.
2. Addison: detecting collisions. Kayla: I am most worried about working on integrating the game engine and game player components.
3. Addison: integrating data with the authoring environment. Kayla: finishing up the side toolbar and getting functionality.
4. The use cases could have been more descriptive, but overall relatively reasonable and we are on track to complete them.
5. Addison and Quin: Yes. We account for errors. Kayla: I believe so, if not, that will be added immediately.