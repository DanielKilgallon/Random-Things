# Random Things Mod
[![Build](https://github.com/DanielKilgallon/Random-Things/actions/workflows/codeql-analysis.yml/badge.svg)](https://github.com/DanielKilgallon/Random-Things/actions/workflows/codeql-analysis.yml)
[![License](https://img.shields.io/github/license/DanielKilgallon/Random-Things)](https://github.com/DanielKilgallon/Random-Things/blob/1.18.1/LICENSE.md)
[![Release](https://img.shields.io/github/v/release/DanielKilgallon/Random-Things?include_prereleases)](https://github.com/DanielKilgallon/Random-Things/releases)
[![Downloads](https://img.shields.io/github/downloads/DanielKilgallon/Random-Things/total)](https://github.com/DanielKilgallon/Random-Things/releases)
[![wakatime](https://wakatime.com/badge/user/2e9479bb-ca4d-4c1e-8f34-68d16794ad5e/project/92b6974f-ff6c-480c-a2b1-7d44e1cb836c.svg)](https://wakatime.com/badge/user/2e9479bb-ca4d-4c1e-8f34-68d16794ad5e/project/92b6974f-ff6c-480c-a2b1-7d44e1cb836c)

This is the source code for the Random Things Minecraft mod. This was originally created and maintained by [lumien231](https://github.com/lumien231/Random-Things), I am currently working on updating it to Minecraft 1.18.

This repo is very much a work in progress. Do not expect clean code or concise documentation (yet!)

If you would like to follow my progress/current task, head over to the GitHub [project](https://github.com/DanielKilgallon/Random-Things/projects/1).

## Documentation
There is [lumien's wiki](https://lumien.net/rtwiki/), but that is not up-to-date with the current state of the mod. I have plans to create a website/wiki to showcase the mod features, but that is far in the future. Until then, this mod is a wild west of features and recipes.

## Contributing
I appreciate all contributions to this project. This is a large project for one person to maintain solo, so any help is greatly appreciated.

---
## Developing
### Running Locally
Open Gradle Window in IntelliJ, and look at tasks to run.

The relevant tasks are:
- `genIntellijRuns` - creates relevant config for IntelliJ
- `publish` - will build the .jar file in the `builds/libs/` folder.
- `runClient` - starts Minecraft instance and injects mod files into it.
- `runServer` - the first time it runs, it'll fail. You'll need to open the 'run' folder in the project, find the `eula.txt` file, open it, and change the flag to true. Now when you rerun the task, and a server will start.

### Common Errors:
If `runClient` hangs and doesn't start an instance, look at runClient configuration in your IDE, the program arguments might be wrong. In the args, the command `suspend=y` makes the task wait on port 8000, so change it to `suspend=n` and now the task should run normally.