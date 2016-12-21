##Part 1
- What about your API/design is intended to be flexible?
We initialize the grid and all the info at level base, in case some game will change the map at each level. 

- How is your API/design encapsulating your implementation decisions?

- How is your part linked to other parts of the project?
We are still working on controller. But we will have a lot objects bound with front end. 

- What exceptions (error cases) might occur in your part and how will you handle them (or not, by throwing)?
We will throw a error message and restart the game. 

- Why do you think your API/design is good (also define what your measure of good is)?
I think is good, because we encapsulated as much as possible. 

##Part 2
- What feature/design problem are you most excited to work on?
I guess to implement all the effects.

- What feature/design problem are you most worried about working on?
The collision.

- What is do you plan to implement this weekend?
finishing up the basic implementation of model		


- Discuss the use cases/issues created for your pieces: are they descriptive, appropriate, and reasonably sized?
yes. 

- Do you have use cases for errors that might occur?
XML has tag indicating whether is a valid game, so we will only load the valid game. The game authoring part will ensure the valid game is actually valid. 