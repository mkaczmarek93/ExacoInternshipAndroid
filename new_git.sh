#!/bin/bash

rm -rf .git

git init
git add .
git commit -m "Initial commit"

git remote add origin git@bitbucket.org:m_kaczmarek/weather-demo-quest.git
git push -u --force origin master