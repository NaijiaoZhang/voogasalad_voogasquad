### Use Cases

1. Creating a new level with a different logic for enemies to move 
2. Creating a game user profile
3. Creating an enemy animation effect
4. Creating a weapon animation effect
5. Signal deaths 
6. Creating and saving a new weapon, tower, or enemy that can then be loaded to be played in the game player.
7. Adjusting parameters of weapons, towers and player in the game engine that can then be loaded to be played in the game player. 
8. Drag towers that have already been loaded from the game engine onto the screen: tower manager 
9. Allowing the user to create a path
10. Allows user to define different win and lose goals
11. Allows the user to define a point system
12. User accidently creates an invalid path that does not connect to the end point of the path
13. User deletes an enemy or tower they created
14. Export an XML game configurations file
15. User creates different types of terrain on the map
16. Allow customization of the characteristics for the user profile
17. User sets the specifications for the amount of enemies at each level
18. Using the game setting, the game player should limit what can be dragged onto the screen based on the amount of money, free space on the BattleGrid, and health of the player. 
19. Project weapons with effects, such as freeze the enemy: Enemy instance takes in effect as a parameter and does the change accordingly, such as changing the moving speed
20. Upgrade the tower: Player will click upgrade and calls the back end upgrade method in tower which will change the shooting range and attacking speed 
21. Entering a different level: The game controller keeps checking the winning condition for the current  once met and then enter the next level                                                      
22. Pause the game: Controller freezes all the animation and disable user input other than resume button
23. Load XML file, initialize game stage: XML Loader, BattleGrid
24. Start game: EnemyAnimator, WeaponAnimator, EventHandler
25. Switch to a previously played game: XML Loader, BattleGrid
26. Save game progress: XML Saver
27. Change the tower’s attack preference to target the strongest enemies
28. User specifies different enemy types for different waves within a level
29. Enemies destroy the base and user loses
30. User changes game settings in the middle of a game
31. Enemy destroys tower
32. Adding a new image for a tower, enemy, or weapon
33. Saving an incomplete created game
34. User inputs an invalid parameter for a attribute
35. User creates a tower that is floating above the map
36. User tries to move a tower in between levels
37. User tries to purchase a tower that they cannot afford
38. User restarts the game
39. User loads an existing XML file to continue designing an incomplete game
40. User gives enemy different speed for each level


41. Set properties of a wave of enemies for a level
42. Set sound properties on a Type
43. Add a movement behavior for Tower  
44. Add a winning condition to the list of winning conditions
45. Add a losing condition to the list of losing conditions
46. Creates a collision effect between weapon and enemy that affects the tower that shot the weapon
47. Creates a collision effect that has a trigger it checks before implementing
48. Create different modes within a game
