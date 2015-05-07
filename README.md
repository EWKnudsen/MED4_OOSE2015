# Slick2D Eclipse Seed Project
This an eclipse project containing all the files necessary to begin developing games with [Slick 2D](http://slick.ninjacave.com/). You can fork this repository, clone it on your disk and start to work in Eclipse.

After having cloned the repository to your disk you have to follow these few steps:

1. Open up Eclipse.
2. Create a new java project and select the folder containing this seed.
2. Go to Project --> Properties in the menu bar.
3. Click on Java Build Path.
4. click the Add Jar button.
5. Select the *lib* folder in your project.
6. Select all the *.jar* files and click OK.
7. Expand *lwjgl.jar*.
8. Select *Natives Library Location* and click the Edit button.
9. Click the Workspace button.
10. Select the *native* folder in your project
11. Select your operating system and click OK until you get back to the default eclipse window.
12. Press play to check it it works.
13. Now, you can start making your own game.

---------------------------------------------------------------------------------------------------------------

# Rules And How To Play Guide:

* At the start of the game a controllable wizard is spawned in the middle of the map.
* You can move this wizard around the map by pressing either W, A, S or D on your keyboard.
* It is only possible to move within the visible window of the game.
* By pressing any mouse key, a missile will spawn from your wizard and propagate in the direction of your mouse curser.
* Every few second an enemy will spawn, with a 25% of it being a spider, and 75% being a zombie.
* The spawn rate of the enemies will increase as the game continues.
* The zombie chase you in a vertical, horizontal, and diagonal direction at the same speed.
* The spider chase you in a horizontal direction at a slow speed, and when your wizard and the spider is vertically aligned,   it will vertically moves towards you at high speed.
* If a missile collides with an enemy, they will die and be removed from the screen.
* If you collide with an enemy, your wizard will lose 20 health.
* Your wizard starts with 100 health, and your current health is always visible in the top corner.
* The game will end when your life reaches 0.
* Time and level will continue to increase until your wizard dies.
* It is up to you to see how long you can stay alive without dying.
