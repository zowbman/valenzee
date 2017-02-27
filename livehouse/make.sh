#!/bin/bash
#
usage()
{
    exit 1
}
if [  $# -gt 1  ]; then
    project=$1
    project=$(echo $project |sed 's/\///g')
    if ! ls -d $project >/dev/null 2>&1; then
        echo "error！$project not exisit!"
        usage
    fi
    if [  $# -eq 2 ]; then
        lan=$2
        lan=$(echo $lan |sed 's/\///g')
        if [ $lan = 'into' ];then
           cd $project && bash ./make.sh $project
           exit $?
        fi
    fi
fi
#echo "执行 gradlew myeclipse ..."
#bash gradlew myeclipse || exit $?
echo "执行 gradlew clean deploy ..."
bash gradlew clean deploy2 || exit $?
mkdir -p outer/lib
rsync -aq --delete build/deploy/ outer/lib/
rsync -aq --delete build/deploy/webapp/ outer/lib/webapp/
mkdir -p outer/etc
rsync -aq --delete src/main/resources/ outer/etc/ --exclude-from=exclude.list
install -v -c -m 755 run.sh outer/
if cd outer/etc; then
    for conf in $(find -type f -name "*.tpl"); do
        mv -v $conf ${conf%.tpl}
    done
fi
exit 0