# Deriving 3D Step by Step

The intention of this project is to create a *very* simple line based 3D renderer without previous planning and without looking up any of the complete maths or techniques.

It will be neither well designed, it will not use best practices, nor will it be performant.

## Step 1: Create a couple of classes and put something on the screen

Let's start with a Player. For a start the player does nothing but stand at (0,0,0).

In later steps the player will be able to look into a specific direction, and after that the player will be able to move in the world.

Then we need a world. The world contains GameObjects. For a start game objects have a center an width, height, and length. That's a simple 3D object: A cube.

We want to be able to draw our cube on the screen. Since we want to draw lines, our cube needs to give out a number of lines - which are defined by pairs of points. Later we can change to a better method.

Okay this is getting a little too hard for me without an image.

So how do we do 3D. Let's draw a crude image:

https://github.com/ChristophHaag/3DPlayground/blob/master/img1.png

The user's eye is the origin (0,0,0) of our "screen space". The screen is at (0,0,1) with 1 meter z offset and it's normal vector is (0,0,1) too because it points in the direction of the z axis.

Now we simply calculate all intersection points of lines from our object's points to the eye with the screen plane. Then we get the intersections in world coordinates and world units. Those we can simply convert to pixels by saying 1 actual meter in world coordinates is 500 pixels long.

With this ansatz we get a reasonable 3D projection.

Two things are still missing for a first success:

1. Rotating the world around the player (when the player wants to rotate). In a real 3D renderer this would be done with a matrix multiplication but we want to manually rotate each point around the player.
2. Handling points and lines that are out of our view. I think we can handle this completely in pixel screen space since we know how many pixels our rendering area has. We can intersect our line we want to draw with the lines representing the screen edges. If there is no intersection and both points are out of our view, we don't need to draw them. If there is an intersection, we need to determine which parts are inside the screen and make a new line only drawing the parts inside.