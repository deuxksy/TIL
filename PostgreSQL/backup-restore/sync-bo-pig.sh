filename=$(date +"%y%m%d%H%M%S")
~/bin/bo-dump.sh $filename && ~/bin/pig-restore.sh $filename
