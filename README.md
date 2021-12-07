Random Things

Open Gradle Window in IntelliJ,
expand 'Tasks', then 'fg_runs', then run 'genIntellijRuns',

run all of 'run' tasks, 'runClient', 'runData', 'runServer'.

# runServer:
then, you'll need to open the 'run' folder,
look for the 'eula.txt' file, open it, and change that to true for the server to start.

From there, you rerun the 'RunServer' task, and a server should start this time.

# runClient:
look at 'runClient' run configuration, the program arguments are WRONG.
Change the line suspend=y to suspend=n, it halts the program until it gets a connection
on port 8000 for some reason (it's the client, why the hell is it doing that)

# Error Issues:
It seems that I needed to run the "publish" task to re-do the 'build' folder, which contains the .jar of the mod.
This jar file needs to be recompiled every time I make a change I want to test... This makes sense
