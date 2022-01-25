import re
import os

# open build file and find the version number
with open("build.gradle") as f:
    content = f.read()
    # line by line
    for line in content.split("\n"):
        if re.search("^version = '*.*.*'$", line):
            # remove anything that is not the version tag (ex. 1.0.0)
            version = "v" + re.sub("[^\d\.\d\.\d]", "", line).strip()
            break

# confirm tag name, create and push tag
yesNo = input("create release tag for: {} ? (y/n)".format(version))
if yesNo.lower() == "y":
    print("creating release: " + version)

    os.system("git tag " + version)
    os.system("git push origin " + version)
    
    print("release is created and pushed")
    print("see progress -> https://github.com/DanielKilgallon/Random-Things/actions/workflows/create-release.yml")
else:
    print("stopped release by user")