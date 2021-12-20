# Random Things has no version for Minecraft 1.14.x and does not plan to. We are sorry.

# Random Things Mod

This is the source code for the Random Things Minecraft mod. Originally created and maintained by [lumien231](https://github.com/lumien231/Random-Things), I am playing around with this mod, and trying to get a working version for modern Minecraft versions.

This repo is very much a work in progress. Do not expect clean code or concise documentation (yet!)

## Documentation
There is [lumien's wiki](https://lumien.net/rtwiki/), but that is not up to date with the current state of the mod. Thus there is an empty `docs` folder in this repo which I plan to eventually fill with documentation and host it as a website. Until then, this mod is a wild west of features and recipes.

## Contributing
I appreciate all contributions to this project. This is a large project for one person to maintain solo, so any help is greatly appreciated.

## Developing
### Running Locally
Open Gradle Window in IntelliJ,
expand 'Tasks', then 'fg_runs', then run 'genIntellijRuns',

run all of 'run' tasks, 'runClient', 'runData', 'runServer'.

After that, run the "publish" task to make/remake the 'build' folder, which contains the .jar of the mod.
This jar file needs to be recompiled every time I make a change. So run 'publish' task, then run the project with the 'runClient' run configuration, and you'll get an up-to-date version of the mod

#### runServer:
then, you'll need to open the 'run' folder in the project,
look for the 'eula.txt' file, open it, and change that to true for the server to start.

From there, you rerun the 'RunServer' task, and a server should start this time.

#### runClient:
look at 'runClient' run configuration, the program arguments are WRONG.
Change the line suspend=y to suspend=n, it halts the program until it gets a connection
on port 8000 for some reason (it's the client, is it doing that)