@echo off
git remote -v
git remote add upstream https://github.com/Grasscutters/Grasscutter.git
git remote -v
git fetch upstream
git checkout development
git merge upstream/development
echo 同步完毕，但仍请修改一些内容，以适配Leekcutter。