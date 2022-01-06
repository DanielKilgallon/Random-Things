#!/bin/bash

cd docs/
npm install -g markdown-to-html
mkdir html/
markdown test.md > html/test.html