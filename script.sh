#!/bin/bash

echo "==================== COMMIT COUNTS PER AUTHOR ====================" 
git shortlog -sne --all
echo

echo "==================== LINES ADDED/DELETED PER AUTHOR ===================="
git log --all --numstat --pretty="%an" | \
awk 'NF==1{author=$0} NF==3{ins[author]+=$1; del[author]+=$2} \
     END{for(a in ins) printf "%-25s Insertions: %8d  Deletions: %8d  Total: %8d\n", a, ins[a], del[a], ins[a]+del[a]}' \
| sort -k6 -n -r
echo

echo "==================== TIMELINE OF COMMITS ===================="
git log --all --date=short --pretty="%ad %an %s" | sort
echo

echo "==================== COMMITS PER BRANCH ===================="
for branch in $(git branch -r | grep -v '\->'); do
    echo "---- $branch ----"
    git log --oneline $branch | wc -l | awk '{print "Commits:",$1}'
done
echo

echo "==================== FILES TOUCHED PER AUTHOR ===================="
git log --all --name-only --pretty="Author: %an" | \
awk 'NF==0{next} /^Author:/ {author=$2;next} {files[author][$0]++} \
     END{for(a in files){print "Author:",a; for(f in files[a]) print "   ",f; print ""}}'

